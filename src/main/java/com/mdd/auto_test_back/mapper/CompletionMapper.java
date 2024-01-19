package com.mdd.auto_test_back.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.mdd.auto_test_back.entity.Completion;

@Mapper
public interface CompletionMapper {
    @Insert("insert into completion (content, answer, analysis, itemId) values (#{content}, #{answer}, #{analysis}, #{itemId});")
    void addCompletion(Completion completion);

    @Select("select last_insert_id();")
    int getLastInsertId();

    @Select("select * from completion where id=#{id};")
    Completion getCompletionById(int id);
}
