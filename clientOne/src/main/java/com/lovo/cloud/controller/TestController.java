package com.lovo.cloud.controller;

import com.lovo.cloud.entity.InfoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TestController {
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("getInfo")
    public  String getInfo(int tag){
        String url="http://clienttwo/infoString/"+tag;
       //远程调用
        ResponseEntity responseEntity=
                restTemplate.getForEntity(url,String.class);
         //获得内容
     return    responseEntity.getBody().toString();
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
}
