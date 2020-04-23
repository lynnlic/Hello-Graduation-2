package com.cms.dao;

import com.cms.entity.TemplateEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TemplateDao {
    List<TemplateEntity> getTemplateBySysid(int sysId);
    List<TemplateEntity> getTemplateByCondition(int sysId, String templateName, int state);
}
