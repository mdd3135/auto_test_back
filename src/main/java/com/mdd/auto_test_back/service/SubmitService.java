package com.mdd.auto_test_back.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mdd.auto_test_back.entity.Choice;
import com.mdd.auto_test_back.entity.Completion;
import com.mdd.auto_test_back.entity.ItemBank;
import com.mdd.auto_test_back.entity.Result;
import com.mdd.auto_test_back.entity.Submit;
import com.mdd.auto_test_back.mapper.ChoiceMapper;
import com.mdd.auto_test_back.mapper.CompletionMapper;
import com.mdd.auto_test_back.mapper.ItemBankMapper;
import com.mdd.auto_test_back.mapper.ResultMapper;
import com.mdd.auto_test_back.mapper.SubmitMapper;
import com.mdd.auto_test_back.util.JsonConvert;

@Service
public class SubmitService {
    @Autowired
    private SubmitMapper submitMapper;

    @Autowired
    private ResultMapper resultMapper;

    @Autowired
    private ItemBankMapper itemBankMapper;

    @Autowired
    private CompletionMapper completionMapper;

    @Autowired
    private ChoiceMapper choiceMapper;

    public int getSubmitCountByUserIdAndHomeworkId(int userId, int homeworkId) {
        return submitMapper.getSubmitByUserIdAndHomeworkId(userId, homeworkId).size();
    }

    public Result submitItem(int userId, int itemId, String answer) {
        ItemBank item = itemBankMapper.getItemBankById(itemId);
        float score = 0;
        String feedback = "";
        if (item.getType() == 1) {
            // 选择题
            Choice choice = choiceMapper.getChoiceById(item.getQuestionId());
            List<String> trueAnswerList = JsonConvert.parseStringList(choice.getAnswer());
            List<String> userAnswerList = JsonConvert.parseStringList(answer);
            Collections.sort(trueAnswerList);
            Collections.sort(userAnswerList);
            if (checkAnswerList(trueAnswerList, userAnswerList)) {
                score = item.getScore();
                feedback = "恭喜你，答案正确！";
            } else {
                score = 0;
                feedback = "很遗憾，答案错误！正确答案是：";
                for (int i = 0; i < trueAnswerList.size(); i++) {
                    if (i != 0) {
                        feedback += "、";
                    }
                    feedback += trueAnswerList.get(i);
                }
                feedback += "。您选择的答案是：";
                for (int i = 0; i < userAnswerList.size(); i++) {
                    if (i != 0) {
                        feedback += "、";
                    }
                    feedback += userAnswerList.get(i);
                }
                feedback += "。\n";
                feedback += choice.getAnalysis();
            }
        } else if (item.getType() == 2) {
            // 填空题
            Completion completion = completionMapper.getCompletionById(item.getQuestionId());
            List<String> trueAnswerList = JsonConvert.parseStringList(completion.getAnswer());
            List<String> userAnswerList = JsonConvert.parseStringList(answer);
            int trueCount = 0;
            for (int i = 0; i < trueAnswerList.size(); i++) {
                if (trueAnswerList.get(i).equals(userAnswerList.get(i))) {
                    trueCount++;
                }
            }
            score = (float) trueCount / (float) trueAnswerList.size() * item.getScore();
            if (trueCount == trueAnswerList.size()) {
                feedback = "恭喜你，答案正确！";
            } else {
                feedback = "很遗憾，答案错误！正确答案是：";
                for (int i = 0; i < trueAnswerList.size(); i++) {
                    if (i != 0) {
                        feedback += "、";
                    }
                    feedback += trueAnswerList.get(i);
                }
                feedback += "。您选择的答案是：";
                for (int i = 0; i < userAnswerList.size(); i++) {
                    if (i != 0) {
                        feedback += "、";
                    }
                    feedback += userAnswerList.get(i);
                }
                feedback += "。\n";
                feedback += completion.getAnalysis();
            }
        } else if (item.getType() == 3) {
            // 简答题
        } else if (item.getType() == 4) {
            // 编程题
        }
        // 还应保存提交记录到数据库，待完成
        Submit submit = new Submit(0, 0, userId, itemId, 1);
        Result result = new Result(0, 0, itemId, score, feedback);
        return result;
    }

    private boolean checkAnswerList(List<String> trueAnswerList, List<String> userAnswerList) {
        if (trueAnswerList.size() != userAnswerList.size()) {
            return false;
        }
        for (int i = 0; i < trueAnswerList.size(); i++) {
            if (!trueAnswerList.get(i).equals(userAnswerList.get(i))) {
                return false;
            }
        }
        return true;
    }
}
