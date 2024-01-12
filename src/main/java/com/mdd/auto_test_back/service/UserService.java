package com.mdd.auto_test_back.service;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.mdd.auto_test_back.entity.User;

@Repository
public interface UserService {

    List<User> findAllUser();

    User login(String name, String pwd);

    User modPwd(String name, String oldPwd, String newPwd);

    List<User> findAllStu();

    User addUser(User user);

    void delAllStu();

    void delUser(String name);

    User resetPwd(String name);

    User modClassroom(String name, String classroom);

    User modNumber(String name, String number);
}
