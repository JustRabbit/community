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
    @Insert("insert into user(name,account_id,token,gmt_Create,gmt_Modified) values(#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified})")
    void insert(User user);

    @Insert("insert into user(name,account_id,token,gmt_Create,gmt_Modified) values(#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified})")
    void insert01(User user);

    @Select("select * from user where token=#{token}")
    //当形参是一个数据类型 需要加注解
    User findByToken(@Param("token") String token);
}
