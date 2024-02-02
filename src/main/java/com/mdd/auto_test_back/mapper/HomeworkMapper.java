package com.mdd.auto_test_back.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.mdd.auto_test_back.entity.Homework;

@Mapper
public interface HomeworkMapper {
    @Insert("insert into homework (id, createTime, startTime, deadline, homeworkName, item, count) values (#{id}, #{createTime}, #{startTime}, #{deadline}, #{homeworkName}, #{item}, #{count})")
    void addHomework(Homework homework);

    @Select("select last_insert_id();")
    int getLastInsertId();

    @Select("select * from homework")
    List<Homework> getAllHomework();
}
