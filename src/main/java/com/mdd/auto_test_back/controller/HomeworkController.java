package com.mdd.auto_test_back.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mdd.auto_test_back.entity.Homework;
import com.mdd.auto_test_back.entity.HomeworkItem;
import com.mdd.auto_test_back.service.HomeworkService;

@RestController
public class HomeworkController {
    @Autowired
    private HomeworkService homeworkService;

    @GetMapping("/getAllHomework")
    public List<Homework> getAllHomework(@RequestParam Map<String, String> map) {
        int count = Integer.parseInt(map.get("count"));
        int page = Integer.parseInt(map.get("page"));
        List<Homework> homeworks = homeworkService.getAllHomework();
        int start = (page - 1) * count;
        int end = page * count > homeworks.size() ? homeworks.size() : page * count;
        return homeworks.subList(start, end);
    }

    @PostMapping("/addHomework")
    public Homework addHomework(@RequestParam Map<String, String> map) {
        String startTime = map.get("startTime");
        String deadline = map.get("deadline");
        String homeworkName = map.get("homeworkName");
        String item = map.get("item");
        int count = Integer.parseInt(map.get("count"));
        return homeworkService.addHomework(startTime, deadline, homeworkName, item, count);
    }

    @GetMapping("/getHomeworkItemByHomeworkId")
    public List<HomeworkItem> getHomeworkItemByHomeworkId(@RequestParam Map<String, String> map) {
        int homeworkId = Integer.parseInt(map.get("homeworkId"));
        return homeworkService.getHomeworkItemByHomeworkId(homeworkId);
    }

    @GetMapping("/getHomeworkCount")
    public int getHomeworkCount() {
        return homeworkService.getHomeworkCount();
    }

    @PostMapping("/removeHomeworkById")
    public void removeHomeworkById(@RequestParam Map<String, String> map) {
        int id = Integer.parseInt(map.get("id"));
        homeworkService.removeHomeworkById(id);
    }

    @GetMapping("/getHomeworkByHomeworkId")
    public Homework getHomeworkById(@RequestParam Map<String, String> map) {
        int id = Integer.parseInt(map.get("id"));
        return homeworkService.getHomeworkById(id);
    }
}
