package com.cms.service.impl;

import com.cms.dao.UserDao;
import com.cms.entity.UserEntity;
import com.cms.service.UserService;
import com.cms.utils.ResultType;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public ResultType<UserEntity> getAllUser(int currentPage, int number) {
        PageHelper.startPage(currentPage, number);
        List<UserEntity> result = userDao.getAllUser();
        PageInfo<UserEntity> pageInfo = new PageInfo<>(result);

        ResultType resultType = new ResultType();
        resultType.setCode(200);
        resultType.setMsg("获取信息成功");
        resultType.setData(result);
        resultType.setTotal(pageInfo.getTotal());

        return resultType;
    }
}
