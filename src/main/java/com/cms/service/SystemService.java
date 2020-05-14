package com.cms.service;

import com.cms.entity.SystemEntity;
import com.cms.utils.ResultType;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface SystemService {
    ResultType<SystemEntity> getSysDetailById(int sysId);
    ResultType<SystemEntity> getSysName(int cid);
    ResultType<SystemEntity> iconUpload(MultipartFile content);
    ResultType<SystemEntity> addSystem(Map<String, Object> map);
    ResultType<SystemEntity> getSysDescribeByCondition(Map<String, Object> map);
}
