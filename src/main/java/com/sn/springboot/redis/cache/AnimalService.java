package com.sn.springboot.redis.cache;

import com.sn.springboot.pojo.Animal;

import java.util.List;

public interface AnimalService {
    Animal addAnimal(Animal user);

    Animal getAnimalById(Long id);

    List<Animal> getAnimals(String name, Integer age);

    Animal updateAnimal(Animal user);

    int deleteAnimalById(Long id);
}
