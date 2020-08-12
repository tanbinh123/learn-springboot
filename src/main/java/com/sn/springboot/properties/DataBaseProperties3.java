package com.sn.springboot.properties;

import com.sn.springboot.condition.DataBaseConditional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

@Component
@Conditional(value = DataBaseConditional.class)
public class DataBaseProperties3 {
    private String driverName;

    private String url;

    private String username;

    private String password;

    public DataBaseProperties3(@Value("${spring.datasource.driver-class-name}") String driverName,
                               @Value("${spring.datasource.url}") String url,
                               @Value("${spring.datasource.username}") String username,
                               @Value("${spring.datasource.password}") String password) {
        this.driverName = driverName;
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
