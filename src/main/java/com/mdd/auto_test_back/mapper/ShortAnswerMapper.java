package com.mdd.auto_test_back.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.mdd.auto_test_back.entity.ShortAnswer;

@Mapper
public interface ShortAnswerMapper {

    @Insert("insert into shortAnswer (content, answer, analysis, itemId) values (#{content}, #{answer}, #{analysis}, #{itemId});")
    void addShortAnswer(ShortAnswer shortAnswer);

    @Select("select last_insert_id();")
    int getLastInsertId();

    @Select("select * from shortAnswer where id=#{id};")
    ShortAnswer getShortAnswerById(int id);
}
