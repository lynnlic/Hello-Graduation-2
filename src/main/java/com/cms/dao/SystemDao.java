package com.cms.dao;

import com.cms.entity.SystemEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SystemDao {
    List<SystemEntity> getSysDescribe();
    List<SystemEntity> getSysDetailById(int sysId);
}
