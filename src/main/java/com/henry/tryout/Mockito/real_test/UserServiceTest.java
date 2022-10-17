package com.henry.tryout.Mockito.real_test;

import com.henry.tryout.Mockito.User;
import com.henry.tryout.Mockito.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    //先普通的注入一个userService bean
    @Autowired
    private UserService userService;

    @Test
    public void getUserById() throws Exception {
        //普通的使用userService，他里面会再去调用userDao取得数据库的数据
        User user = userService.getUserById(1);

        //检查结果
        Assert.assertNotNull(user);
        Assert.assertEquals(user.getId(), new Integer(1));
        Assert.assertEquals(user.getName(), "John");
    }
}
