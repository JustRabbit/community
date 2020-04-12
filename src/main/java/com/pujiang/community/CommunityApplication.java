package com.pujiang.community;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class CommunityApplication {
//右键运行    即可运行spring项目  启动项目类
    //  原理；使用spring内部的tomcat
   public static void main(String[] args) {
        SpringApplication.run(CommunityApplication.class, args);
    }
}
