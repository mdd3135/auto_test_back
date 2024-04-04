package com.mdd.auto_test_back.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mdd.auto_test_back.entity.User;
import com.mdd.auto_test_back.mapper.UserMapper;
import com.mdd.auto_test_back.util.Sha256;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> findAllUser() {
        return userMapper.findAllUser();
    }

    public User findUserById(int id) {
        return userMapper.findUserById(id);
    }

    public User login(String name, String pwd) {
        User user = userMapper.findUserByName(name);
        if (user == null) {
            return null;
        } else if (user.getPwd().equals(pwd) == false) {
            return null;
        }
        return user;
    }

    public User modPwd(String name, String oldPwd, String newPwd) {
        User user = userMapper.findUserByName(name);
        if (user == null || user.getPwd().equals(oldPwd) == false) {
            return null;
        } else {
            userMapper.modPwd(name, newPwd);
            return userMapper.findUserByName(name);
        }
    }

    public List<User> findAllStu() {
        return userMapper.findAllStu();
    }

    public User addUser(User user) {
        User u = userMapper.findUserByName(user.getName());
        if (u != null) {
            return null;
        }
        userMapper.addUser(user);
        return userMapper.findUserByName(user.getName());
    }

    public void delAllStu() {
        userMapper.delAllStu();
    }

    public void delUser(String name) {
        userMapper.delUserByName(name);
    }

    public User resetPwd(String name) {
        User user = userMapper.findUserByName(name);
        if (user == null) {
            return null;
        }
        String newPwd = Sha256.encode(user.getNumber());
        userMapper.modPwd(name, newPwd);
        return userMapper.findUserByName(name);
    }

    public User modClassroom(String name, String classroom) {
        User user = userMapper.findUserByName(name);
        if (user == null) {
            return null;
        }
        userMapper.modClassroom(name, classroom);
        return userMapper.findUserByName(name);
    }

    public User modNumber(String name, String number) {
        User user = userMapper.findUserByName(name);
        if (user == null) {
            return null;
        }
        userMapper.modNumber(name, number);
        return userMapper.findUserByName(name);
    }

}
