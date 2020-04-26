package com.cms.service;


import com.cms.entity.PageEntity;
import com.cms.utils.ResultType;

import java.util.Map;

public interface PageService {
    ResultType<PageEntity> getPageInfo(int currentPage, int number);
    ResultType<PageEntity> getPagesBySysid(int sysId);
    ResultType<PageEntity> getPagesByCondition(Map<String, Object> map);
    ResultType<PageEntity> deletePage(Map<String, Object> map);
}
