package com.pujiang.community.model;

import lombok.Data;

/***
 *
 *
 */
@Data
public class Question {
    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Long gmt_Create;
    private Long gmt_Modified;
    private Integer creator;
    private Integer view_Count;
    private Integer comment_Count;
    private Integer like_Count;

}
