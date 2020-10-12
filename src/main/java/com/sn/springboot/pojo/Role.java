package com.sn.springboot.pojo;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Alias("role")
public class Role implements Serializable {
    private static final long serialVersionUID = 1686082419421920171L;

    private Long id;

    private String roleCode;

    private String roleName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
