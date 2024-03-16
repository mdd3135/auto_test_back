package com.mdd.auto_test_back.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mdd.auto_test_back.entity.Collect;
import com.mdd.auto_test_back.mapper.CollectMapper;

@Service
public class CollectService {
    @Autowired
    CollectMapper collectMapper;

    public Collect addCollect(int userId, int itemId) {
        Collect collect = new Collect(0, userId, itemId);
        if (collectMapper.getCollectByUserIdAndItemId(collect).size() > 0) {
            return null;
        }
        collectMapper.addCollect(collect);
        return collect;
    }

    public List<Collect> getCollectByUserId(int userId) {
        return collectMapper.getCollectByUserId(userId);
    }

    public void deleteColectByUserIdAndItemId(int userId, int itemId) {
        Collect collect = new Collect(0, userId, itemId);
        collectMapper.deleteColectByUserIdAndItemId(collect);
    }

    public int getCollectByUserIdAndItemId(int userId, int itemId) {
        Collect collect = new Collect(0, userId, itemId);
        return collectMapper.getCollectByUserIdAndItemId(collect).size();
    }
}
