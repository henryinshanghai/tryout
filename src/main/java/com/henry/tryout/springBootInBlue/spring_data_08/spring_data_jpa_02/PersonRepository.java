package com.henry.tryout.springBootInBlue.spring_data_08.spring_data_jpa_02;

import com.henry.tryout.springBootInBlue.spring_data_08.overview_00.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {
    /*
        根据属性名 来 定义查询方法

        特征：
            1 findBy关键字 可以使用 find、read、readby等代替
            2 Like、and这样的查询关键字 - 详见列表
     */
    List<Person> findByAddress(String address);

    Person findByNameAndAddress(String name,String address);

    @Query("select p from Person p where p.name= :name and p.address= :address")

    Person withNameAndAddressQuery(@Param("name")String name, @Param("address")String address);

    Person withNameAndAddressNamedQuery(String name,String address);


}
