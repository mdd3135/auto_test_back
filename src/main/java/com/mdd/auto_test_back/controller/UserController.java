package com.mdd.auto_test_back.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.mdd.auto_test_back.entity.User;
import com.mdd.auto_test_back.service.UserService;
import com.mdd.auto_test_back.util.Sha256;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/findAllUser")
    public List<User> findAllUser(@RequestParam Map<String, String> map) {
        return userService.findAllUser();
    }

    @PostMapping("/login")
    public User login(@RequestParam Map<String, String> map) {
        String name = map.get("name");
        String pwd = map.get("pwd");
        return userService.login(name, pwd);
    }

    @PostMapping("/modPwd")
    public User modPwd(@RequestParam Map<String, String> map) {
        String name = map.get("name");
        String oldPwd = map.get("oldPwd");
        String newPwd = map.get("newPwd");
        return userService.modPwd(name, oldPwd, newPwd);
    }

    @GetMapping("/findAllStu")
    public List<User> getMethodName(@RequestParam Map<String, String> map) {
        int count = Integer.parseInt(map.get("count"));
        int page = Integer.parseInt(map.get("page"));
        List<User> userList = userService.findAllStu();
        int start = (page - 1) * count;
        int end = page * count > userList.size() ? userList.size() : page * count;
        return userList.subList(start, end);
    }

    @PostMapping("/addStu")
    public User addStu(@RequestParam Map<String, String> map) {
        User user = new User(map);
        user.setPwd(Sha256.encode(user.getNumber()));
        user.setType(0);
        return userService.addUser(user);
    }

    @PostMapping("/delAllStu")
    public void delAllStu() {
        userService.delAllStu();
    }

    @PostMapping("/delUser")
    public void delUser(@RequestParam Map<String, String> map) {
        String name = map.get("name");
        userService.delUser(name);
    }

    @GetMapping("/getStuCount")
    public int getStuCount() {
        List<User> userList = userService.findAllStu();
        return userList.size();
    }

    @PostMapping("/resetPwd")
    public User resetPwd(@RequestParam Map<String, String> map) {
        String name = map.get("name");
        return userService.resetPwd(name);
    }

    @PostMapping("/modNumber")
    public User modNumber(@RequestParam Map<String, String> map) {
        String name = map.get("name");
        String number = map.get("number");
        return userService.modNumber(name, number);
    }

    @PostMapping("/modClassroom")
    public User modClassroom(@RequestParam Map<String, String> map) {
        String name = map.get("name");
        String classroom = map.get("classroom");
        return userService.modClassroom(name, classroom);
    }
}
