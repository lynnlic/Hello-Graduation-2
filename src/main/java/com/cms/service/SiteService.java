package com.cms.service;

import com.cms.entity.SiteEntity;
import com.cms.utils.ResultType;

public interface SiteService {
    ResultType<SiteEntity> getAllSite(int currentPage, int number);
}
