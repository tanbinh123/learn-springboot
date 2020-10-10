package com.sn.springboot.controller;

import com.sn.springboot.pojo.Animal;
import com.sn.springboot.redis.cache.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 功能：
 * 作者：SheHuan
 * 时间：2020/9/30 11:16
 */
@RestController
@RequestMapping("/animal")
public class UserController {
    @Autowired
    private AnimalService animalService;

    @PostMapping("/addAnimal")
    public Animal addUserAnimal(Animal animal) {
        return animalService.addAnimal(animal);
    }

    @GetMapping("/getAnimalById")
    public Animal getAnimalById(Long id) {
        return animalService.getAnimalById(id);
    }

    @GetMapping("/getAnimals")
    public List<Animal> getAnimals(String name, Integer age) {
        return animalService.getAnimals(name, age);
    }

    @PostMapping("/updateAnimal")
    public String updateAnimal(Animal animal) {
        Animal u = animalService.updateAnimal(animal);
        return u == null ? "更新失败" : "更新成功";
    }

    @GetMapping("/deleteAnimalById")
    public String deleteAnimalById(Long id) {
        int row = animalService.deleteAnimalById(id);
        return row > 0 ? "删除成功" : "删除失败";
    }
}
