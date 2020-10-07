package com.sn.springboot.ehcache;

import com.sn.springboot.pojo.User;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@CacheConfig(cacheNames = "mycache")
public class EhcacheService {
    @Cacheable(key = "'ehcache_user_'+#id")
    public User getUserById(Long id) {
        System.out.println("new user");
        User user = new User();
        user.setId(1L);
        user.setAge(10);
        user.setName("zhangsan");
        user.setTime(new Date());
        return user;
    }
}
