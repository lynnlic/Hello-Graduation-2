package com.cms.dao;

import com.cms.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    int addUser(int id, String name, String account, String password, int parentId, int state);
    List<UserEntity> getUserByCondition(String account, String name, int parentId);
    int editUser(String name, String account, int state, String password, int id);
}
