package com.example.administrator.happyanswer.bean;

public class News {
    private int id;
    private String title;
    private String time;
    private String content;
    private String img;

    public News(int id, String title, String time, String content, String img) {
        this.id = id;
        this.title = title;
        this.time = time;
        this.content = content;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
