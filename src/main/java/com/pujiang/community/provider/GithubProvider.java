package com.pujiang.community.provider;


import com.alibaba.fastjson.JSON;
import com.pujiang.community.dto.AccessTokenDTO;
import com.pujiang.community.dto.GithubUser;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class GithubProvider {
    //  OkHttp 的 POST请求
    public String getAccessToken(AccessTokenDTO accesstokenDTO){
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();
            RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accesstokenDTO));

            Request request = new Request.Builder()
                    .url("https://github.com/login/oauth/access_token")
                    .post(body)
                    .build();
            try (Response response = client.newCall(request).execute()) {
                String  string=response.body().string();

                // 将token分解
                String token=string.split("&")[0].split("=")[1];
                System.out.println(token+"这个是解析后的token");
                return token;

            } catch (Exception e) {
                e.printStackTrace();
            }
        return null;
    }

    //  OkHttp 的 Get请求
    public GithubUser getUser(String accessToken){
        OkHttpClient client =new OkHttpClient();
        System.out.println(accessToken);
        Request request=new Request.Builder()
               .url("https://api.github.com/user?access_token="+accessToken)
                .build();
        try {
            Response response=client.newCall(request).execute();
            String  string=response.body().string();
            System.out.println(string+"这是测试");
            GithubUser githubUser=JSON.parseObject(string,GithubUser.class);
            System.out.println(githubUser+"!@#");
            return githubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
