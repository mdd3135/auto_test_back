package com.mdd.auto_test_back.mapper;

import java.sql.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mdd.auto_test_back.entity.ItemBank;

@SpringBootTest
public class ItemBankMapperTest {
    @Autowired
    ItemBankMapper itemBankMapper;

    @Test
    void testAddItemBank() {
        String time = String.valueOf(System.currentTimeMillis());
        ItemBank itemBank = new ItemBank(0, 0, time, 2, 3, "测试题目");
        itemBankMapper.addItemBank(itemBank);
        int id = itemBankMapper.getLastInsertId();
        System.out.println(id);
    }

    @Test
    void testGetItemBankById() {
        ItemBank itemBank = itemBankMapper.getItemBankById(1);
        System.out.println(itemBank);
    }

    @Test
    void testModItemBankById() {
        ItemBank itemBank = itemBankMapper.getItemBankById(1);
        itemBank.setDescription("修改后的描述");
        itemBankMapper.modItemBankById(itemBank);
    }
}
