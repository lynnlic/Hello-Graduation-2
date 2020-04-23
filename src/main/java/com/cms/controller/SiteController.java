package com.cms.controller;

import com.cms.entity.SiteEntity;
import com.cms.service.SiteService;
import com.cms.utils.ResultType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
public class SiteController {
    @Autowired
    SiteService siteService;

    @PostMapping("/site/addSite")
    public ResultType<SiteEntity> addSite(@RequestBody Map<String,Object> map){
        return siteService.addSite(map);
    }

    @PostMapping("/site/getSiteByCondition")
    public ResultType<SiteEntity> getSiteByCondition(@RequestBody Map<String, Object> map){
        return siteService.getSiteByCondition(map);
    }
}
