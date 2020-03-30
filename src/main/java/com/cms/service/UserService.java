package com.cms.service;

import com.cms.entity.UserEntity;
import com.cms.utils.ResultType;

public interface UserService {
    ResultType<UserEntity> getAllUser(int currentPage, int number);
}
