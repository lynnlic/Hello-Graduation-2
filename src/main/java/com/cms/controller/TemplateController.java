package com.cms.controller;

import com.cms.entity.TemplateEntity;
import com.cms.service.TemplateService;
import com.cms.utils.ResultType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Map;
import java.util.UUID;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
public class TemplateController {
    @Autowired
    TemplateService templateService;

    @GetMapping("/template/getTemplateBySysid")
    public ResultType<TemplateEntity> getTemplateBySysid(@RequestParam int sysId){
        return templateService.getTemplateBySysid(sysId);
    }

    @PostMapping("/template/getTemplateByCondition")
    public ResultType<TemplateEntity> getTemplateByCondition(@RequestBody Map<String, Object> map){
        return templateService.getTemplateByCondition(map);
    }

    @RequestMapping("/template/fileUpload")
    @ResponseBody
    public ResultType<TemplateEntity> fileUpload(@RequestParam("file") MultipartFile file){
        return templateService.fileUpload(file);
    }

    @PostMapping("/template/loadLocalTemplate")
    public ResultType loadLocalTemplate(@RequestBody Map<String, Object> map){
        System.out.println(map.get("fileName"));
        return templateService.loadLocalTemplate(map);
    }

    @PostMapping("/template/addTemplate")
    public ResultType addTemplate(@RequestBody Map<String, Object> map){
        return templateService.addTemplate(map);
    }

    @GetMapping("/template/getTagsByTemplateId")
    public ResultType getTagsByTemplateId(@RequestParam("id") int id){
        return templateService.getTagsByTemplateId(id);
    }

    @PostMapping("/template/editTemplate")
    public ResultType editTemplate(@RequestBody Map<String, Object> map){
        return templateService.editTemplate(map);
    }
}
