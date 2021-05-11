package com.henry.tryout.springBootInBlue.spring_data_08.spring_data_jpa_02.define_own_repo_01;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;

// 0 定义一个接口 - 作用：？？？
@NoRepositoryBean // 1 声明此接口不是 领域类的接口(PersonRepo)
public interface CustomRepository_01<T, ID extends Serializable>
		extends PagingAndSortingRepository<T, ID> { // 2 实现 PagingAndSortingRepository接口,继承能力

	// 3 定义所需要的数据库操作方法
	public void doSomething(ID id);
	

}
