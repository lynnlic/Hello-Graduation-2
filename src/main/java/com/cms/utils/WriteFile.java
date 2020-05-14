package com.cms.utils;

import java.io.FileOutputStream;
import java.io.IOException;

public class WriteFile {
    public static String writeByFileOutputStream(String outFile, String content) throws IOException {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(outFile);
            fos.write(content.getBytes());
            return "SUCCESS";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "FALSE";
        } finally {
            if (fos != null) {
                fos.close();
                fos = null;
            }
        }
    }
}
