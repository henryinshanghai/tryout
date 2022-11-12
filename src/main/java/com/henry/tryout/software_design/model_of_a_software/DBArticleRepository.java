package com.henry.tryout.software_design.model_of_a_software;

import java.sql.Connection;

public class DBArticleRepository implements ArticleRepository{
    private Connection connection;

    public DBArticleRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Article findByTitle(String title) {
        return null;
    }
}
