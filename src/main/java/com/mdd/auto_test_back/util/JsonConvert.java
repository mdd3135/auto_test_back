package com.mdd.auto_test_back.util;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonConvert {
    public static List<Integer> parseIntList(String jsonString) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            TypeReference<List<Integer>> listType = new TypeReference<List<Integer>>() {
            };
            List<Integer> jacsonList = objectMapper.readValue(jsonString, listType);
            return jacsonList;
        } catch (Exception e) {
            return new ArrayList<Integer>();
        }
    }

    public static List<String> parseStringList(String jsonString) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(jsonString, new TypeReference<List<String>>() {
            });
        } catch (Exception e) {
            return new ArrayList<String>();
        }

    }
}
