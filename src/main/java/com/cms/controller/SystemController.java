package com.cms.controller;

import com.cms.entity.SystemEntity;
import com.cms.service.SystemService;
import com.cms.utils.ResultType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
public class SystemController {
    @Autowired
    SystemService systemService;

    /**
     * 获得系统名和id
     * */
    @GetMapping("/system/getSysName")
    public ResultType<SystemEntity> getSysName(){
        return systemService.getSysName();
    }

    @GetMapping("/system/getSysDescribe")
    public ResultType<SystemEntity>  getSysDescribe(@RequestParam int currentPage, @RequestParam int number){
        return systemService.getSysDescribe(currentPage,number);
    }

    @GetMapping("/system/getSysDetail")
    public ResultType<SystemEntity>  getSysDetailById(@RequestParam int sysId){
        return systemService.getSysDetailById(sysId);
    }

}
