package com.cms.dao;

import com.cms.entity.SiteEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SiteDao {
    List<SiteEntity> getAllSite();
}
