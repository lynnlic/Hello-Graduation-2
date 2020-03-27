package com.cms.service.impl;

import com.cms.dao.LoginDao;
import com.cms.entity.UserEntity;
import com.cms.service.LoginService;
import com.cms.utils.ResultType;
import com.cms.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    LoginDao loginDao;

    @Override
    public ResultType<UserEntity> login(String account, String password) {
        List<UserEntity> user = loginDao.getUserByAccount(account);
        ResultType resultType = new ResultType();
        if(user.size()==0){//账号不存在
            resultType = ResultUtil.error(2, "账号不存在");
        } else {
            String orignPassword = user.get(0).getPassword();
            if(orignPassword.equals(password)){//密码匹配
                int status = user.get(0).getState();
                switch (status){
                    //禁用
                    case 0:resultType = ResultUtil.error(3,"该账号不可用");
                        System.out.printf("用户%s登陆失败,该账号已禁用\n",user.get(0).getAccount());
                        break;
                    //超级管理员
                    case 1:resultType = ResultUtil.success(0, "登陆成功", user.get(0));
                        System.out.printf("用户%s登陆成功\n",user.get(0).getAccount());
                        break;
                    //普通用户
                    case 2:resultType = ResultUtil.success(0, "登陆成功", user.get(0));
                        System.out.printf("用户%s登陆成功\n",user.get(0).getAccount());
                        break;
                    case -1:resultType = ResultUtil.error(-1,"该账号不可用");
                        System.out.printf("用户%s登陆失败,未知错误\n",user.get(0).getAccount());
                        break;
                }

            } else {//密码不匹配
                resultType = ResultUtil.error(1,"账号或密码错误");
                System.out.printf("用户%s登陆失败,账号或密码错误\n",user.get(0).getAccount());
            }
        }
        return resultType;
    }
}
