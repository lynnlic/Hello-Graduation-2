package com.cms.entity;

import java.sql.Date;

public class DataEntity {
    private int dataId;
    private String title;
    private String path;
    private String describe;
    private String tag;
    private int creatorId;
    private Date createTime;
    private SiteEntity siteEntity;

    public int getDataId() {
        return dataId;
    }

    public void setDataId(int dataId) {
        this.dataId = dataId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
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

    public SiteEntity getSiteEntity() {
        return siteEntity;
    }

    public void setSiteEntity(SiteEntity siteEntity) {
        this.siteEntity = siteEntity;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
