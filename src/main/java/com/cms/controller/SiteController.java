package com.cms.controller;

import com.cms.entity.SiteEntity;
import com.cms.service.SiteService;
import com.cms.utils.ResultType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
public class SiteController {
    @Autowired
    SiteService siteService;

    @GetMapping("/site/getAllSite")
    public ResultType<SiteEntity> getAllSite(@RequestParam int currentPage, @RequestParam int number){
        return siteService.getAllSite(currentPage, number);
    }
}
