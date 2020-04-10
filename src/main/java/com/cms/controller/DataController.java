package com.cms.controller;

import com.cms.entity.DataEntity;
import com.cms.service.DataService;
import com.cms.utils.ResultType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
public class DataController {
    @Autowired
    DataService dataService;

    @GetMapping("/content/getData")
    public ResultType<DataEntity> getAllData(@RequestParam int currentPage, @RequestParam int number){
        return dataService.getAllData(currentPage, number);
    }

    @GetMapping("/content/getDataBySiteName")
    public ResultType<DataEntity> getDataBySiteName(@RequestParam String siteName){
        return dataService.getDataBySiteName(siteName);
    }
}
