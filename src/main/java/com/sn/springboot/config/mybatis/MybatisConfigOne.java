package com.sn.springboot.config.mybatis;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.sql.DataSource;

// 多数据源配置时去掉启动类的MapperScan配置
//@Configuration
//@MapperScan(basePackages = "要扫描的mapper包", sqlSessionFactoryRef = "sqlSessionFactoryOne", sqlSessionTemplateRef = "sqlSessionTemplateOne")
public class MybatisConfigOne {
//    @Resource(name = "dsOne")
//    private DataSource dataSource;
//
//    @Bean
//    public SqlSessionFactory sqlSessionFactoryOne() {
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
//    public SqlSessionTemplate sqlSessionTemplateOne() {
//        return new SqlSessionTemplate(sqlSessionFactoryOne());
//    }
}
