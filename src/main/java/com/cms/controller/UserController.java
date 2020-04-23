package com.cms.controller;

import com.cms.domain.UserInfo;
import com.cms.entity.UserEntity;
import com.cms.repository.UserRepository;
import com.cms.service.UserService;
import com.cms.utils.ResultType;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/user/addUser")
    public ResultType<UserEntity> addUser(@RequestBody Map<String, Object> map){
        return userService.addUser(map);
    }

    @PostMapping("/user/getUserByCondition")
    public ResultType<UserEntity> getUserByCondition(@RequestBody Map<String, Object> map){
        return userService.getUserByCondition(map);
    }
}
