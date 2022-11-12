package com.henry.tryout.software_design.model_of_a_software;

public class ArticleServiceWithoutNew {
    private ArticleRepository repository;

    // 构造器只是完成了参数到变量的绑定，而没有初始化repository
    public ArticleServiceWithoutNew(final ArticleRepository repository) {
        this.repository = repository;
    }
}
