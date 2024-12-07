package com.bite.book.controller;

import com.bite.book.constant.Constants;
import com.bite.book.model.Result;
import com.bite.book.model.UserInfo;
import com.bite.book.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", produces = "application/json")
    public Result login(String userName, String password, HttpSession session){

        //1. 校验参数
        //2. 验证密码是否正确
        //3. 返回响应结果
        if (!StringUtils.hasLength(userName)||!StringUtils.hasLength(password)){
            return Result.fail("用户名或密码为空");
        }
        //理论上应该从数据库中读取, 但是当前还没有学习, 先模拟校验
//        if (!"admin".equals(userName) || !"admin".equals(password)){
//           return "密码错误";
//        }
        //根据用户名称, 去数据库查询用户信息, 如果未查询到, 说明用户不存在
        //如果查询到用户信息, 比对密码是否正确
        UserInfo userInfo = userService.getUserInfoByName(userName);
        if (userInfo==null){
            return Result.fail("用户不存在");
        }
        //用户存在, 校验密码是否正确
        if (!password.equals(userInfo.getPassword())){
            return Result.fail("密码错误");
        }
        //正确的情况
        session.setAttribute(Constants.USER_SESSION_KEY,userInfo);
        return Result.success("");
    }
}
