package com.lovo.cloud.service.api;

import com.lovo.cloud.entity.InfoEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
@FeignClient("clienttwo") //被调用服务器在注册服务器中的名字
public interface InfoService {
    //与远程的方法进行对应
    @RequestMapping("/infoString/{tag}")
    public  String infoString(@PathVariable("tag") int tag);

    @RequestMapping("/infoEntityPost")
    public InfoEntity infoEntityPost(InfoEntity infoEntity);

    @RequestMapping(value = "/infoEntityJson/{tag}")
    public String infoEntityJson(@PathVariable("tag") int tag);
}
