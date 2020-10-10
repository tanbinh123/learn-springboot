package com.sn.springboot.dao;

import com.sn.springboot.pojo.Animal;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalDao {
    Animal getAnimalById(Long id);

    List<Animal> getAnimals(String name, Integer age);

    int addAnimal(Animal user);

    int updateAnimal(Animal user);

    int deleteAnimalById(Long id);
}
