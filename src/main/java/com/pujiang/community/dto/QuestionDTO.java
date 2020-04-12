package com.pujiang.community.dto;

import com.pujiang.community.model.User;
import lombok.Data;

/**
 * zai question he user   之间搭建 桥梁（链表查询的概念
 */
@Data
public class QuestionDTO {
    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Long gmt_Create;
    private Long gmt_Modified;
    private Long creator;
    private Integer view_Count;
    private Integer comment_Count;
    private Integer like_Count;
    private User user;
}
