package com.pujiang.community.dto;

import lombok.Data;

/**
 *
 */
//超出两个参数建议封装成一个类
@Data
public class AccessTokenDTO {
    private  String client_id;
    private  String client_secret;
    private  String code;
    private  String redirect_uri;
    private  String state;
// alt+insert

}
