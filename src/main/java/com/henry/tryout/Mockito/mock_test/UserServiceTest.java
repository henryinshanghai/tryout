package com.henry.tryout.Mockito.mock_test;

import com.henry.tryout.Mockito.User;
import com.henry.tryout.Mockito.UserDao;
import com.henry.tryout.Mockito.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserDao userDao;

    @Test
    public void getUserById() throws Exception {
        // mock 有返回值的方法调用 - 手段：Mockito.when().thenReturn()
        // 定义当调用mock userDao的getUserById()方法，并且参数为3时，就返回id为200、name为I'm mock3的user对象
        Mockito.when(userDao.getUserById(3)).thenReturn(new User(200, "I'm mock 3"));

        // mock掉调用方法时需要传入的参数 - 手段：Mockito.anyXxx()
        Mockito.when(userService.getUserById(Mockito.anyInt())).thenReturn(new User(3, "I'm mock"));
        User user1 = userService.getUserById(3); // 回传的user的名字为I'm mock
        User user2 = userService.getUserById(200); // 回传的user的名字也为I'm mock

        // 返回的会是名字为I'm mock 3的user对象
        User user = userService.getUserById(1);

        // mock掉调用方法时需要传入的参数 - 手段：Mockito.anyXxx()
        Mockito.when(userService.insertUser(Mockito.any(User.class))).thenReturn(100);
        Integer i = userService.insertUser(new User()); //会返回100

        // mock方法抛出一个异常 - 手段：Mockito.when().thenThrow()
        Mockito.when(userService.getUserById(9)).thenThrow(new RuntimeException("mock throw exception"));
        User user3 = userService.getUserById(9); //会抛出一个RuntimeException

        // 特征：如果方法没有返回值的话，需要使用 doThrow()方法
        // 语法：Mockito.doThrow().when(A).b()
        Mockito.doThrow(new RuntimeException("mock throw exception")).when(userService).print();
        userService.print(); //会抛出一个RuntimeException

        // 检查 特定方法的调用次数 - 手段：Mockito.verify(A, times).b()
        // demo: getUserById(3)的调用次数是否为1次
        Mockito.verify(userService, Mockito.times(1)).getUserById(Mockito.eq(3)) ;
        Assert.assertNotNull(user);
        Assert.assertEquals(user.getId(), new Integer(200));
        Assert.assertEquals(user.getName(), "I'm mock 3");
    }
}
/*
Mockito的限制：
1 不能Mock静态方法？
2 不能mock 私有方法
3 不能mock final class

 */
