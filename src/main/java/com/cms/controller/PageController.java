package com.cms.controller;

import com.cms.entity.PageEntity;
import com.cms.service.PageService;
import com.cms.utils.ResultType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
public class PageController {
    @Autowired
    PageService pageService;

    @GetMapping("/page/getPageInfo")
    public ResultType<PageEntity> getPageInfo(@RequestParam int currentPage, @RequestParam int number){
        return pageService.getPageInfo(currentPage, number);
    }
}
