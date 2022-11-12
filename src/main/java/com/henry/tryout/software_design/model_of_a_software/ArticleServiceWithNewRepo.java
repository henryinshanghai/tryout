package com.henry.tryout.software_design.model_of_a_software;

import java.sql.Connection;

public class ArticleServiceWithNewRepo {

    // service依赖于repository
    // 手段：把repository作为service的成员变量, 然后在构造器中初始化它
    private ArticleRepository repository;

    // 数据库对象 使用共享的数据库连接
    // 手段：传入数据库连接对象作为构造器的参数
    public ArticleServiceWithNewRepo(final Connection connection) {
        this.repository = new DBArticleRepository(connection);
    }

    // 根据标题查询文章
    Article findByTitle(final String title) {
        return null;
    }
}
