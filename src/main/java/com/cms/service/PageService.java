package com.cms.service;


import com.cms.entity.PageEntity;
import com.cms.utils.ResultType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public interface PageService {
    ResultType<PageEntity> getPagesBySysid(int sysId);
    ResultType<PageEntity> getPagesByCondition(Map<String, Object> map);
    ResultType<PageEntity> deletePage(Map<String, Object> map);
    ResultType<PageEntity> uploadPageInfo(Map<String, Object> map);
    ResultType<PageEntity> createNewPage(Map<String, Object> map);
    ResultType<PageEntity> getPageByPageId(Map<String, Object> map);
    ResultType<PageEntity> downloadFile(Map<String, Object> map, HttpServletResponse response) throws Exception;
    ResultType<PageEntity> uploadEditPageInfo(Map<String, Object> map);
}
