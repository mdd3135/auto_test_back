package com.mdd.auto_test_back.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mdd.auto_test_back.entity.Choice;
import com.mdd.auto_test_back.entity.Completion;
import com.mdd.auto_test_back.entity.ItemBank;
import com.mdd.auto_test_back.entity.Program;
import com.mdd.auto_test_back.entity.Result;
import com.mdd.auto_test_back.entity.ShortAnswer;
import com.mdd.auto_test_back.entity.Submit;
import com.mdd.auto_test_back.entity.SubmitAndResult;
import com.mdd.auto_test_back.entity.Response.ChatResponse;
import com.mdd.auto_test_back.entity.Response.OjResponse;
import com.mdd.auto_test_back.mapper.ChoiceMapper;
import com.mdd.auto_test_back.mapper.CompletionMapper;
import com.mdd.auto_test_back.mapper.ItemBankMapper;
import com.mdd.auto_test_back.mapper.ProgramMapper;
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

    @Autowired
    private ProgramMapper programMapper;

    public int getSubmitCountByUserIdAndHomeworkId(int userId, int homeworkId) {
        return submitMapper.getSubmitByUserIdAndHomeworkId(userId, homeworkId).size();
    }

    public int getSubmitCount(int userId) {
        return submitMapper.getSubmitByUserId(userId).size();
    }

    public Result submitItem(int userId, int itemId, String answer) throws Exception {
        ItemBank item = itemBankMapper.getItemBankById(itemId);
        Map<String, String> map = Map.of("score", "0", "feedback", "");
        if (item.getType() == 1) {
            map = gradeChoice(item, answer);
        } else if (item.getType() == 2) {
            map = gradeCompletion(item, answer);
        } else if (item.getType() == 3) {
            map = gradeShortAnswer(item, answer);
        } else if (item.getType() == 4) {
            map = gradeProgram(item, answer);
        }
        String feedback = map.get("feedback");
        float score = Float.parseFloat(map.get("score"));
        String time = String.valueOf(System.currentTimeMillis());
        Submit submit = new Submit(0, 0, userId, itemId, 1, time);
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

    public List<SubmitAndResult> getSubmitAndResultByUserId(int userId) {
        List<Submit> submitList = submitMapper.getSubmitByUserId(userId);
        List<SubmitAndResult> submitAndResultList = new ArrayList<SubmitAndResult>();
        for (Submit submit : submitList) {
            List<Result> resultList = resultMapper.getResultBySubmitId(submit.getId());
            SubmitAndResult submitAndResult = new SubmitAndResult(resultList, submit);
            submitAndResultList.add(submitAndResult);
        }
        return submitAndResultList;
    }

    public Submit submitHomework(int userId, int homeworkId) {
        String time = String.valueOf(System.currentTimeMillis());
        Submit submit = new Submit(0, homeworkId, userId, 0, 0, time);
        submitMapper.addSubmit(submit);
        int submitId = submitMapper.getLastInsertId();
        submit.setId(submitId);
        return submit;
    }

    public Result submitHomeworkItem(String answer, int submitId, int itemId) throws Exception {
        ItemBank item = itemBankMapper.getItemBankById(itemId);
        Map<String, String> map = Map.of("score", "0", "feedback", "");
        if (item.getType() == 1) {
            map = gradeChoice(item, answer);
        } else if (item.getType() == 2) {
            map = gradeCompletion(item, answer);
        } else if (item.getType() == 3) {
            map = gradeShortAnswer(item, answer);
        } else if (item.getType() == 4) {
            map = gradeProgram(item, answer);
        }
        String feedback = map.get("feedback");
        float score = Float.parseFloat(map.get("score"));
        Result result = new Result(0, submitId, itemId, score, feedback);
        resultMapper.addResult(result);
        return result;
    }

    private Map<String, String> gradeChoice(ItemBank item, String answer) {
        float score = 0;
        String feedback = "";
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
        return Map.of("score", String.valueOf(score), "feedback", feedback);
    }

    private Map<String, String> gradeCompletion(ItemBank item, String answer) {
        Completion completion = completionMapper.getCompletionById(item.getQuestionId());
        List<String> trueAnswerList = JsonConvert.parseStringList(completion.getAnswer());
        List<String> userAnswerList = JsonConvert.parseStringList(answer);
        int trueCount = 0;
        float score = 0;
        String feedback = "";
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
        return Map.of("score", String.valueOf(score), "feedback", feedback);
    }

    private Map<String, String> gradeShortAnswer(ItemBank item, String answer) throws Exception {
        String APIKEY = System.getenv().get("CHAT_API_KEY");
        float score = 0;
        String feedback = "";
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
        return Map.of("score", String.valueOf(score), "feedback", feedback);
    }

    private Map<String, String> gradeProgram(ItemBank item, String answer) throws Exception {
        float score = 0;
        String feedback = "";
        Program program = programMapper.getProgramById(item.getQuestionId());
        Unirest.setTimeouts(0, 0);
        ObjectMapper objectMapper = new ObjectMapper();
        JSONObject jsonBody = new JSONObject();
        String input = objectMapper.readValue(program.getInput(), new TypeReference<List<String>>() {
        }).get(0);
        String output = objectMapper.readValue(program.getOutput(), new TypeReference<List<String>>() {
        }).get(0);
        jsonBody.put("language", program.getLanguage());
        jsonBody.put("code", answer);
        jsonBody.put("input", input);
        jsonBody.put("output", output);
        com.mashape.unirest.http.HttpResponse<String> response = Unirest.post("http://127.0.0.1:5000/judge")
                .header("Content-Type", "application/json")
                .body(jsonBody.toString())
                .asString();
        OjResponse ojResponse = objectMapper.readValue(response.getBody(), OjResponse.class);
        String result = ojResponse.getResult();
        if (result.equals("Accepted")) {
            score = item.getScore();
            feedback = "恭喜你，代码正确!";
        } else {
            score = 0;
            feedback = "很遗憾，你的代码有误，请检查你的代码!";
            System.out.println(result);
        }
        return Map.of("score", String.valueOf(score), "feedback", feedback);
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
