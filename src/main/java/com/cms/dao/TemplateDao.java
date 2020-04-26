package com.cms.dao;

import com.cms.entity.TemplateEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TemplateDao {
    List<TemplateEntity> getTemplateBySysid(int sysId);
    List<TemplateEntity> getTemplateByCondition(int sysId, String templateName, int state);
    int addTemplate(int id, String name, String describe, String path, String tags, int sysId, int state);
}
