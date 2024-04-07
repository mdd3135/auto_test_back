package com.mdd.auto_test_back.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mdd.auto_test_back.entity.ScoreAnalysis;
import com.mdd.auto_test_back.service.StatisticsService;

@RestController
public class StatisticsController {
    @Autowired
    StatisticsService statisticsService;

    @GetMapping("/getScoreAnalysis")
    public List<ScoreAnalysis> getScoreAnalysis(@RequestParam Map<String, String> map) {
        int userId = Integer.parseInt(map.get("userId"));
        return statisticsService.getScoreAnalysis(userId);
    }

    @GetMapping("/getHomeworkAnalysis")
    public List<ScoreAnalysis> getHomeworkAnalysis(@RequestParam Map<String, String> map) {
        int homeworkId = Integer.parseInt(map.get("homeworkId"));
        return statisticsService.getHomeworkAnalysis(homeworkId);
    }
}
