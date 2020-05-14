package com.cms.entity;

import java.sql.Date;

public class SystemEntity {
    private int sysId;
    private String sysName;
    private String sysCopyRight;
    private String sysIconPath;
    private String sysUrl;
    private String sysSaveName;
    private String sysPhone;
    private String sysEmail;
    private int sysCreatorId;
    private Date sysCreateTime;

    public SystemEntity() {
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

    public int getSysCreatorId() {
        return sysCreatorId;
    }

    public void setSysCreatorId(int sysCreatorId) {
        this.sysCreatorId = sysCreatorId;
    }

    public Date getSysCreateTime() {
        return sysCreateTime;
    }

    public void setSysCreateTime(Date sysCreateTime) {
        this.sysCreateTime = sysCreateTime;
    }

    public String getSysIconPath() {
        return sysIconPath;
    }

    public void setSysIconPath(String sysIconPath) {
        this.sysIconPath = sysIconPath;
    }
}
