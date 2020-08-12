package com.sn.springboot.dao;

import com.sn.springboot.pojo.Cookie;
import org.springframework.stereotype.Repository;

@Repository
public interface CookieDao {
    public Cookie getCookieById(Integer id);
}
