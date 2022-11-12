package com.henry.tryout.software_design.model_of_a_software;

public interface ArticleRepository {
    // 在持久化存储(可以是数据库，也可以是别的)中，根据标题查询文章
    Article findByTitle(final String title);
}
