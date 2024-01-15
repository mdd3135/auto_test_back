package com.mdd.auto_test_back.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mdd.auto_test_back.entity.ItemBank;

@Mapper
public interface ItemBankMapper {
    void addItemBank(ItemBank itemBank);

    int getLastInsertId();

    ItemBank getItemBankById(int id);

    void modItemBankById(ItemBank itemBank);

    List<ItemBank> getAllItemBank();

    void deleteItemBankById(int id);
}
