package com.sn.springboot.jpa;

import com.sn.springboot.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;

/**
 * description：
 * author：SheHuan
 * time：2021/6/2 17:44
 */
public class UserService {
    @Autowired
    UserDao userDao;

    public void testUserDao(){
        User user = new User();
        user.setUsername("张三");
        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("username", ExampleMatcher.GenericPropertyMatchers.exact());
        Example<User> example = Example.of(user, matcher);
        PageRequest pageRequest = PageRequest.of(1, 10);
        Page<User> all = userDao.findAll(example, pageRequest);
    }
}
