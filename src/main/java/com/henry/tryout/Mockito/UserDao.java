package com.henry.tryout.Mockito;

import org.springframework.stereotype.Component;

@Component
public class UserDao {

    public User getUserById(int id) {
        return new User(id, "henry");

    }


    public int insertUser(User user) {
        return 1;
    }

}
