package com.lovo.cloud.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {

    @RequestMapping("infoString/{tag}")
    public  String infoString(@PathVariable("tag") int tag){

        switch (tag){
            case 1:
                return "hello cloud 1-2";
            case 2:
                return "hello cloud 2-2";
            default:
                return "eror";
        }
    }
}
