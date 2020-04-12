package com.pujiang.community.mapper;


import com.pujiang.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface UserMapper {
    @Insert("insert into user(name,account_id,token,gmt_Create,gmt_Modified,avatar_url) values(#{name},#{account_Id},#{token},#{gmt_Create},#{gmt_Modified},#{avatar_Url})")
    void insert(User user);

    @Select("select * from user where token=#{token}")
    //当形参是一个数据类型 需要加注解
    User findByToken(@Param("token") String token);

    //联表查询 用的    question表和user表
    @Select("select * from user where id = #{creator}")
    User findById(@Param("creator") Integer creator);





}
