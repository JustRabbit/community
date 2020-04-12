package com.pujiang.community.model;

import lombok.Data;

/***
 * lombok  data 自动生成set get equal tostring。。
 */
@Data
public class User {

  private Integer id;
  private String account_Id;
  private String name;
  private String token;
  private long gmt_Create;
  private long gmt_Modified;
  private String avatar_Url;


}
