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

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/user/getAllUser")
    public ResultType<UserEntity> getAllUser(@RequestParam int currentPage, @RequestParam int number){
        return userService.getAllUser(currentPage, number);
    }

    //构造器注入方式
    /*private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/person/save")
    public UserInfo save(@RequestParam String name){
        UserInfo userInfo = new UserInfo();
        userInfo.setName(name);
        if(userRepository.save(userInfo)){
            System.out.printf("用户对象：%s 保存成功！\n", userInfo);
        }

        return userInfo;
    }

    @GetMapping("/person/findAll")
    public Collection<UserInfo> findAllUser(){
        return userRepository.findAll();
    }

    @PostMapping("person/delete")
    public String delete(@RequestParam Integer id){
        //userRepository.
    }*/
}
