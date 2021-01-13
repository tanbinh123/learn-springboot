package com.sn.springboot;

import com.sn.springboot.aop.GreetAspect;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableScheduling
@EnableAsync
@PropertySource(value = {"classpath:jdbc.properties"}, ignoreResourceNotFound = true)
// 扫描MyBatis Mapper的包
@MapperScan(basePackages = "com.sn.springboot.dao", annotationClass = Repository.class)
// 开启Spring缓存
@EnableCaching
// 开启Spring安全认证
@EnableWebSecurity
public class LearnSpringbootApplication implements WebMvcConfigurer {

    @Bean
    public GreetAspect initGreetAspect() {
        return new GreetAspect();
    }

//    @Bean
//    public MapperScannerConfigurer mapperScannerConfigurer() {
//        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
//        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
//        mapperScannerConfigurer.setBasePackage("com.sn.springboot.dao");
//        mapperScannerConfigurer.setAnnotationClass(Repository.class);
//        return mapperScannerConfigurer;
//    }

    public static void main(String[] args) {
        SpringApplication.run(LearnSpringbootApplication.class, args);
    }

}
