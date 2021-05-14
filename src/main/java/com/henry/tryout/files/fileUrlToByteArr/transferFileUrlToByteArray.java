package com.henry.tryout.files.fileUrlToByteArr;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class transferFileUrlToByteArray {
    public static void main(String[] args) {
        String fileUrl = "https://github.com/henryinshanghai/little_prince/blob/master/chapter01.txt";
        byte[] byteArr = null;
        try {
            byteArr = fetchRemoteFile(fileUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("this is us" + byteArr);
    }

    private static byte[] fetchRemoteFile(String fileUrl) throws IOException {
        URL url = new URL(fileUrl);
        InputStream is = null;
        byte[] bytes = null;
        try {
            is = url.openStream(); // 为什么今天下午这里会报错呢？
            bytes = IOUtils.toByteArray(is);
        } catch (IOException e) {
            //handle errors
        } finally {
            if (is != null) is.close();
        }
        return bytes;
    }


}
