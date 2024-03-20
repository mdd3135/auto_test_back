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

    @Insert("insert into submit (userId, homeworkId, itemId, type) values (#{userId}, #{homeworkId}, #{itemId}, #{type})")
    public void addSubmit(Submit submit);

    @Select("select last_insert_id();")
    public int getLastInsertId();
}
