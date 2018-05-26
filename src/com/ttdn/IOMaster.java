/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ttdn;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author Admin
 */
public class IOMaster {

    private IOMaster() {
    }

    //cho phép đọc file theo encoding utf-8
    public static String readUTF8Text(String file) throws FileNotFoundException, UnsupportedEncodingException, IOException {
        FileInputStream fi = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(fi, "utf-8");
        BufferedReader br = new BufferedReader(isr);
        String sR = "", sNewLine;
        while ((sNewLine = br.readLine()) != null) {
            sR += sNewLine + "\n";
        }
        fi.close();
        return sR;
    }

    //cho phép ghi text vào file có tùy ý append: ghi nối vào dữ liệu cũ hay ko
    public static void writeUTF8Text(String file, String text, boolean append) throws FileNotFoundException, UnsupportedEncodingException, IOException {
        FileOutputStream fo = new FileOutputStream(file, append);
        OutputStreamWriter osw = new OutputStreamWriter(fo, "utf-8");
        osw.write(text);
        osw.flush();
        fo.close();
    }

    //mặc định append=false, tức là mỗi lần lưu sẽ tạo mới file
    public static void writeUTF8Text(String file, String text) throws FileNotFoundException, UnsupportedEncodingException, IOException {
        writeUTF8Text(file, text, false);
    }

}
