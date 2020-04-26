package com.cms.controller;

import com.cms.entity.PageEntity;
import com.cms.service.PageService;
import com.cms.utils.ResultType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
public class PageController {
    @Autowired
    PageService pageService;

    @GetMapping("/page/getPageInfo")
    public ResultType<PageEntity> getPageInfo(@RequestParam int currentPage, @RequestParam int number){
        return pageService.getPageInfo(currentPage, number);
    }

    @GetMapping("/page/getPagesBySysid")
    public ResultType<PageEntity> getPagesBySysid(@RequestParam int sysId){
        return pageService.getPagesBySysid(sysId);
    }

    @PostMapping("/page/getPagesByCondition")
    public ResultType<PageEntity> getPagesByCondition(@RequestBody Map<String, Object> map){
        return pageService.getPagesByCondition(map);
    }

    @PostMapping("/page/deletePage")
    public ResultType<PageEntity> deletePage(@RequestBody Map<String, Object> map){
        return pageService.deletePage(map);
    }
}
