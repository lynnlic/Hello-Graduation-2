package com.cms.entity;

import java.sql.Date;

public class TemplateEntity {
    private int templateId;
    private String templateName;
    private String describe;
    private String templatePath;
    private String templateTags;
    private int creatorId;
    private Date createTime;
    private int state;
    private SystemEntity systemEntity;

    public TemplateEntity() {
    }

    public int getTemplateId() {
        return templateId;
    }

    public void setTemplateId(int templateId) {
        this.templateId = templateId;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getTemplatePath() {
        return templatePath;
    }

    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }

    public int getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getTemplateTags() {
        return templateTags;
    }

    public void setTemplateTags(String templateTags) {
        this.templateTags = templateTags;
    }

    public SystemEntity getSystemEntity() {
        return systemEntity;
    }

    public void setSystemEntity(SystemEntity systemEntity) {
        this.systemEntity = systemEntity;
    }
}
