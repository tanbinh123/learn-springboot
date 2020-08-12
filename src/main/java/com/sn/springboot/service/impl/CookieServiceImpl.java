package com.sn.springboot.service.impl;

import com.sn.springboot.dao.CookieDao;
import com.sn.springboot.pojo.Cookie;
import com.sn.springboot.service.CookieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CookieServiceImpl implements CookieService {
    @Autowired
    CookieDao cookieDao;

    @Override
    public Cookie getCookieById(Integer id) {
        return cookieDao.getCookieById(id);
    }
}
