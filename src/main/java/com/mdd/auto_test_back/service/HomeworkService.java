package com.mdd.auto_test_back.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mdd.auto_test_back.entity.Homework;
import com.mdd.auto_test_back.mapper.HomeworkMapper;

@Service
public class HomeworkService {
    @Autowired
    private HomeworkMapper homeworkMapper;

    public List<Homework> getAllHomework() {
        return homeworkMapper.getAllHomework();
    }

}
