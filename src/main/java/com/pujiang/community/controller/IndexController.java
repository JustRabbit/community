package com.pujiang.community.controller;


import com.pujiang.community.dto.QuestionDTO;
import com.pujiang.community.mapper.UserMapper;
import com.pujiang.community.model.User;
import com.pujiang.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionServicess;

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model) {
        //服务器重置后 用户断开后 用户需要重新登录
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }


        //插叙数据库列表内容放在前台中
        List<QuestionDTO> questionList=questionServicess.list();
        model.addAttribute("questions",questionList);

        return "index";
    }
}
