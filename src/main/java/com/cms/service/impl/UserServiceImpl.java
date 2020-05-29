package com.cms.service.impl;

import com.cms.dao.IdDao;
import com.cms.dao.UserDao;
import com.cms.entity.IDEntity;
import com.cms.entity.UserEntity;
import com.cms.service.UserService;
import com.cms.utils.ResultType;
import com.cms.utils.ResultUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.regexp.internal.REUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;
    @Autowired
    IdDao idDao;

    @Override
    public ResultType<UserEntity> addUser(Map<String, Object> map) {
        //获得表中的id号，使得序号自增
        List<IDEntity> id = idDao.getID("user");
        //新的id
        int newId = id.get(0).getId()+1;

        String name = map.get("name").toString();
        String account = map.get("account").toString();
        String password = map.get("password").toString();
        int parentId = Integer.parseInt(map.get("parentId").toString());
        int state = Integer.parseInt(map.get("state").toString());
        //返回值
        int result = userDao.addUser(newId, name, account, password,parentId, state);

        ResultType resultType;
        if(result==1){
            resultType = ResultUtil.success(201, "添加成功",null);
            //设置新的id号
            idDao.updateID(newId,"user");
        } else {
            resultType = ResultUtil.error(202, "添加失败");
        }
        return resultType;
    }

    @Override
    public ResultType<UserEntity> getUserByCondition(Map<String, Object> map) {
        //条件值
        String account = map.get("account")==null?null:map.get("account").toString();
        String name = map.get("name")==null?null:map.get("name").toString();
        int parentId = Integer.parseInt(map.get("parentId").toString());
        int currentPage = Integer.parseInt(map.get("currentPage").toString());
        int number = Integer.parseInt(map.get("number").toString());

        PageHelper.startPage(currentPage, number);
        List<UserEntity> result = userDao.getUserByCondition(account, name, parentId);
        PageInfo<UserEntity> pageInfo = new PageInfo<>(result);

        ResultType resultType = new ResultType();
        resultType.setCode(200);
        resultType.setMsg("获取信息成功");
        resultType.setData(result);
        resultType.setTotal(pageInfo.getTotal());

        return resultType;
    }

    @Override
    public ResultType<UserEntity> editUser(Map<String, Object> map) {
        //条件值
        String name = map.get("name")==null?null:map.get("name").toString();
        String account = map.get("account")==null?null:map.get("account").toString();
        int state = map.get("state")==null?-1:Integer.parseInt(map.get("state").toString());
        String password = map.get("password")==null?null:map.get("password").toString();
        int id = Integer.parseInt(map.get("id").toString());

        int result = userDao.editUser(name, account, state, password, id);
        if(result==1){
            return ResultUtil.success(207,"修改成功", null);
        } else {
            return ResultUtil.error(208, "修改失败");
        }
    }
}
