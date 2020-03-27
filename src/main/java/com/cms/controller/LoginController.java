package com.cms.controller;

import com.cms.entity.UserEntity;
import com.cms.service.LoginService;
import com.cms.utils.ResultType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping("/login")
    public ResultType<UserEntity> login(@RequestBody Map<String,Object> map){
        String account = map.get("account").toString();
        String password = map.get("password").toString();
        return loginService.login(account, password);
    }
}
