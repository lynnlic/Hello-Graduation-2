package com.cms.service;

import com.cms.entity.TemplateEntity;
import com.cms.utils.ResultType;

public interface TemplateService {
    ResultType<TemplateEntity> getAllTemplate(int currentPage, int number);
    ResultType<TemplateEntity> getTemplateBySysid(int sysId);
}
