package com.henry.tryout.springBootInBlue.spring_data_08.overview_00;

import org.springframework.stereotype.Indexed;

@Indexed
public interface MyRepositoryInterface<T, ID> { // 类型参数列表：领域类、领域类的ID类型

}