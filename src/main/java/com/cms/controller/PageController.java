package com.cms.controller;

import com.cms.entity.PageEntity;
import com.cms.service.PageService;
import com.cms.utils.ResultType;
import com.cms.utils.ResultUtil;
import com.github.pagehelper.Page;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
public class PageController {
    @Autowired
    PageService pageService;

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

    @PostMapping("/page/uploadPageInfo")
    public ResultType<PageEntity> uploadPageInfo(@RequestBody Map<String, Object> map){
        return pageService.uploadPageInfo(map);
    }

    @PostMapping("/page/createNewPage")
    public ResultType<PageEntity> createNewPage(@RequestBody Map<String, Object> map){
        return pageService.createNewPage(map);
    }

    @PostMapping("/page/getPageByPageId")
    public ResultType<PageEntity> getPageByPageId(@RequestBody Map<String, Object> map){
        return pageService.getPageByPageId(map);
    }

    @PostMapping("/page/downloadFile")
    public ResultType<PageEntity> downloadFile(@RequestBody Map<String, Object> map, HttpServletResponse response) throws Exception {
        return pageService.downloadFile(map, response);
    }
}
