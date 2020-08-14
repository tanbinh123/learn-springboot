package com.sn.springboot.pojo;

public enum SexEnum {
    MALE(1, "男"),
    FEMALE(2, "女");

    private int code;
    private String name;

    SexEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static SexEnum getEnumById(int id) {
        for (SexEnum sexEnum : SexEnum.values()) {
            if (sexEnum.getId() == id) {
                return sexEnum;
            }
        }

        return null;
    }

    public int getId() {
        return code;
    }

    public void setId(int id) {
        this.code = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
