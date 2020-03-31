package com.cms.service;

import com.cms.entity.DataEntity;
import com.cms.utils.ResultType;

public interface DataService {
    ResultType<DataEntity> getAllData(int currentPage, int number);
}
