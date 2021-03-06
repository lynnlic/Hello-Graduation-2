package com.cms.dao;

import com.cms.entity.TemplateEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TemplateDao {
    List<TemplateEntity> getTemplateBySysid(int sysId);
    List<TemplateEntity> getTemplateByCondition(int sysId, String templateName, int state, int parentId);
    int addTemplate(int id, String name, String describe, String path, String tags, int sysId, int state, int creatorId);
    List<TemplateEntity> getTagsByTemplateId(int id);
    int editTemplate(int templateId, String filePath, String tags, String templateName, String describe, int state);
}
