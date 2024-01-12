package com.mdd.auto_test_back.mapper;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mdd.auto_test_back.entity.User;

@SpringBootTest
public class UserMapperTest {
    @Autowired
    UserMapper userMapper;

    @Test
    void testFindAllUser() {
        List<User> userList = userMapper.findAllUser();
        System.out.println(userList);
    }

    @Test
    void testFindUserByName() {
        User user = userMapper.findUserByName("毛旦旦");
        System.out.println(user);
    }
}
