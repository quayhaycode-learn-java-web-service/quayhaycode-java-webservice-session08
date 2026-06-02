package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
    public static List<User> listUser = new ArrayList<>();
    static {
        listUser.add(new User(1, "ho va ten", 20));
        listUser.add(new User(2, "ho va ten", 20));
        listUser.add(new User(3, "ho va ten", 20));
    }

}
