package com.henry.tryout.design_pattern.decorator_pattern;

public class ClientUsingDecoratorPattern {
    public static void main(String[] args) {

        // E:\development_project\tryout
        String fileProperty = System.getProperty("user.dir");

        String letters = "A、B、C、D、E、F、G、H、I、J、K、L、M、N、O、P、Q、R、S、T、U、V、W、X、Y、Z";

        // 创建所需要的装饰器
        FileDataSource fileDataSource = new FileDataSource(fileProperty +
                "/src/main/java/com/henry/tryout/design_pattern/decorator_pattern/" + "letter.text");
        DataSourceBaseDecorator encryptionDecorator
                = new EncryptionDecorator(fileDataSource);

        // 使用装饰器 来 执行写入内容到指定文件
        encryptionDecorator.write(letters);

        // 打印字母
        System.out.println("--- Inputted Content: ---");
        System.out.println(letters);

        // 打印写入到文件中的内容
        System.out.println("--- encoded content: ---");
        System.out.println(fileDataSource.read()); // 使用原始对象来read

        // 打印文件解密后的内容
        System.out.println("--- decoded content: ---");
        System.out.println(encryptionDecorator.read()); // 使用装饰器对象 来 read
    }
}
