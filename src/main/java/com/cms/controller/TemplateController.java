package com.cms.controller;

import com.cms.entity.TemplateEntity;
import com.cms.service.TemplateService;
import com.cms.utils.ResultType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
public class TemplateController {
    @Autowired
    TemplateService templateService;

    @GetMapping("/template/getAllTemplate")
    public ResultType<TemplateEntity> getAllTemplate(@RequestParam int currentPage, @RequestParam int number){
        return templateService.getAllTemplate(currentPage, number);
    }
}