package com.mdd.auto_test_back.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.mdd.auto_test_back.entity.Choice;

@Mapper
public interface ChoiceMapper {
    @Insert("insert into choice (content, options, answer, analysis, isMultiple, itemId) values (#{content}, #{options}, #{answer}, #{analysis}, #{isMultiple}, #{itemId});")
    void addChoice(Choice choice);

    @Select("select last_insert_id();")
    int getLastInsertId();

    @Select("select * from choice where id=#{id};")
    Choice getChoiceById(int id);
}
