package com.sn.springboot.redis.cache;

import com.sn.springboot.pojo.User;

import java.util.List;

public interface UserService {
    User addUser(User user);

    User getUserById(Long id);

    List<User> getUsers(String name, Integer age);

    User updateUser(User user);

    int deleteUserById(Long id);
}
