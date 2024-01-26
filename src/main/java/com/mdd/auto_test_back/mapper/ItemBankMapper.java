package com.mdd.auto_test_back.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.mdd.auto_test_back.entity.ItemBank;

@Mapper
public interface ItemBankMapper {
    @Insert("insert into itemBank (createTime, type, score, description) values (#{createTime}, #{type}, #{score}, #{description});")
    void addItemBank(ItemBank itemBank);

    @Select("select last_insert_id();")
    int getLastInsertId();

    @Select("select * from itemBank where id=#{id};")
    ItemBank getItemBankById(int id);

    @Update("update itemBank set questionId=#{questionId}, type=#{type}, score=#{score},description=#{description} where id=#{id};")
    void modItemBankById(ItemBank itemBank);

    @Select("select * from itemBank;")
    List<ItemBank> getAllItemBank();

    @Delete("delete from itemBank where id=#{id};")
    void deleteItemBankById(int id);
}
