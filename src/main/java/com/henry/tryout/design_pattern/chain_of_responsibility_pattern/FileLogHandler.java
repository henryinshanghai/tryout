package com.henry.tryout.design_pattern.chain_of_responsibility_pattern;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileLogHandler extends AbstractLogHandler{
    private File logFile;

    // 在构造器中，设置当前处理者 所能够处理的日志等级 - 构造器决定了使用者初始化时需要传入的信息
    public FileLogHandler(File logFile, int level) {
        this.level = level;
        this.logFile = logFile;
    }

    @Override
    protected void write(String message) {
        // try resource 从内存中 写出数据 到文件
        try (BufferedWriter out = new BufferedWriter(new FileWriter(this.logFile))) {
            out.write(message);
            System.out.println("File: write success.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
