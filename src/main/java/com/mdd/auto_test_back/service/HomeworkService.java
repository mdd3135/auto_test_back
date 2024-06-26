package com.mdd.auto_test_back.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mdd.auto_test_back.entity.Homework;
import com.mdd.auto_test_back.entity.HomeworkItem;
import com.mdd.auto_test_back.mapper.HomeworkItemMapper;
import com.mdd.auto_test_back.mapper.HomeworkMapper;
import com.mdd.auto_test_back.util.JsonConvert;

@Service
public class HomeworkService {
    @Autowired
    private HomeworkMapper homeworkMapper;

    @Autowired
    private HomeworkItemMapper homeworkItemMapper;

    public List<Homework> getAllHomework() {
        List<Homework> homeworkList = homeworkMapper.getAllHomework();
        for (int i = 0; i < homeworkList.size(); i++) {
            if (homeworkList.get(i).getId() == 0) {
                homeworkList.remove(0);
                break;
            }
        }
        return homeworkList;
    }

    public Homework addHomework(String startTime, String deadline, String homeworkName, String item, int count) {
        Homework homework = new Homework(0, String.valueOf(System.currentTimeMillis()), startTime, deadline,
                homeworkName, count);
        homeworkMapper.addHomework(homework);
        int homeworkId = homeworkMapper.getLastInsertId();
        List<Integer> itemList = JsonConvert.parseIntList(item);
        for (Integer itemId : itemList) {
            HomeworkItem homeworkItem = new HomeworkItem(0, homeworkId, itemId);
            try {
                homeworkItemMapper.addHomeworkItem(homeworkItem);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return homeworkMapper.getHomeworkById(homeworkId);
    }

    public List<HomeworkItem> getHomeworkItemByHomeworkId(int homeworkId) {
        return homeworkItemMapper.getHomeworkItemByHomeworkId(homeworkId);
    }

    public int getHomeworkCount() {
        List<Homework> homeworkList = homeworkMapper.getAllHomework();
        for (int i = 0; i < homeworkList.size(); i++) {
            if (homeworkList.get(i).getId() == 0) {
                homeworkList.remove(0);
                break;
            }
        }
        return homeworkList.size();
    }

    public void removeHomeworkById(int id) {
        homeworkMapper.deleteHomeworkById(id);
    }

    public Homework getHomeworkById(int id) {
        return homeworkMapper.getHomeworkById(id);
    }
}
