package com.cms.dao;

import com.cms.entity.SystemEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SystemDao {
    List<SystemEntity> getSysDescribe();
    List<SystemEntity> getSysDetailById(int sysId);
    List<SystemEntity> getSysName(int cid);
    int addSystem(int id, String copyRight, String name, String iconPath, String url, String savaName, String phone, String email, int creatorId);
    List<SystemEntity> getSysDescribeByCondition(String sysName, String url, int parentId);
}
