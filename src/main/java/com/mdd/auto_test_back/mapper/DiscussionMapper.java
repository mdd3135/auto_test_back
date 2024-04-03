package com.mdd.auto_test_back.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.mdd.auto_test_back.entity.Discussion;

@Mapper
public interface DiscussionMapper {
    @Insert("insert into discussion (homeworkId, userId, content, isTop) values (#{homeworkId}, #{userId}, #{content}, #{isTop})")
    public void addDiscussion(Discussion discussion);

    @Delete("delete from discussion where id=#{id}")
    public void delDiscussionById(int id);

    @Select("select * from discussion where id=#{id}")
    public Discussion findDiscussionById(int id);

    @Select("select last_insert_id();")
    public int getLastInsertId();

    @Select("select * from discussion where userId=#{userId}")
    public List<Discussion> findDiscussionByUserId(int userId);

    @Select("select * from discussion where homeworkId=#{homeworkId}")
    public List<Discussion> findDiscussionByHomeworkId(int homeworkId);

    @Update("update discussion set homeworkId=#{homeworkId}, userId=#{userId}, content=#{content}, isTop=#{isTop} where id=#{id}")
    public void modDiscussion(Discussion discussion);
}
