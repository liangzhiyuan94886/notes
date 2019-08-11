package com.learn.notes.entity;

public class Notes {
    private Integer id;
    private String nid;
    private String keyword;
    private String content;
//    其它表
    private String tagName;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public String toString() {
        return "Notes{" +
                "id=" + id +
                ", nid='" + nid + '\'' +
                ", keyword='" + keyword + '\'' +
                ", content='" + content + '\'' +
                ", tagName='" + tagName + '\'' +
                '}';
    }
}
