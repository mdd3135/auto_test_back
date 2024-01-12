package com.mdd.auto_test_back.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.mdd.auto_test_back.entity.User;

@Mapper
public interface UserMapper {
    List<User> findAllUser();

    User findUserByName(String name);

    void modPwd(String name, String pwd);

    List<User> findAllStu();

    void addUser(User user);

    void delAllStu();

    void delUserByName(String user);

    void modClassroom(String name, String classroom);

    void modNumber(String name, String number);
}
