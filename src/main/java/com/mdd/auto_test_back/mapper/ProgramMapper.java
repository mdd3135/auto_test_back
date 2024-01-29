package com.mdd.auto_test_back.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.mdd.auto_test_back.entity.Program;

@Mapper
public interface ProgramMapper {
    @Insert("insert into program (content, answer, analysis, input, output, language, itemId) values (#{content}, #{answer}, #{analysis}, #{input}, #{output}, #{language}, #{itemId});")
    void addProgram(Program program);

    @Select("select last_insert_id();")
    int getLastInsertId();

    @Select("select * from program where id=#{id};")
    Program getProgramById(int id);
}
