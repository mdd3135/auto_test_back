package com.mdd.auto_test_back.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mdd.auto_test_back.entity.Collect;
import com.mdd.auto_test_back.entity.ItemBank;
import com.mdd.auto_test_back.service.CollectService;
import com.mdd.auto_test_back.service.ItemBankService;

@RestController
public class CollectController {
    @Autowired
    private CollectService collectService;
    @Autowired
    private ItemBankService itemBankService;

    @GetMapping("/getCollectItemByUserId")
    public List<ItemBank> getCollectByUserId(@RequestParam Map<String, String> map) {
        int userId = Integer.parseInt(map.get("userId"));
        int count = Integer.parseInt(map.get("count"));
        int page = Integer.parseInt(map.get("page"));
        List<Collect> collectList = collectService.getCollectByUserId(userId);
        int start = (page - 1) * count;
        int end = page * count > collectList.size() ? collectList.size() : page * count;
        List<ItemBank> itemBankList = new ArrayList<ItemBank>();
        for (int i = start; i < end; i++) {
            itemBankList.add(itemBankService.getItemBankById(collectList.get(i).getItemId()));
        }
        return itemBankList;
    }

    @PostMapping("/addCollect")
    public Collect addCollect(@RequestParam Map<String, String> map) {
        int userId = Integer.parseInt(map.get("userId"));
        int itemId = Integer.parseInt(map.get("itemId"));
        return collectService.addCollect(userId, itemId);
    }

    @GetMapping("/getCollectCount")
    public int getCollectCount(@RequestParam Map<String, String> map) {
        int userId = Integer.parseInt(map.get("userId"));
        return collectService.getCollectByUserId(userId).size();
    }

    @PostMapping("/deleteCollectByUserIdAndItemId")
    public void deleteCollectByUserIdAndItemId(@RequestParam Map<String, String> map) {
        int userId = Integer.parseInt(map.get("userId"));
        int itemId = Integer.parseInt(map.get("itemId"));
        collectService.deleteColectByUserIdAndItemId(userId, itemId);
    }

    @GetMapping("/checkCollectByUserIdAndItemId")
    public int checkCollectByUserIdAndItemId(@RequestParam Map<String, String> map) {
        int userId = Integer.parseInt(map.get("userId"));
        int itemId = Integer.parseInt(map.get("itemId"));
        return collectService.getCollectByUserIdAndItemId(userId, itemId);
    }
}
