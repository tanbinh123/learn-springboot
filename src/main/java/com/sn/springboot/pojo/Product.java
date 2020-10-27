package com.sn.springboot.pojo;

import org.apache.ibatis.type.Alias;

import java.math.BigDecimal;

/**
 * 功能：
 * 作者：SheHuan
 * 时间：2020/10/22 10:58
 */
@Alias("product")
public class Product {
    private Long id;

    private String name;

    private Long stock;

    private Double price;

    private Integer version;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
