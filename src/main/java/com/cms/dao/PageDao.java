package com.cms.dao;

import com.cms.entity.IDEntity;
import com.cms.entity.PageEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PageDao {
    List<PageEntity> getPagesBySysid(int sysId);
    List<PageEntity> getPagesByCondition(String name, int sysId);
    int deletePage(int pageId);
    int addPage(int newId, String name, String pageFileName, String path, int siteId, int templateId, int sysId, int state, int cId);
    int addPageDataInfo(int pdId, int did, int newId);
    List<PageEntity> getPageinfoByPid(int id);
    List<PageEntity> getPageByPageId(int pageId);
    int updataState(int pageId);
}
