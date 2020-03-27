package com.cms.entity;

import java.sql.Date;

public class SystemEntity {
    private int sysId;
    private String sysName;
    private String sysCopyRight;
    private String sysUrl;
    private String sysSaveName;
    private String sysPhone;
    private String sysEmail;
    private Date sysCreateTime;

    public SystemEntity() {
    }

    public SystemEntity(int sysId, String sysName, String sysCopyRight, String sysUrl, String sysSaveName, String sysPhone, String sysEmail, Date sysCreateTime) {
        this.sysId = sysId;
        this.sysName = sysName;
        this.sysCopyRight = sysCopyRight;
        this.sysUrl = sysUrl;
        this.sysSaveName = sysSaveName;
        this.sysPhone = sysPhone;
        this.sysEmail = sysEmail;
        this.sysCreateTime = sysCreateTime;
    }

    public int getSysId() {
        return sysId;
    }

    public void setSysId(int sysId) {
        this.sysId = sysId;
    }

    public String getSysName() {
        return sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName;
    }

    public String getSysCopyRight() {
        return sysCopyRight;
    }

    public void setSysCopyRight(String sysCopyRight) {
        this.sysCopyRight = sysCopyRight;
    }

    public String getSysUrl() {
        return sysUrl;
    }

    public void setSysUrl(String sysUrl) {
        this.sysUrl = sysUrl;
    }

    public String getSysSaveName() {
        return sysSaveName;
    }

    public void setSysSaveName(String sysSaveName) {
        this.sysSaveName = sysSaveName;
    }

    public String getSysPhone() {
        return sysPhone;
    }

    public void setSysPhone(String sysPhone) {
        this.sysPhone = sysPhone;
    }

    public String getSysEmail() {
        return sysEmail;
    }

    public void setSysEmail(String sysEmail) {
        this.sysEmail = sysEmail;
    }

    public Date getSysCreateTime() {
        return sysCreateTime;
    }

    public void setSysCreateTime(Date sysCreateTime) {
        this.sysCreateTime = sysCreateTime;
    }
}
