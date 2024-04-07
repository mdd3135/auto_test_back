package com.mdd.auto_test_back.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.mdd.auto_test_back.entity.Submit;

@Mapper
public interface SubmitMapper {
    @Select("select * from submit where userId=#{userId} and homeworkId=#{homeworkId}")
    public List<Submit> getSubmitByUserIdAndHomeworkId(int userId, int homeworkId);

    @Select("select * from submit where userId=#{userId} and itemId=#{itemId}")
    public List<Submit> getSubmitByUserIdAndItemId(int userId, int itemId);

    @Select("select * from submit where userId=#{userId}")
    public List<Submit> getSubmitByUserId(int userId);

    @Select("select * from submit where userId=#{userId} and type=0")
    public List<Submit> getHomeworkSubmitByUserId(int userId);

    @Select("select * from submit where homeworkId=#{homeworkId}")
    public List<Submit> getSubmitByHomeworkId(int homeworkId);

    @Insert("insert into submit (userId, homeworkId, itemId, type, createTime) values (#{userId}, #{homeworkId}, #{itemId}, #{type}, #{createTime})")
    public void addSubmit(Submit submit);

    @Select("select last_insert_id();")
    public int getLastInsertId();
}
