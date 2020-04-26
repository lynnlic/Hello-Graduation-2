package com.cms.service;

import com.cms.entity.TemplateEntity;
import com.cms.utils.ResultType;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface TemplateService {
    ResultType<TemplateEntity> getTemplateBySysid(int sysId);
    ResultType<TemplateEntity> getTemplateByCondition(Map<String, Object> map);
    ResultType fileUpload(MultipartFile file);
    ResultType loadLocalTemplate(Map<String, Object> map);
    ResultType addTemplate(Map<String, Object> map);
}
