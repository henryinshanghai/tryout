package com.henry.tryout.springBootInBlue.spring_data_08.spring_data_jpa_02.define_own_repo_01;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.io.Serializable;

// 定义接口的实现 - 作用：？？？
public class CustomRepositoryImpl_02<T, ID extends Serializable>
        extends SimpleJpaRepository<T, ID> // 继承既有的能力
        implements CustomRepository_01<T, ID> { // 实现预设的能力

    // 2 使用entityManager来进行DAO操作
    private final EntityManager entityManager;

    // 3 这里需要一个构造方法，因为它爸爸那里没有默认的构造方法
    public CustomRepositoryImpl_02(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
        this.entityManager = em; // 在构造器中进行初始化操作
    }

    // 4 定义自己的DAO操作
    @Override
    public void doSomething(ID id) {

    }


}
