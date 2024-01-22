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
    public ItemBank addCompletion(@RequestParam Map<String, String> map) {
        float score = Float.parseFloat(map.get("score"));
        String description = map.get("description");
        String content = map.get("content");
        String answer = map.get("answer");
        String analysis = map.get("analysis");
        return itemBankService.addCompletion(score, description, content, answer, analysis);
    }

    @GetMapping("/getAllItemBank")
    public List<ItemBank> getAllItemBank(@RequestParam Map<String, String> map) {
        try {
            int count = Integer.parseInt(map.get("count"));
            int page = Integer.parseInt(map.get("page"));
            List<ItemBank> itemList = itemBankService.getAllItemBank();
            int start = (page - 1) * count;
            int end = page * count > itemList.size() ? itemList.size() : page * count;
            return itemList.subList(start, end);
        } catch (Exception e) {
            return itemBankService.getAllItemBank();
        }

    }

    @GetMapping("/getCompletionById")
    public Completion getCompletionById(@RequestParam Map<String, String> map) {
        int id = Integer.parseInt(map.get("id"));
        return itemBankService.getCompletionById(id);
    }

    @PostMapping("/deleteItemBank")
    public void deleteItemBank(@RequestParam Map<String, String> map) {
        int id = Integer.parseInt(map.get("id"));
        itemBankService.deleteItemBank(id);
    }

    @GetMapping("/getItemBankCount")
    public int getItemBankCount() {
        List<ItemBank> itemList = itemBankService.getAllItemBank();
        return itemList.size();
    }

    @PostMapping("addChoice")
    public ItemBank addChoice(@RequestParam Map<String, String> map) {
        String content = map.get("content");
        String options = map.get("options");
        String answer = map.get("answer");
        String analysis = map.get("analysis");
        int isMultiple = Integer.parseInt(map.get("isMultiple"));
        float score = Float.parseFloat(map.get("score"));
        String description = map.get("description");
        return itemBankService.addChoice(content, options, answer, analysis, isMultiple, score, description);
    }

}
