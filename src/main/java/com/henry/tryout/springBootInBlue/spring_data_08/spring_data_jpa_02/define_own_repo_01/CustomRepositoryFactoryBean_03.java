package com.henry.tryout.springBootInBlue.spring_data_08.spring_data_jpa_02.define_own_repo_01;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.persistence.EntityManager;
import java.io.Serializable;

// 定义一个工厂Bean - 作用：？？？
public class CustomRepositoryFactoryBean_03<T extends JpaRepository<S, ID>, S, ID extends Serializable>
        extends JpaRepositoryFactoryBean<T, S, ID> {// 1 自定义的FactoryBean继承自 JpaRepoFactoryBean


    public CustomRepositoryFactoryBean_03(Class<? extends T> repositoryInterface) {
        super(repositoryInterface);
    }

    // 2 重写 createRepoFactory()方法 - 使用 CustomRepoFactory 来 创建实例
    @Override
    protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
        return new CustomRepositoryFactory(entityManager);
    }

    // 3 创建 CustomRepoFactory， 继承自 JpaRepoFactory
    private static class CustomRepositoryFactory extends JpaRepositoryFactory {

        public CustomRepositoryFactory(EntityManager entityManager) {
            super(entityManager);
        }


        // 4 重写 getTargetRepo()方法 - 获取到自定义的CustomRepo的实现类
//        @Override
//        @SuppressWarnings({"unchecked"})
//        protected <T, ID extends Serializable> SimpleJpaRepository<?, ?> getTargetRepository(
//                RepositoryInformation information, EntityManager entityManager) {
//            return new CustomRepositoryImpl_02<>((Class<T>) information.getDomainType(), entityManager);
//
//        }

        // 5 重写 getRepoBaseClass()方法 - 获取到自定义的CustomRepo实现类的Class对象
        @Override
        protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
            return CustomRepositoryImpl_02.class;
        }
    }

}
