package com.pujiang.community.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class HelloController {
    @GetMapping("/hello")
    public String hello(@RequestParam(name = "name") String name, Model model) {
//    把值放入model 中
        model.addAttribute("name", name);
//        src/main/resources/templates/hello.html
        return "hello";
    }
}
