package com.mdd.auto_test_back.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.mdd.auto_test_back.entity.Collect;

@Mapper
public interface CollectMapper {
    @Insert("insert into collect (userId, itemId) values (#{userId}, #{itemId});")
    void addCollect(Collect collect);

    @Select("select * from collect where userId=#{userId};")
    List<Collect> getCollectByUserId(int userId);

    @Select("select * from collect where userId=#{userId} and itemId=#{itemId}")
    List<Collect> getCollectByUserIdAndItemId(Collect collect);

    @Delete("delete from collect where userId=#{userId} and itemId=#{itemId};")
    void deleteColectByUserIdAndItemId(Collect collect);

}
