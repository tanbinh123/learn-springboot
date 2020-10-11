package com.sn.springboot.pojo;

import org.apache.ibatis.type.Alias;

import java.util.List;

@Alias("menu")
public class Menu {
    private Long id;

    private String pattern;

    private List<Role> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
