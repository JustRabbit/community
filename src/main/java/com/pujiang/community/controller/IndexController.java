package com.pujiang.community.controller;


import com.pujiang.community.mapper.UserMapper;
import com.pujiang.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;


    @GetMapping("/")
    public String index(HttpServletRequest request){
        //服务器重置后 用户断开后 用户需要重新登录
        Cookie[] cookies = request.getCookies();
        if (cookies!=null){
        for (Cookie cookie:cookies){
            if (cookie.getName().equals("token")){
                String token = cookie.getValue();
                User user=userMapper.findByToken(token);
                if(user!=null) {
                    request.getSession().setAttribute("user",user);
                }
                break;
            }
        }
        }
        else {
            System.out.println("cookies is null");
        }
        return "index"; }
}
