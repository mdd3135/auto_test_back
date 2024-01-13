package com.mdd.auto_test_back.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mdd.auto_test_back.entity.Completion;
import com.mdd.auto_test_back.entity.ItemBank;
import com.mdd.auto_test_back.mapper.CompletionMapper;
import com.mdd.auto_test_back.mapper.ItemBankMapper;
import com.mdd.auto_test_back.service.ItemBankService;

@Service
public class ItemBankServiceImpl implements ItemBankService {
    @Autowired
    CompletionMapper completionMapper;
    @Autowired
    ItemBankMapper itemBankMapper;

    @Override
    public ItemBank addCompletion(float socre, String description, String content, String answer, String analysis) {
        String time = String.valueOf(System.currentTimeMillis());
        ItemBank itemBank = new ItemBank(0, 0, time, 2, socre, description);
        itemBankMapper.addItemBank(itemBank);
        int itemId = itemBankMapper.getLastInsertId();
        itemBank.setId(itemId);
        Completion completion = new Completion(0, content, answer, analysis, itemId);
        completionMapper.addCompletion(completion);
        int completionId = completionMapper.getLastInsertId();
        itemBank.setQuestionId(completionId);
        itemBankMapper.modItemBankById(itemBank);
        return itemBankMapper.getItemBankById(itemId);
    }

    @Override
    public List<ItemBank> getAllItemBank() {
        return itemBankMapper.getAllItemBank();
    }

    @Override
    public Completion getCompletionById(int id) {
       return completionMapper.getCompletionById(id);
    }

    
}
