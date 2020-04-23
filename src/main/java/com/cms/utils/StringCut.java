package com.cms.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCut{
    /**
     * 找到对应的字符串
     * @param str 原字符串
     * @param rgex 正则表达式
     * @return 结果List合集
     */
    public static List getSubString(String str, String rgex){
        Pattern pattern = Pattern.compile(rgex);
        Matcher matcher = pattern.matcher(str);
        List<String> result = new ArrayList<>();
        while(matcher.find()){
            //字符串处理
            result.add(matcher.group());
        }
        return result;
    }
}
