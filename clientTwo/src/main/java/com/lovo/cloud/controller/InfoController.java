package com.lovo.cloud.controller;

import com.lovo.cloud.entity.InfoEntity;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController  //所有方法不做视图渲染，只返回数据
public class InfoController {
    @RequestMapping("infoString/{tag}")
    public  String infoString(@PathVariable("tag") int tag){

        switch (tag){
            case 1:
                return "hello cloud 1";
            case 2:
                return "hello cloud 2";
                default:
                    return "eror";
        }
    }

    @RequestMapping("infoEntity/{tag}")
    public InfoEntity infoEntity(@PathVariable("tag") int tag){
        InfoEntity infoEntity=new InfoEntity();
        switch (tag){
            case 1:
                infoEntity.setInfo("hello cloud-1");
                break;
            case 2:
                infoEntity.setInfo("hello cloud-2");
                break;

        }
        return  infoEntity;
    }

    @RequestMapping(value = "infoEntityJson/{tag}"
            ,produces = "application/json;charset=utf-8")
    public InfoEntity infoEntityJson(@PathVariable("tag") int tag){
        InfoEntity infoEntity=new InfoEntity();
        switch (tag){
            case 1:
                infoEntity.setInfo("hello cloud-1");
                break;
            case 2:
                infoEntity.setInfo("hello cloud-2");
                break;

        }
        return  infoEntity;
    }


    @RequestMapping("infoEntityPost")
    public InfoEntity infoEntityPost(@RequestBody InfoEntity infoEntity){
        infoEntity.setInfo("我是后台服务器...");
        return  infoEntity;
    }

    @RequestMapping("infoEntityPost2")
    public InfoEntity infoEntityPost(@RequestBody Map<String,Integer> map){
        InfoEntity infoEntity=new InfoEntity();
        switch (map.get("tag")){
            case 1:
                infoEntity.setInfo("hello cloud-1");
                break;
            case 2:
                infoEntity.setInfo("hello cloud-2");
                break;

        }
        return  infoEntity;
    }
}
