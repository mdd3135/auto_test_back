package com.mdd.auto_test_back.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.mdd.auto_test_back.entity.Result;

@Mapper
public interface ResultMapper {
    @Select("select * from result where submitId=#{submitId}")
    public List<Result> getResultBySubmitId(int submitId);

    @Insert("insert into result (id, submitId, itemId, score, feedback) values (#{id}, #{submitId}, #{itemId}, #{score}, #{feedback})")
    public void addResult(Result result);

    @Select("select last_insert_id();")
    public int getLastInsertId();
}
