package com.mdd.auto_test_back.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mdd.auto_test_back.entity.Completion;
import com.mdd.auto_test_back.entity.ItemBank;
import com.mdd.auto_test_back.service.ItemBankService;

@RestController
public class ItemBankController {

    @Autowired
    ItemBankService itemBankService;

    @PostMapping("/addCompletion")
    ItemBank addCompletion(@RequestParam Map<String, String> map) {
        float score = Float.parseFloat(map.get("socre"));
        String description = map.get("description");
        String content = map.get("content");
        String answer = map.get("answer");
        String analysis = map.get("analysis");
        return itemBankService.addCompletion(score, description, content, answer, analysis);
    }

    @GetMapping("/getAllItemBank")
    List<ItemBank> getAllItemBank() {
        return itemBankService.getAllItemBank();
    }

    @GetMapping("/getCompletionById")
    Completion getCompletionById(@RequestParam Map<String, String> map) {
        int id = Integer.parseInt(map.get("id"));
        return itemBankService.getCompletionById(id);
    }
}
