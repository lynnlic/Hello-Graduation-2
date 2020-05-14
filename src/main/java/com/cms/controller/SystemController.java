package com.cms.controller;

import com.cms.entity.DataEntity;
import com.cms.entity.SystemEntity;
import com.cms.service.SystemService;
import com.cms.utils.ResultType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
public class SystemController {
    @Autowired
    SystemService systemService;

    /**
     * 获得系统名和id
     * */
    @GetMapping("/system/getSysName")
    public ResultType<SystemEntity> getSysName(@RequestParam int cid){
        return systemService.getSysName(cid);
    }

    @PostMapping("/system/getSysDescribeByCondition")
    public ResultType<SystemEntity> getSysDescribeByCondition(@RequestBody Map<String, Object> map){
        return systemService.getSysDescribeByCondition(map);
    }

    @GetMapping("/system/getSysDetail")
    public ResultType<SystemEntity>  getSysDetailById(@RequestParam int sysId){
        return systemService.getSysDetailById(sysId);
    }

    @RequestMapping("/system/iconUpload")
    @ResponseBody
    public ResultType<SystemEntity> iconUpload(@RequestParam("icon") MultipartFile content){
        return systemService.iconUpload(content);
    }

    @PostMapping("/system/addSystem")
    public ResultType<SystemEntity> addSystem(@RequestBody Map<String, Object> map){
        return systemService.addSystem(map);
    }
}
