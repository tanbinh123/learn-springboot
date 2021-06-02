package com.sn.springboot.jpa;

import com.sn.springboot.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * description：
 * author：SheHuan
 * time：2021/6/2 17:37
 */
public interface UserDao extends JpaRepository<User, Long> {

}
