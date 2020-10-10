package com.sn.springboot.ehcache;

import com.sn.springboot.pojo.Animal;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@CacheConfig(cacheNames = "mycache")
public class EhcacheService {
    @Cacheable(key = "'ehcache_animal_'+#id")
    public Animal getAnimalById(Long id) {
        System.out.println("new animal");
        Animal animal = new Animal();
        animal.setId(1L);
        animal.setAge(10);
        animal.setName("zhangsan");
        animal.setTime(new Date());
        return animal;
    }
}
