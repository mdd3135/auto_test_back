package com.mdd.auto_test_back.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mdd.auto_test_back.entity.Choice;
import com.mdd.auto_test_back.entity.Completion;
import com.mdd.auto_test_back.entity.ItemBank;
import com.mdd.auto_test_back.entity.Result;
import com.mdd.auto_test_back.entity.ShortAnswer;
import com.mdd.auto_test_back.entity.Submit;
import com.mdd.auto_test_back.entity.Response.ChatResponse;
import com.mdd.auto_test_back.mapper.ChoiceMapper;
import com.mdd.auto_test_back.mapper.CompletionMapper;
import com.mdd.auto_test_back.mapper.ItemBankMapper;
import com.mdd.auto_test_back.mapper.ResultMapper;
import com.mdd.auto_test_back.mapper.ShortAnswerMapper;
import com.mdd.auto_test_back.mapper.SubmitMapper;
import com.mdd.auto_test_back.util.JsonConvert;
import com.mdd.auto_test_back.util.LastDoubleInString;

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

    @Autowired
    private ShortAnswerMapper shortAnswerMapper;

    public int getSubmitCountByUserIdAndHomeworkId(int userId, int homeworkId) {
        return submitMapper.getSubmitByUserIdAndHomeworkId(userId, homeworkId).size();
    }

    public int getSubmitCount(int userId) {
        return submitMapper.getSubmitByUserId(userId).size();
    }

    public Result submitItem(int userId, int itemId, String answer) throws Exception {
        String APIKEY = System.getenv().get("CHAT_API_KEY");
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
            ShortAnswer shortAnswer = shortAnswerMapper.getShortAnswerById(item.getQuestionId());
            Unirest.setTimeouts(0, 0);
            String authorization = "Bearer " + APIKEY;
            String content = "将给你一个标准答案和一个待评分的答案，满分为";
            content += item.getScore();
            content += "分，请你给待评分答案评分，只需要说一个分数，不要有其他任何解释。标准答案是“";
            content += shortAnswer.getAnswer();
            content += "”，待评分答案是“";
            content += answer;
            content += "”";
            String bodyString = "{\n  \"model\": \"gpt-3.5-turbo\",\n  \"temperature\": 0.2,\n  \"messages\": [{\"role\": \"user\", \"content\": \"";
            bodyString += content;
            bodyString += "\"}]\n}";
            com.mashape.unirest.http.HttpResponse<String> response = Unirest
                    .post("https://api.chatanywhere.com.cn/v1/chat/completions")
                    .header("Authorization", authorization)
                    .header("Content-Type", "application/json")
                    .body(bodyString)
                    .asString();
            ObjectMapper objectMapper = new ObjectMapper();
            ChatResponse chatResponse = objectMapper.readValue(response.getBody(), ChatResponse.class);
            String responseMessage = chatResponse.getChoices().get(0).getMessage().getContent();
            System.out.println(responseMessage);
            score = (float) LastDoubleInString.convert(responseMessage);
            if (score == item.getScore()) {
                feedback = "恭喜你，答案正确！";
            } else {
                feedback = "很遗憾，您的答案有误！正确答案是：";
                feedback += shortAnswer.getAnswer();
                feedback += "\n您的答案是：";
                feedback += answer;
                feedback += "\n";
                feedback += shortAnswer.getAnalysis();
            }
        } else if (item.getType() == 4) {
            // 编程题
        }
        Submit submit = new Submit(0, null, userId, itemId, 1);
        submitMapper.addSubmit(submit);
        int submitId = submitMapper.getLastInsertId();
        Result result = new Result(0, submitId, itemId, score, feedback);
        resultMapper.addResult(result);
        return result;
    }

    public List<Submit> getSubmitByUserId(int userId) {
        return submitMapper.getSubmitByUserId(userId);
    }

    public List<Result> getResultBySubmitId(int submitId) {
        return resultMapper.getResultBySubmitId(submitId);
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
