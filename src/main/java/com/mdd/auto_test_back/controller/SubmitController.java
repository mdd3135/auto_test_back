package com.mdd.auto_test_back.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mdd.auto_test_back.entity.Result;
import com.mdd.auto_test_back.entity.Submit;
import com.mdd.auto_test_back.mapper.ResultMapper;
import com.mdd.auto_test_back.service.SubmitService;

@RestController
public class SubmitController {
    @Autowired
    private SubmitService submitService;

    @Autowired
    private ResultMapper resultMapper;

    @GetMapping("/getSubmitCountByUserIdAndHomeworkId")
    public int getSubmitCountByUserIdAndHomeworkId(@RequestParam Map<String, String> map) {
        int userId = Integer.parseInt(map.get("userId"));
        int homeworkId = Integer.parseInt(map.get("homeworkId"));
        return submitService.getSubmitCountByUserIdAndHomeworkId(userId, homeworkId);
    }

    @PostMapping("/submitItem")
    public Result submitItem(@RequestParam Map<String, String> map) throws Exception {
        int userId = Integer.parseInt(map.get("userId"));
        int itemId = Integer.parseInt(map.get("itemId"));
        String answer = map.get("answer");
        return submitService.submitItem(userId, itemId, answer);
    }

    @GetMapping("/getSubmitCountByUserId")
    public int getSubmitCountByUserId(@RequestParam Map<String, String> map) {
        int userId = Integer.parseInt(map.get("userId"));
        return submitService.getSubmitCount(userId);
    }

    @GetMapping("/getSubmitByUserId")
    public List<Submit> getSubmitByUserId(@RequestParam Map<String, String> map) {
        int count = Integer.parseInt(map.get("count"));
        int page = Integer.parseInt(map.get("page"));
        int userId = Integer.parseInt(map.get("userId"));
        List<Submit> submitList = submitService.getSubmitByUserId(userId);
        int start = (page - 1) * count;
        int end = page * count > submitList.size() ? submitList.size() : page * count;
        return submitList.subList(start, end);
    }

    @GetMapping("/getResultBySubmitId")
    public List<Result> getResultBySubmitId(@RequestParam Map<String, String> map) {
        int submitId = Integer.parseInt(map.get("submitId"));
        return resultMapper.getResultBySubmitId(submitId);
    }
}
