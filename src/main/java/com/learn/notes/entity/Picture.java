package com.learn.notes.entity;

public class Picture {
    private Integer id;
    private String picName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName;
    }

    @Override
    public String toString() {
        return "Picture{" +
                "id=" + id +
                ", picName='" + picName + '\'' +
                '}';
    }
}
