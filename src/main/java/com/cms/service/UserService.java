package com.cms.service;

import com.cms.entity.UserEntity;
import com.cms.utils.ResultType;

import java.util.Map;

public interface UserService {
    ResultType<UserEntity> addUser(Map<String, Object> map);
    ResultType<UserEntity> getUserByCondition(Map<String, Object> map);
    ResultType<UserEntity> editUser(Map<String, Object> map);
}
