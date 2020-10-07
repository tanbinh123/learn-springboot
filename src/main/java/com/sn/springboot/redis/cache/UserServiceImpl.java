package com.sn.springboot.redis.cache;

import com.sn.springboot.dao.UserDao;
import com.sn.springboot.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * keys *：查询Redis中存在的键值对
 * get key：查询指定key对应的value
 * del key：删除指定key对应的键值对
 * ttl key：查询指定key剩余的超时秒数
 */
@Service
// 统一配置缓存名称
@CacheConfig(cacheNames = "redisCache")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    /**
     * @CachePut 表示将方法的返回结果进行缓存，默认情况方法的参数值就是key
     * value = "redisCache"，表示缓存名称，和配置文件的spring.cache.cache-names对应
     * key = "'redis_user_'+#result.id"，采用Spring EL定义要缓存的值对应的键
     */
    @Override
//    @CachePut(value = "redisCache", key = "'redis_user_'+#result.id")
    @CachePut(key = "'redis_user_'+#result.id")
    public User addUser(User user) {
        user.setTime(new Date());
        userDao.addUser(user);
        return user;
    }

    /**
     * @Cacheable 表示先从缓存中查询键对应的值，如果查询到了则返回，否则执行该方法来返回数据，并将返回值进行缓存
     */
    @Override
    @Cacheable(key = "'redis_user_'+#id")
    public User getUserById(Long id) {
        User user = userDao.getUserById(id);
        if (user == null) {
            return null;
        }
        return user;
    }

    @Override
    public List<User> getUsers(String name, Integer age) {
        return userDao.getUsers(name, age);
    }

    /**
     * condition = "result != null"，表示如果查询结果为null，则不缓存结果
     */
    @Override
    @CachePut(key = "'redis_user_'+#user.id", condition = "#result != null")
    public User updateUser(User user) {
        User u = userDao.getUserById(user.getId());
        if (u == null) {
            return null;
        }
        user.setTime(new Date());
        userDao.updateUser(user);
        u = userDao.getUserById(user.getId());
        return u;
    }

    /**
     * @CacheEvict 表示通过定义的键移除缓存
     * beforeInvocation = false，表示在方法执行之后移除缓存，也是默认的配置
     */
    @Override
    @CacheEvict(key = "'redis_user_'+#id", beforeInvocation = false)
    public int deleteUserById(Long id) {
        return userDao.deleteUserById(id);
    }
}
