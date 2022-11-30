package com.henry.tryout.files.fileUrlToByteArr;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

// 工具：从远端文件URL中读取出二进制数组
public class transferFileUrlToByteArray {
    public static void main(String[] args) {
        String fileUrl = "https://github.com/henryinshanghai/little_prince/blob/master/chapter01.txt";
        byte[] byteArr = null;
        try {
            byteArr = fetchRemoteFile(fileUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 这里的byteArr打印出来是一个 SHA01
        System.out.println("this is us" + byteArr);
    }

    private static byte[] fetchRemoteFile(String fileUrl) throws IOException {
        // #1 使用url初始化得到 URL对象
        URL url = new URL(fileUrl);
        InputStream is = null;
        byte[] bytes = null;
        try {
            // #2 从URL对象中，打开流
            is = url.openStream();
            // #3 使用IOUtils 的静态方法 toByteArray() 来 把流转化成为byte[]
            bytes = IOUtils.toByteArray(is);
        } catch (IOException e) {
            //handle errors
        } finally {
            if (is != null) is.close();
        }
        return bytes;
    }


}
