package com.lovo.cloud.controller;

import com.lovo.cloud.entity.InfoEntity;
import com.lovo.cloud.service.api.InfoService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {
    @Autowired
    private RestTemplate restTemplate;

    //注入本地远程调用接口
    @Autowired
    private InfoService infoService;
   @RequestMapping("test")
    public  String test(){
        com.lovo.basic.entity.InfoEntity info=new
                com.lovo.basic.entity.InfoEntity();
        info.setInfo("hello info");
        return info.getInfo();
    }
    @RequestMapping("getInfo")
    @HystrixCommand(fallbackMethod = "getInfob")
    public  String getInfo(int tag){
        String url="http://clienttwo/infoString/"+tag;
        //远程调用
        ResponseEntity responseEntity=
                restTemplate.getForEntity(url,String.class);
        //获得内容
        return    responseEntity.getBody().toString();
    }

    public String getInfob(int tag){
        return "系统暂时无法访问"+tag;
    }

    @RequestMapping("getInfo2")
    public  String getInfo2(int tag){
        String url="http://clienttwo/infoEntity/"+tag;
        //远程调用
        ResponseEntity responseEntity=
                restTemplate.getForEntity(url, InfoEntity.class);
        InfoEntity infoEntity= (InfoEntity) responseEntity.getBody();
        //获得内容
        return infoEntity.getInfo();
    }
    @RequestMapping("getInfo3")
    public  String getInfo3(int tag){
        String url="http://clienttwo/infoEntityJson/"+tag;
        //远程调用
        ResponseEntity responseEntity=
                restTemplate.getForEntity(url, String.class);
        return responseEntity.getBody().toString();

    }

    @RequestMapping("getInfoPost1")
    public  String getInfoPost1(int tag){
        String url="http://clienttwo/infoEntityPost";
        InfoEntity infoEntity=new InfoEntity();
        infoEntity.setInfo("我是前端");
        //url-请求的地址，tag请求的参数，InfoEntity.class返回的类型
        ResponseEntity responseEntity=
                restTemplate.postForEntity(url,infoEntity,InfoEntity.class);
        infoEntity=(InfoEntity)responseEntity.getBody();
        return infoEntity.getInfo();

    }

    @RequestMapping("getInfoPost2")
    public  String getInfoPost2(int tag){
        String url="http://clienttwo/infoEntityPost2";
        Map<String,Integer> map=new HashMap<>();
        map.put("tag",tag);
        ResponseEntity responseEntity=
                restTemplate.postForEntity(url,map,InfoEntity.class);
        return ((InfoEntity)responseEntity.getBody()).getInfo();

    }

    @RequestMapping("getFeignString")
    public String getFeignString(int tag){
        String str= infoService.infoString(tag);
        return str;
    }
    @RequestMapping("infoEntityPost")
    public String infoEntityPost(){
         InfoEntity infoEntity=new InfoEntity();
         infoEntity.setInfo("赵云");
        String str= infoService.infoEntityPost(infoEntity).getInfo();
        return str;
    }
@RequestMapping("infoEntityJson")
    public String infoEntityJson(int tag){
        return infoService.infoEntityJson(tag);
    }
}
