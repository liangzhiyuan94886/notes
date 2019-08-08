package com.learn.notes.entity;

public class Notes {
    private Integer id;
    private Integer type;
    private String describe;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    @Override
    public String toString() {
        return "Notes{" +
                "id=" + id +
                ", type=" + type +
                ", describe='" + describe + '\'' +
                '}';
    }
}
