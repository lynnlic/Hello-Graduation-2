package com.cms.service;

import com.cms.entity.SiteEntity;
import com.cms.utils.ResultType;

import java.util.Map;

public interface SiteService {
    ResultType<SiteEntity> addSite(Map<String, Object> map);
    ResultType<SiteEntity> getSiteByCondition(Map<String, Object> map);
    ResultType<SiteEntity> editSite(Map<String, Object> map);
}
