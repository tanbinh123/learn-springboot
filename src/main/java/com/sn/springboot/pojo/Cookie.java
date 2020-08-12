package com.sn.springboot.pojo;

import org.apache.ibatis.type.Alias;

@Alias("cookie")
public class Cookie {
    private Integer id;

    private String cookie;

    private String addTime;

    private String staleTime;

    private Integer status;

    private String loginName;

    private Integer mediaId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getStaleTime() {
        return staleTime;
    }

    public void setStaleTime(String staleTime) {
        this.staleTime = staleTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public Integer getMediaId() {
        return mediaId;
    }

    public void setMediaId(Integer mediaId) {
        this.mediaId = mediaId;
    }
}
