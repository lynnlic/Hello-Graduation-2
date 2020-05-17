package com.cms.service;

import com.cms.entity.DataEntity;
import com.cms.utils.ResultType;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface DataService {
    ResultType<DataEntity> getDataBySiteId(int siteId);
    ResultType<DataEntity> getDataByCondition(Map<String, Object> map);
    ResultType loadLocalContent(Map<String, Object> map);
    ResultType<DataEntity> contentUpload(MultipartFile content);
    ResultType<DataEntity> addContent(Map<String, Object> map);
    ResultType<DataEntity> updateContent(Map<String, Object> map);
}
