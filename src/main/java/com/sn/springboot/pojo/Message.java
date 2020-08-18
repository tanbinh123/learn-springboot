package com.sn.springboot.pojo;

import java.io.Serializable;

public class Message implements Serializable {
    private static final long serialVersionUID = 4771657861006669852L;

    private String date;
    private String title;

    public Message(String date, String title) {
        this.date = date;
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Message{" +
                "date='" + date + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
