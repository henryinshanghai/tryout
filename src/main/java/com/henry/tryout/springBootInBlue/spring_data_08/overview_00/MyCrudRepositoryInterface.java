package com.henry.tryout.springBootInBlue.spring_data_08.overview_00;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.util.Optional;

@NoRepositoryBean
// 作为 Repository接口的子接口， CrudRepository接口中定义了 CRUD的抽象方法
public interface MyCrudRepositoryInterface<T, ID> extends Repository<T, ID> {

    <S extends T> S save(S var1);

    <S extends T> Iterable<S> saveAll(Iterable<S> var1);

    Optional<T> findById(ID var1);

    boolean existsById(ID var1);

    Iterable<T> findAll();

    Iterable<T> findAllById(Iterable<ID> var1);

    long count();

    void deleteById(ID var1);

    void delete(T var1);

    void deleteAll(Iterable<? extends T> var1);

    void deleteAll();

}
