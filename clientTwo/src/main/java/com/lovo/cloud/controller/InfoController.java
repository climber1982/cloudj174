package com.lovo.cloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
