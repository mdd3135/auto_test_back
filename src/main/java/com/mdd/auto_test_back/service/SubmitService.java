package com.mdd.auto_test_back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mdd.auto_test_back.mapper.SubmitMapper;

@Service
public class SubmitService {
    @Autowired
    private SubmitMapper submitMapper;

    public int getSubmitCountByUserIdAndHomeworkId(int userId, int homeworkId) {
        return submitMapper.getSubmitByUserIdAndHomeworkId(userId, homeworkId).size();
    }
}
