package com.henry.tryout.springBootInBlue.spring_data_08.overview_00;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
// 在 PagingAndSortingRepository 子接口中，提供了分页与排序的相关操作
public interface MyPagingAndSortingRepositoryInterface<T, ID> extends CrudRepository<T, ID> {

    Iterable<T> findAll(Sort var1);

    Page<T> findAll(Pageable var1);
}
