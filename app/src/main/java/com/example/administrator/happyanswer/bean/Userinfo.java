package com.example.administrator.happyanswer.bean;

public class Userinfo {
    private int id;
    private String username;
    private String phone;
    private String email;
    private String comments;

    public Userinfo(int id, String username, String phone, String email, String comments) {
        this.id = id;
        this.username = username;
        this.phone = phone;
        this.email = email;
        this.comments = comments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
