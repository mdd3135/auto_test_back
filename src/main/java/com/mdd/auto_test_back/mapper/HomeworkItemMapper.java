package com.mdd.auto_test_back.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.mdd.auto_test_back.entity.HomeworkItem;

@Mapper
public interface HomeworkItemMapper {
    @Insert("insert into homeworkItem (homeworkId, itemId) values (#{homeworkId}, #{itemId});")
    void addHomeworkItem(HomeworkItem homeworkItem);

    @Select("select last_insert_id();")
    int getLastInsertId();

    @Select("select * from homeworkItem where homeworkId=#{homeworkId};")
    List<HomeworkItem> getHomeworkItemByHomeworkId(int homeworkId);

    @Delete("delete from homeworkItem where id=#{id};")
    void deleteHomeworkItemById(int id);

}
