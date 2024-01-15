package com.mdd.auto_test_back.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mdd.auto_test_back.entity.Completion;
import com.mdd.auto_test_back.entity.ItemBank;

@Repository
public interface ItemBankService {
    ItemBank addCompletion(float socre, String description, String content, String answer, String analysis);

    List<ItemBank> getAllItemBank();

    Completion getCompletionById(int id);

    void deleteItemBank(int id);
}
