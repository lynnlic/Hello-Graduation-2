package com.cms.entity;

import java.sql.Date;

public class SiteEntity {
    private int siteId;
    private String siteName;
    private String sysId;
    private String siteDescribe;
    private SystemEntity systemEntity;
    private Date siteCreateTime;

    public SiteEntity() {
    }

    public SiteEntity(int siteId, String siteName, String sysId, String siteDescribe, Date siteCreateTime) {
        this.siteId = siteId;
        this.siteName = siteName;
        this.sysId = sysId;
        this.siteDescribe = siteDescribe;
        this.siteCreateTime = siteCreateTime;
    }

    public int getSiteId() {
        return siteId;
    }

    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getSysId() {
        return sysId;
    }

    public void setSysId(String sysId) {
        this.sysId = sysId;
    }

    public String getSiteDescribe() {
        return siteDescribe;
    }

    public void setSiteDescribe(String siteDescribe) {
        this.siteDescribe = siteDescribe;
    }

    public SystemEntity getSystemEntity() {
        return systemEntity;
    }

    public void setSystemEntity(SystemEntity systemEntity) {
        this.systemEntity = systemEntity;
    }

    public Date getSiteCreateTime() {
        return siteCreateTime;
    }

    public void setSiteCreateTime(Date siteCreateTime) {
        this.siteCreateTime = siteCreateTime;
    }
}
