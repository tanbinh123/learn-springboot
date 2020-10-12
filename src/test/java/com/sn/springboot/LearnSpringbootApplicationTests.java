package com.sn.springboot;

import com.sn.springboot.aop.CoffeeShop;
import com.sn.springboot.dao.MenuDao;
import com.sn.springboot.dao.UserDao;
import com.sn.springboot.ehcache.EhcacheService;
import com.sn.springboot.mail.MailService;
import com.sn.springboot.pojo.Menu;
import com.sn.springboot.pojo.User;
import com.sn.springboot.properties.DataBaseProperties3;
import com.sn.springboot.redis.RedisService;
import com.sn.springboot.redis.RedisService2;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class LearnSpringbootApplicationTests {

    @Autowired
    DataBaseProperties3 dataBaseProperties;

    @Autowired
    CoffeeShop coffeeShop;

    @Autowired
    RedisService redisService;

    @Autowired
    RedisService2 redisService2;

    @Autowired
    MailService mailService;

    @Autowired
    EhcacheService ehcacheService;

    @Autowired
    UserDao userDao;

    @Autowired
    MenuDao menuDao;

    @Test
    void doTest() {
//        System.out.println(dataBaseProperties.getDriverName());
//        coffeeShop.sale("拿铁");
//        redisService2.deletePiplineKV();
//        mailService.send();
//        System.out.println(ehcacheService.getUserById(1L));
//        System.out.println(ehcacheService.getUserById(1L));

//        PasswordEncoder passwordEncoder = new Pbkdf2PasswordEncoder("qazwsxedc");
//        System.out.println(passwordEncoder.encode("123456"));

//        User u = userDao.getUserByName("zhangsan");
//        System.out.println(u);

        List<Menu> m = menuDao.getAllMenus();
        System.out.println(m);
    }

}
