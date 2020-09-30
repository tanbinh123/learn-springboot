package com.sn.springboot.dao;

import com.sn.springboot.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    User getUserById(Long id);

    List<User> getUsers(String name, Integer age);

    int addUser(User user);

    int updateUser(User user);

    int deleteUserById(Long id);
}
