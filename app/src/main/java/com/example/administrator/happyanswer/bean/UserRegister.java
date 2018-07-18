package com.example.administrator.happyanswer.bean;

public class UserRegister {
    private String username;
    private String password;
    private String twopassword;
    private String addtime;

    public UserRegister(String username, String password, String twopassword, String addtime) {
        this.username = username;
        this.password = password;
        this.twopassword = twopassword;
        this.addtime = addtime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTwopassword() {
        return twopassword;
    }

    public void setTwopassword(String twopassword) {
        this.twopassword = twopassword;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }
}
