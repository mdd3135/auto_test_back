package com.mdd.auto_test_back.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mdd.auto_test_back.entity.Discussion;
import com.mdd.auto_test_back.mapper.DiscussionMapper;

@Service
public class DiscussionService {

    @Autowired
    private DiscussionMapper discussionMapper;

    public Discussion addDiscussion(int homeworkId, int userId, String content, int isTop) {
        Discussion discussion = new Discussion(0, homeworkId, userId, content, isTop);
        discussionMapper.addDiscussion(discussion);
        int id = discussionMapper.getLastInsertId();
        discussion.setId(id);
        return discussion;
    }

    public void delDiscussionById(int id) {
        discussionMapper.delDiscussionById(id);
    }

    public List<Discussion> findDiscussionByHomeworkId(int homeworkId) {
        return discussionMapper.findDiscussionByHomeworkId(homeworkId);
    }

    public Discussion modDiscussion(int id, int homeworkId, int userId, String content, int isTop) {
        Discussion discussion = new Discussion(id, homeworkId, userId, content, isTop);
        discussionMapper.modDiscussion(discussion);
        return discussion;
    }

}
