package com.mdd.auto_test_back.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.mdd.auto_test_back.entity.User;

@Mapper
public interface UserMapper {
    @Select("select * from user;")
    List<User> findAllUser();

    @Select("select * from user where name=#{name};")
    User findUserByName(String name);

    @Select("select * from user where id=#{id}")
    User findUserById(int id);

    @Update("update user set pwd=#{pwd} where name=#{name};")
    void modPwd(String name, String pwd);

    @Select("select * from user where type=0;")
    List<User> findAllStu();

    @Insert("insert into user (name, number, pwd, classroom, type) values (#{name}, #{number}, #{pwd}, #{classroom}, #{type});")
    void addUser(User user);

    @Delete("delete from user where type=0;")
    void delAllStu();

    @Delete("delete from user where name=#{name};")
    void delUserByName(String user);

    @Update("update user set classroom=#{classroom} where name=#{name};")
    void modClassroom(String name, String classroom);

    @Update("update user set number=#{number} where name=#{name};")
    void modNumber(String name, String number);
}
