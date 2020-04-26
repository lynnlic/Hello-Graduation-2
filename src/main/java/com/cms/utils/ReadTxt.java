package com.cms.utils;

import java.io.*;

public class ReadTxt {
    public static String readText(File file) {
        String s = "";
        InputStreamReader in = null;
        try{
            in = new InputStreamReader(new FileInputStream(file),"gbk");
        } catch (IOException e){
            System.out.println(e);
            return null;
        }
        BufferedReader br = new BufferedReader(in);
        StringBuffer content = new StringBuffer();
        try{
            while ((s=br.readLine())!=null){
                content = content.append(s);
            }
        } catch (IOException e){
            System.out.println(e);
        }

        return content.toString();
    }
}
