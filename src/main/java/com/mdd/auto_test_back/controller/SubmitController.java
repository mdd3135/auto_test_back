package com.mdd.auto_test_back.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mdd.auto_test_back.entity.Result;
import com.mdd.auto_test_back.service.SubmitService;

@RestController
public class SubmitController {
    @Autowired
    private SubmitService submitService;

    @GetMapping("/getSubmitCountByUserIdAndHomeworkId")
    public int getSubmitCountByUserIdAndHomeworkId(@RequestParam Map<String, String> map) {
        int userId = Integer.parseInt(map.get("userId"));
        int homeworkId = Integer.parseInt(map.get("homeworkId"));
        return submitService.getSubmitCountByUserIdAndHomeworkId(userId, homeworkId);
    }

    @PostMapping("/submitItem")
    public Result submitItem(@RequestParam Map<String, String> map) {
        int userId = Integer.parseInt(map.get("userId"));
        int itemId = Integer.parseInt(map.get("itemId"));
        String answer = map.get("answer");
        return submitService.submitItem(userId, itemId, answer);
    }

}
