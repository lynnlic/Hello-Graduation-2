package com.cms.controller;

import com.cms.domain.UserInfo;
import com.cms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class UserController {

    //构造器注入方式
    private final UserRepository userRepository;

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

   /* @PostMapping("person/delete")
    public String delete(@RequestParam Integer id){
        //userRepository.
    }*/
}
