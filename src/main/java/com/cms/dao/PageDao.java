package com.cms.dao;

import com.cms.entity.PageEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PageDao {
    List<PageEntity> getPageInfo();
    List<PageEntity> getPagesBySysid();
}
