package com.mdd.auto_test_back.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mdd.auto_test_back.entity.Choice;
import com.mdd.auto_test_back.entity.Completion;
import com.mdd.auto_test_back.entity.ItemBank;
import com.mdd.auto_test_back.entity.Program;
import com.mdd.auto_test_back.entity.ShortAnswer;
import com.mdd.auto_test_back.mapper.ChoiceMapper;
import com.mdd.auto_test_back.mapper.CompletionMapper;
import com.mdd.auto_test_back.mapper.ItemBankMapper;
import com.mdd.auto_test_back.mapper.ProgramMapper;
import com.mdd.auto_test_back.mapper.ShortAnswerMapper;

@Service
public class ItemBankService {
    @Autowired
    CompletionMapper completionMapper;
    @Autowired
    ItemBankMapper itemBankMapper;
    @Autowired
    ChoiceMapper choiceMapper;
    @Autowired
    ShortAnswerMapper shortAnswerMapper;
    @Autowired
    ProgramMapper programMapper;

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

    public List<ItemBank> getAllItemBank() {
        List<ItemBank> itemBankList = itemBankMapper.getAllItemBank();
        for(int i = 0; i < itemBankList.size(); i++){
            if(itemBankList.get(i).getId() == 0){
                itemBankList.remove(i);
                break;
            }
        }
        return itemBankList;
    }

    public ItemBank getItemBankById(int id) {
        return itemBankMapper.getItemBankById(id);
    }

    public Completion getCompletionById(int id) {
        return completionMapper.getCompletionById(id);
    }

    public void deleteItemBank(int id) {
        itemBankMapper.deleteItemBankById(id);
    }

    public ItemBank modItemBankById(int id, float score, String description) {
        ItemBank itemBank = itemBankMapper.getItemBankById(id);
        itemBank.setScore(score);
        itemBank.setDescription(description);
        itemBankMapper.modItemBankById(itemBank);
        return itemBankMapper.getItemBankById(id);
    }

    public ItemBank addChoice(String content, String options, String answer, String analysis, int isMultiple,
            float score,
            String description) {
        String time = String.valueOf(System.currentTimeMillis());
        ItemBank itemBank = new ItemBank(0, 0, time, 1, score, description);
        itemBankMapper.addItemBank(itemBank);
        int itemId = itemBankMapper.getLastInsertId();
        itemBank.setId(itemId);
        Choice choice = new Choice(0, content, options, answer, analysis, isMultiple, itemId);
        choiceMapper.addChoice(choice);
        int choiceId = choiceMapper.getLastInsertId();
        itemBank.setQuestionId(choiceId);
        itemBankMapper.modItemBankById(itemBank);
        return itemBankMapper.getItemBankById(itemId);
    }

    public Choice getChoiceById(int id) {
        return choiceMapper.getChoiceById(id);
    }

    public ItemBank addShortAnswer(String content, String answer, String analysis, float score, String description) {
        String time = String.valueOf(System.currentTimeMillis());
        ItemBank itemBank = new ItemBank(0, 0, time, 3, score, description);
        itemBankMapper.addItemBank(itemBank);
        int itemId = itemBankMapper.getLastInsertId();
        itemBank.setId(itemId);
        ShortAnswer shortAnswer = new ShortAnswer(0, content, answer, analysis, itemId);
        shortAnswerMapper.addShortAnswer(shortAnswer);
        int shortAnswerId = shortAnswerMapper.getLastInsertId();
        itemBank.setQuestionId(shortAnswerId);
        itemBankMapper.modItemBankById(itemBank);
        return itemBankMapper.getItemBankById(itemId);
    }

    public ShortAnswer getShortAnswerById(int id) {
        return shortAnswerMapper.getShortAnswerById(id);
    }

    public ItemBank addProgram(String content, String answer, String analysis, String input, String output,
            String language, float score, String description) {
        String time = String.valueOf(System.currentTimeMillis());
        ItemBank itemBank = new ItemBank(0, 0, time, 4, score, description);
        itemBankMapper.addItemBank(itemBank);
        int itemId = itemBankMapper.getLastInsertId();
        itemBank.setId(itemId);
        Program program = new Program(0, content, answer, analysis, input, output, language, itemId);
        programMapper.addProgram(program);
        int programId = programMapper.getLastInsertId();
        itemBank.setQuestionId(programId);
        itemBankMapper.modItemBankById(itemBank);
        return itemBankMapper.getItemBankById(itemId);
    }

    public Program getProgramById(int id) {
        return programMapper.getProgramById(id);
    }

}
