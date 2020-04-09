package com.cms.service;

import com.cms.entity.SystemEntity;
import com.cms.utils.ResultType;

public interface SystemService {
    ResultType<SystemEntity> getSysDescribe(int currentPage, int number);
    ResultType<SystemEntity> getSysDetailById(int sysId);
    ResultType<SystemEntity> getSysName();
}
