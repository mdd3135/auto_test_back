package com.mdd.auto_test_back.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mdd.auto_test_back.entity.Homework;
import com.mdd.auto_test_back.service.HomeworkService;

@RestController
public class HomeworkController {
    @Autowired
    HomeworkService homeworkService;

    @GetMapping("/getAllHomework")
    List<Homework> getAllHomework(@RequestParam Map<String, String> map) {
        int count = Integer.parseInt(map.get("count"));
        int page = Integer.parseInt(map.get("page"));
        List<Homework> homeworks = homeworkService.getAllHomework();
        int start = (page - 1) * count;
        int end = page * count > homeworks.size() ? homeworks.size() : page * count;
        return homeworks.subList(start, end);
    }
}
