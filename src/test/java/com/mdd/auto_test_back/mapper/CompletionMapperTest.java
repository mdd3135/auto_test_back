package com.mdd.auto_test_back.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mdd.auto_test_back.entity.Completion;

@SpringBootTest
public class CompletionMapperTest {
    @Autowired 
    CompletionMapper completionMapper;
    
    @Test
    void testAddCompletion() {
        Completion completion = new Completion(0, "一道填空题", "[\"答案\"]", "题目解析", 1);
        completionMapper.addCompletion(completion);
        int id = completionMapper.getLastInsertId();
        System.out.println(id);
    }

    @Test
    void testGetLastInsertId() {

    }
}
