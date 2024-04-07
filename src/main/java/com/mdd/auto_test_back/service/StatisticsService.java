package com.mdd.auto_test_back.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mdd.auto_test_back.entity.Homework;
import com.mdd.auto_test_back.entity.ItemBank;
import com.mdd.auto_test_back.entity.Result;
import com.mdd.auto_test_back.entity.ScoreAnalysis;
import com.mdd.auto_test_back.entity.Submit;
import com.mdd.auto_test_back.entity.User;
import com.mdd.auto_test_back.mapper.HomeworkMapper;
import com.mdd.auto_test_back.mapper.ItemBankMapper;
import com.mdd.auto_test_back.mapper.ResultMapper;
import com.mdd.auto_test_back.mapper.SubmitMapper;
import com.mdd.auto_test_back.mapper.UserMapper;

@Service
public class StatisticsService {
    @Autowired
    SubmitMapper submitMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    HomeworkMapper homeworkMapper;

    @Autowired
    ResultMapper resultMapper;

    @Autowired
    ItemBankMapper itemBankMapper;

    public List<ScoreAnalysis> getScoreAnalysis(int userId) {
        User user = userMapper.findUserById(userId);
        List<Submit> submitList = submitMapper.getHomeworkSubmitByUserId(userId);
        List<ScoreAnalysis> scoreAnalysisList = new ArrayList<>();
        // 得到ScoreAnalysisList
        for (int i = 0; i < submitList.size(); i++) {
            Submit submit = submitList.get(i);
            Homework homework = homeworkMapper.getHomeworkById(submit.getHomeworkId());
            List<Result> resultList = resultMapper.getResultBySubmitId(submit.getId());
            float gainedScore = 0;
            float totalScore = 0;
            for (int j = 0; j < resultList.size(); j++) {
                Result result = resultList.get(j);
                gainedScore += result.getScore();
                ItemBank item = itemBankMapper.getItemBankById(result.getItemId());
                totalScore += item.getScore();
            }
            ScoreAnalysis scoreAnalysis = new ScoreAnalysis(homework.getId(), homework.getHomeworkName(), userId,
                    user.getName(), homework.getCreateTime(), homework.getCount(), gainedScore, totalScore, 1);
            scoreAnalysisList.add(scoreAnalysis);
        }
        // 按主关键字homeworkId排序，按次关键字createTime排序
        Collections.sort(scoreAnalysisList,
                Comparator.comparing(ScoreAnalysis::getHomeworkId).thenComparing(ScoreAnalysis::getCreateTime));
        // 舍去超出count的ScoreAnalysis
        Map<Integer, Integer> idCountMap = new HashMap<>();
        List<ScoreAnalysis> filteredList = new ArrayList<>();
        for (ScoreAnalysis scoreAnalysis : scoreAnalysisList) {
            int homeworkId = scoreAnalysis.getHomeworkId();
            int count = scoreAnalysis.getCount();
            int currentCount = idCountMap.getOrDefault(homeworkId, 0);
            if (currentCount < count) {
                idCountMap.put(homeworkId, currentCount + 1);
                filteredList.add(scoreAnalysis);
            }
        }
        // 对相同的homeworkId去重保留score最大的那个
        Map<Integer, ScoreAnalysis> idMaxScoreMap = new HashMap<>();
        for (ScoreAnalysis scoreAnalysis : filteredList) {
            int homeworkId = scoreAnalysis.getHomeworkId();
            float score = scoreAnalysis.getGainedScore();
            if (!idMaxScoreMap.containsKey(homeworkId) || score > idMaxScoreMap.get(homeworkId).getGainedScore()) {
                idMaxScoreMap.put(homeworkId, scoreAnalysis);
            }
        }
        scoreAnalysisList = new ArrayList<>(idMaxScoreMap.values());
        return scoreAnalysisList;
    }

    public List<ScoreAnalysis> getHomeworkAnalysis(int homeworkId) {
        List<Submit> submitList = submitMapper.getSubmitByHomeworkId(homeworkId);
        Homework homework = homeworkMapper.getHomeworkById(homeworkId);
        List<ScoreAnalysis> scoreAnalysisList = new ArrayList<>();
        for (int i = 0; i < submitList.size(); i++) {
            Submit submit = submitList.get(i);
            User user = userMapper.findUserById(submit.getUserId());
            List<Result> resultList = resultMapper.getResultBySubmitId(submit.getId());
            float gainedScore = 0;
            float totalScore = 0;
            for (int j = 0; j < resultList.size(); j++) {
                Result result = resultList.get(j);
                gainedScore += result.getScore();
                ItemBank item = itemBankMapper.getItemBankById(result.getItemId());
                totalScore += item.getScore();
            }
            ScoreAnalysis scoreAnalysis = new ScoreAnalysis(homework.getId(), homework.getHomeworkName(), user.getId(),
                    user.getName(), homework.getCreateTime(), homework.getCount(), gainedScore, totalScore, 1);
            scoreAnalysisList.add(scoreAnalysis);

        }
        Collections.sort(scoreAnalysisList,
                Comparator.comparing(ScoreAnalysis::getUserId).thenComparing(ScoreAnalysis::getCreateTime));
        Map<Integer, Integer> idCountMap = new HashMap<>();
        List<ScoreAnalysis> filteredList = new ArrayList<>();
        for (ScoreAnalysis scoreAnalysis : scoreAnalysisList) {
            int userId = scoreAnalysis.getUserId();
            int count = scoreAnalysis.getCount();
            int currentCount = idCountMap.getOrDefault(userId, 0);
            if (currentCount < count) {
                idCountMap.put(userId, currentCount + 1);
                filteredList.add(scoreAnalysis);
            }
        }
        Map<Integer, ScoreAnalysis> idMaxScoreMap = new HashMap<>();
        for (ScoreAnalysis scoreAnalysis : filteredList) {
            int userId = scoreAnalysis.getUserId();
            float score = scoreAnalysis.getGainedScore();
            if (!idMaxScoreMap.containsKey(userId) || score > idMaxScoreMap.get(userId).getGainedScore()) {
                idMaxScoreMap.put(userId, scoreAnalysis);
            }
        }
        scoreAnalysisList = new ArrayList<>(idMaxScoreMap.values());
        return scoreAnalysisList;
    }

}
