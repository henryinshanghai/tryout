package com.henry.tryout.springBootInBlue.spring_data_08.overview_00;


import org.springframework.data.repository.Repository;

import java.util.List;

public interface PersonRepository extends Repository<Person, Long> {

    // 按照年龄进行计数
    Long countByAge(Integer age);

    // 按照名字进行删除
    Long deleteByName(String name);

    // 按照名字进行查询
    List<Person> findByName(String name);

    // 按照名字和地址进行查询
    List<Person> findByNameAndAddress(String name, String address);
}
