package com.pujiang.community.controller;
import com.pujiang.community.dto.AccessTokenDTO;
import com.pujiang.community.dto.GithubUser;
import com.pujiang.community.mapper.UserMapper;
import com.pujiang.community.model.User;
import com.pujiang.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Autowired
    private UserMapper userMapper;

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.url}")
    private String redirectUrl;

    /**
     *
     * @param code 授权码
     * @param state 随机生成的，为了方便跨转使用
     * @param response 代表服务器的响应
     * @return
     */
    @GetMapping("/callback")
    public  String callback(@RequestParam(name = "code") String code,
                            @RequestParam(name = "state")String state,
                            HttpServletResponse response
                            ){
        System.out.println("code"+code+"STATE"+state);
        /**

         * 当点击 确认授权时，就会从github返回一个code （授权码）
         * 使用该授权码，去github得到令牌
         */
        AccessTokenDTO accesstokenDTO=getAccessTokenDTO(code,state);
        /**
         * 使用 授权码 得到令牌getAccessToKen()
         */
        String accessToken=githubProvider.getAccessToken(accesstokenDTO);
        System.out.println(accessToken+"这是accessToken");

        /**
         * 再次 通过令牌 访问github得到该用户的信息
         */
        GithubUser githubUser=githubProvider.getUser(accessToken);
        System.out.println(githubUser);
        if (githubUser!=null && githubUser.getId()!= null){
            User user =getUser(githubUser);
            userMapper.insert(user);
            //登录成功，写cookie和session

            response.addCookie(new Cookie("token",user.getToken()));


            //重定向
            return "redirect:/";


        }else {
            //登录失败 重新登录
            return "redirect:/";
        }
    }


    //从Github 得到的 授权码 code
    public AccessTokenDTO getAccessTokenDTO(String code,String state){
        AccessTokenDTO accesstokenDTO = new AccessTokenDTO();
        accesstokenDTO.setClient_id(clientId);
        accesstokenDTO.setClient_secret(clientSecret);
        accesstokenDTO.setCode(code);
        accesstokenDTO.setRedirect_uri(redirectUrl);
        accesstokenDTO.setState(state);
        System.out.println(accesstokenDTO+"______________");
        return accesstokenDTO;
    }



/**
 * 将 github的信息，存储到 User类中
 * @param githubUser 授权的github账户信息
 * @return User
 */
    public User getUser(GithubUser githubUser){
        User user =new User();
        user.setToken(UUID.randomUUID().toString());
        user.setName(githubUser.getName());
        user.setAccount_Id(String.valueOf(githubUser.getId()));
        user.setGmt_Create(System.currentTimeMillis());
        user.setGmt_Modified(user.getGmt_Create());
        user.setAvatar_Url(githubUser.getAvatar_url());
        return user;
    }
}
