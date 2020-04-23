package com.cms.dao;

import com.cms.entity.SiteEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SiteDao {
    List<SiteEntity> getSitesBySysid(int sysId);
    int addSite(int id, String name, String url, int sysId, String describe);
    List<SiteEntity> getSiteByCondition(int sysId, String siteName);
}
