package com.cms.service;


import com.cms.entity.PageEntity;
import com.cms.utils.ResultType;

public interface PageService {
    ResultType<PageEntity> getPageInfo(int currentPage, int number);
}
