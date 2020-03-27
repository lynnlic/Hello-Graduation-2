package com.cms.service;

import com.cms.entity.UserEntity;
import com.cms.utils.ResultType;

public interface LoginService {
    ResultType<UserEntity> login(String account, String password);
}
