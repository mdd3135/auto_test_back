package com.mdd.auto_test_back.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mdd.auto_test_back.entity.Discussion;
import com.mdd.auto_test_back.service.DiscussionService;

@RestController
public class DiscussionController {
    @Autowired
    private DiscussionService discussionService;

    @PostMapping("/addDiscussion")
    public Discussion addDiscussion(@RequestParam Map<String, String> map) {
        int homeworkId = Integer.parseInt(map.get("homeworkId"));
        int userId = Integer.parseInt(map.get("userId"));
        String content = map.get("content");
        int isTop = Integer.parseInt(map.get("isTop"));
        return discussionService.addDiscussion(homeworkId, userId, content, isTop);
    }

    @PostMapping("/delDiscussionById")
    public void delDiscussionById(@RequestParam Map<String, String> map) {
        int id = Integer.parseInt(map.get("id"));
        discussionService.delDiscussionById(id);
    }

    @GetMapping("/findDiscussionByHomeworkId")
    public List<Discussion> findDiscussionByHomeworkId(@RequestParam Map<String, String> map) {
        int homeworkId = Integer.parseInt(map.get("homeworkId"));
        return discussionService.findDiscussionByHomeworkId(homeworkId);
    }

    @PostMapping("/modDiscussion")
    public Discussion modDiscussion(@RequestParam Map<String, String> map) {
        int id = Integer.parseInt(map.get("id"));
        int homeworkId = Integer.parseInt(map.get("homeworkId"));
        int userId = Integer.parseInt(map.get("userId"));
        String content = map.get("content");
        int isTop = Integer.parseInt(map.get("isTop"));
        return discussionService.modDiscussion(id, homeworkId, userId, content, isTop);
    }
}
