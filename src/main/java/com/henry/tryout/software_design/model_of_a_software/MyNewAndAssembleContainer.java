package com.henry.tryout.software_design.model_of_a_software;

// 验证DI容器的作用 - 管理对象之间的依赖
// 特征：被依赖对象 会被注入到目标对象中，因此叫做 DI(Dependency Injection)容器
public class MyNewAndAssembleContainer {
    public void assembleObjects() {
        // todo 不知道这里需要引入什么Container，暂时先注掉
//        Container container = new Container();
//        container.bind(Connection.class).to(connection);
//        container.bind(ArticleRepository.class).to(DBArticleRepository.class);
//        container.bind(ArticleServiceWithoutNew.class).to(ArticleServiceWithoutNew.class);
//
//        ArticleServiceWithoutNew articleService = container.getInstance(ArticleServiceWithoutNew.class);
    }
}

