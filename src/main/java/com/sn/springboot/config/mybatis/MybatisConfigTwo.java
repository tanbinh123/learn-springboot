package com.sn.springboot.config.mybatis;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.sql.DataSource;

//@Configuration
//@MapperScan(basePackages = "要扫描的mapper包", sqlSessionFactoryRef = "sqlSessionFactoryTwo", sqlSessionTemplateRef = "sqlSessionTemplateTwo")
public class MybatisConfigTwo {
//    @Resource(name = "dsTwo")
//    private DataSource dataSource;
//
//    @Bean
//    public SqlSessionFactory sqlSessionFactoryTwo() {
//        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
//        bean.setDataSource(dataSource);
//        try {
//            return bean.getObject();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    @Bean
//    public SqlSessionTemplate sqlSessionTemplateTwo() {
//        return new SqlSessionTemplate(sqlSessionFactoryTwo());
//    }
}
