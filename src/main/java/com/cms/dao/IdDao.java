package com.cms.dao;

import com.cms.entity.IDEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IdDao {
    List<IDEntity> getID(String tableName);
    int updateID(int id, String tableName);
}
