package com.cms.dao;

import com.cms.entity.PageEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PageDao {
    List<PageEntity> getPageInfo();
    List<PageEntity> getPagesBySysid(int sysId);
    List<PageEntity> getPagesByCondition(String name, int sysId);
    int deletePage(int pageId);
}
