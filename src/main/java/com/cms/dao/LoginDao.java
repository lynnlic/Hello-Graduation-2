package com.cms.dao;

import com.cms.entity.UserEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoginDao {
    List<UserEntity> getUserByAccount(@Param("account") String account);
}
