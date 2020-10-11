package com.sn.springboot.security;

import com.sn.springboot.dao.MenuDao;
import com.sn.springboot.dao.UserDao;
import com.sn.springboot.pojo.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {
    @Autowired
    private MenuDao menuDao;

    public List<Menu> getAllMenus() {
        return menuDao.getAllMenus();
    }
}
