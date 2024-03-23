package com.mdd.auto_test_back.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LastDoubleInString {
    public static double convert(String input) {
        String regex = "\\d+\\.\\d+";
        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(input);
        double lastDouble = 0.0; // 默认值
        // 从后往前搜索最后一个 double 数字
        while (matcher.find()) {
            lastDouble = Double.parseDouble(matcher.group());
        }
        return lastDouble;
    }
}
