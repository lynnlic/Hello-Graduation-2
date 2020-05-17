package com.cms.controller;

import com.cms.entity.DataEntity;
import com.cms.service.DataService;
import com.cms.utils.ResultType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
public class DataController {
    @Autowired
    DataService dataService;

    @GetMapping("/content/getDataBySiteId")
    public ResultType<DataEntity> getDataBySiteName(@RequestParam int siteId){
        return dataService.getDataBySiteId(siteId);
    }

    @PostMapping("/content/getDataByCondition")
    public ResultType<DataEntity> getDataByCondition(@RequestBody Map<String, Object> map){
        return dataService.getDataByCondition(map);
    }

    @PostMapping("/content/loadLocalContent")
    public ResultType loadLocalContent(@RequestBody Map<String, Object> map){
        return dataService.loadLocalContent(map);
    }

    @RequestMapping("/content/contentUpload")
    @ResponseBody
    public ResultType<DataEntity> contentUpload(@RequestParam("content") MultipartFile content){
        return dataService.contentUpload(content);
    }

    @PostMapping("/content/addContent")
    public ResultType<DataEntity> addContent(@RequestBody Map<String, Object> map){
        return dataService.addContent(map);
    }

    @PostMapping("/content/updateContent")
    public ResultType<DataEntity> updateContent(@RequestBody Map<String, Object> map){
        return dataService.updateContent(map);
    }
}
