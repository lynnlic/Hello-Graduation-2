package com.cms.dao;

import com.cms.entity.DataEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataDao {
    List<DataEntity> getData();
    List<DataEntity> getDataBySiteName(String siteName);
}
