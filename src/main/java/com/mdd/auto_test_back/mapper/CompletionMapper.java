package com.mdd.auto_test_back.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.mdd.auto_test_back.entity.Completion;

@Mapper
public interface CompletionMapper {
    void addCompletion(Completion completion);

    int getLastInsertId();

    Completion getCompletionById(int id);
}
