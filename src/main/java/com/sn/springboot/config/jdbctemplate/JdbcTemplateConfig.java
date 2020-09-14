package com.sn.springboot.config.jdbctemplate;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

//@Configuration
public class JdbcTemplateConfig {
//    @Bean
//    public JdbcTemplate jdbcTemplateOne(@Qualifier("dsOne") DataSource dsOne) {
//        return new JdbcTemplate(dsOne);
//    }
//
//    @Bean
//    public JdbcTemplate jdbcTemplateTwo(@Qualifier("dsTwo") DataSource dsTwo) {
//        return new JdbcTemplate(dsTwo);
//    }
}
