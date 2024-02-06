package com.mdd.auto_test_back.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.mdd.auto_test_back.entity.Homework;

@Mapper
public interface HomeworkMapper {
    @Insert("insert into homework (createTime, startTime, deadline, homeworkName, count) values (#{createTime}, #{startTime}, #{deadline}, #{homeworkName}, #{count})")
    void addHomework(Homework homework);

    @Select("select last_insert_id();")
    int getLastInsertId();

    @Select("select * from homework;")
    List<Homework> getAllHomework();

    @Select("select * from homework where id=#{id}")
    Homework getHomeworkById(int id);

    @Delete("delete from homework where id=#{id};")
    void deleteHomeworkById(int id);

    @Update("update homework set startTime=#{startTime}, deadline=#{deadline}, homeworkName=#{homeworkName}, count=#{count} where id=#{id};")
    Homework updateHomeworkById(Homework homework);
}
