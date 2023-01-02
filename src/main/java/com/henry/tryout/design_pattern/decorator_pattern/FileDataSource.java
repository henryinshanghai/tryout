package com.henry.tryout.design_pattern.decorator_pattern;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;

// #3 定义一个具体组件类，并 声明其基础行为；
public class FileDataSource implements DataSource {

    // 文件名
    private String fileName;

    public FileDataSource(String fileName) {
        this.fileName = fileName;
    }

    // 从内存中写出到文件
    @Override
    public void write(String data) {
        File file = new File(fileName);
        try (OutputStream fos = new FileOutputStream(file)) {
            fos.write(data.getBytes(), 0, data.length());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // 读取文件 到内存中
    @Override
    public String read() {
        File file = new File(fileName);
        char[] buffer = null;
        try (FileReader fileReader = new FileReader(file)) {
            // 成块地读入内存
            buffer = new char[(int) file.length()];
            fileReader.read(buffer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return buffer != null ? new String(buffer) : null;
    }
}
