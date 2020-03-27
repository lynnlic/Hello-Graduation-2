package com.cms.entity;

import java.sql.Date;

public class PageEntity {
    private int pageId;
    private String pageName;
    private String fileName;
    private String pagePath;
    private int siteId;
    private int templateId;
    private int sysId;
    private int creatorId;
    private Date createTime;

    public PageEntity() {
    }

    public int getPageId() {
        return pageId;
    }

    public void setPageId(int pageId) {
        this.pageId = pageId;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getPagePath() {
        return pagePath;
    }

    public void setPagePath(String pagePath) {
        this.pagePath = pagePath;
    }

    public int getSiteId() {
        return siteId;
    }

    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }

    public int getTemplateId() {
        return templateId;
    }

    public void setTemplateId(int templateId) {
        this.templateId = templateId;
    }

    public int getSysId() {
        return sysId;
    }

    public void setSysId(int sysId) {
        this.sysId = sysId;
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

    public PageEntity(int pageId, String pageName, String fileName, String pagePath, int siteId, int templateId, int sysId, int creatorId, Date createTime) {
        this.pageId = pageId;
        this.pageName = pageName;
        this.fileName = fileName;
        this.pagePath = pagePath;
        this.siteId = siteId;
        this.templateId = templateId;
        this.sysId = sysId;
        this.creatorId = creatorId;
        this.createTime = createTime;
    }
}
