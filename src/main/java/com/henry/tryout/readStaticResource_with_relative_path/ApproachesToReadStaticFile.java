package com.henry.tryout.readStaticResource_with_relative_path;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

// 验证：在代码中指定 静态文件的路径时，应该拷贝 path from content root
public class ApproachesToReadStaticFile {
    public static void main(String[] args) throws IOException {
        System.out.println("***** using approach01: File ********");
        usingFileAsFirstStep();

        System.out.println("***** using approach02: xxx ********");
    }

    // 使用File作为基础 来 读取到一个stream 或者 byte[] - 一旦读取到这样的结果，就能够有多种可用的方式对之进行后续处理
    private static void usingFileAsFirstStep() throws IOException {
        usingFileToReadToStream();
        System.out.println("---------- golden separate line ------------");
        usingFileToReadToByteArray();
    }


    private static void usingFileToReadToStream() throws IOException {
        // 绝对路径 - 可行
//        String filePathAbusolutePath = "E:\\develop\\tryout\\src\\main\\resources\\statics\\poem.pdf";

        // 在 copy relative path 时，IDEA提供的相对路径是 - statics/poem.pdf
        /*
            String filePath = "statics/poem.pdf";
            这种方式会报错 - java.io.FileNotFoundException: statics\poem.pdf (系统找不到指定的路径。)
            特征：报错中给出的路径信息是反斜杠
         */

        // 尝试在相对路径前，添加一个/ - 表示 /resources目录
        /*
            代码：String filePath = "/statics/poem.pdf";
            报错：FileNotFoundException: \statics\poem.pdf (系统找不到指定的路径。)
         */

        // 尝试从 /src目录开始，使用相对目录来 在java代码中引用
        /*
            路径：src/main/resources/statics/poem.pdf
            java代码：String filePath = "src/main/resources/statics/poem.pdf";
            结果：可用
         */

        // 尝试在路径前面添加一个/
        /*
            路径：/src/main/resources/statics/poem.pdf
            结果：java.io.FileNotFoundException: \src\main\resources\statics\poem.pdf (系统找不到指定的路径。)
         */
        String filePath = "src/main/resources/statics/poem.pdf";


//        File file = new File(filePathAbusolutePath);
        File file = new File(filePath);

        // 从File对象中，得到一个 Stream输入流对象
        FileInputStream fis = new FileInputStream(file);

        System.out.println(fis.available());
    }

    // 尝试从File对象中得到一个 byte[]对象 - 直接使用正确的相对路径
    private static void usingFileToReadToByteArray() throws IOException {
        String filePath = "src/main/resources/statics/poem.pdf";

        // 手段1：使用 nio包中的Files工具类 来 获取到byte[]
        // 结果：能够正确地转化成为byte[]数据
//        byte[] bytes = Files.readAllBytes(Paths.get(filePath));

        // 手段2：使用 IOUtils工具类 来 获取到byte[]
        // 结果：能够正确地转化成为byte[]数据
        byte[] bytes = IOUtils.toByteArray(new FileInputStream(filePath));

        System.out.println("字符数组的长度为： " + bytes.length);
    }

}
