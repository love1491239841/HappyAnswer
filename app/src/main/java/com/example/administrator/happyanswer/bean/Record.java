package com.example.administrator.happyanswer.bean;

public class Record {
    private String username;
    private String titlenumber;

    public Record(String username, String titlenumber) {
        this.username = username;
        this.titlenumber = titlenumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitlenumber() {
        return titlenumber;
    }

    public void setTitlenumber(String titlenumber) {
        this.titlenumber = titlenumber;
    }
}
