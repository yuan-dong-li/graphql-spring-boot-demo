package org.example.app.dao;

import org.example.app.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserQueryRepository {
    public User getUser(int id) {
        User user = new User();
        user.setId(id);
        user.setAge(id + 15);
        user.setSex(id % 2);
        user.setName("Name_" + id);
        user.setPic("Pic_" + id + ".jpg");
        return user;
    }

    public List<User> getUsers(int page, int size, String name) {
        List<User> users = new ArrayList<>(size);
        for(int i=0; i<size; i++) {
            User user = new User();
            user.setId(i);
            user.setAge(i + 15);
            user.setSex(i % 2);
            user.setName(name + "_" + page + "_" + i);
            user.setPic("Pic_" + i + ".jpg");

            users.add(user);
        }
        return users;
    }
}
