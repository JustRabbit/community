package com.pujiang.community.mapper;

import com.pujiang.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface QuestionMapper {
//
    @Insert("insert into question (id,title,description,gmt_create,gmt_modified,creator,tag)" +
            "values(#{id},#{title},#{description},#{gmt_Create},#{gmt_Modified},#{creator},#{tag})")
    public void create(Question question);

    @Select("select * from question")
    List<Question> list();


}
