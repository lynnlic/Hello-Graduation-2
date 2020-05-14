package com.cms.utils;

import sun.misc.BASE64Encoder;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class ReadFile {
    public static String readText(File file) {
        String s = "";
        InputStreamReader in = null;
        try{
            in = new InputStreamReader(new FileInputStream(file),"GB2312");
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

    public static String readImg(String path){
        byte[] data = null;
        path = "file:///" + path;
        try {
            // 创建URL
            URL url = new URL(path);
            // 创建链接
            URLConnection conn =  url.openConnection();
            //conn.setRequestMethod("GET");
            conn.setConnectTimeout(5 * 1000);
            InputStream inStream = conn.getInputStream();
            data = new byte[inStream.available()];
            inStream.read(data);
            inStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        // 返回Base64编码过的字节数组字符串
        return encoder.encode(data);
    }
}
