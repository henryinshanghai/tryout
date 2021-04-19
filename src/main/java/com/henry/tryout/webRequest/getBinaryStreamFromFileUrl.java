package com.henry.tryout.webRequest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.*;

public class getBinaryStreamFromFileUrl {

    public static void downLoadPhoto(String photoUrl) throws Exception {

        //获取照片 返回二进制流
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();

        ResponseEntity<byte[]> entity =
                restTemplate.exchange(photoUrl,
                        HttpMethod.GET,
                        new HttpEntity<>(headers),
                        byte[].class);
        byte[] body = entity.getBody();
//        InputStream sbs = new ByteArrayInputStream(body);

        File dest = new File("E:/develop/tryout/src/main/java/com/henry/tryout/webRequest/a.jpg");
        if (!dest.exists()) {
            dest.createNewFile();
        }

        InputStream in = null;
        OutputStream out = null;

        in = new ByteArrayInputStream(body);
        out = new FileOutputStream(dest);

        byte[] bt = new byte[1024];
        int length = 0;
        while ((length = in.read(bt)) != -1) {
            //一次性写入文件a中
            out.write(bt, 0, length);
        }

        if (null != out) {
            out.close();
        }

    }

    public static void main(String[] args) throws Exception {
        String imageUrl = "https://pics0.baidu.com/feed/29381f30e924b8997c407e5ed6c4409d0b7bf6a7.jpeg?token=caa2b49ca451ffc530a319acb9d4711c";

        downLoadPhoto(imageUrl);
    }
}
