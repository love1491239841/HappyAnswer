package com.example.administrator.happyanswer.bean;

public class Title {
    private int id;
    private String idch;
    private String title;
    private String optionsa;
    private String optionsb;
    private String optionsc;
    private String optionsd;
    private String theanswer;

    public Title(int id, String idch, String title, String optionsa, String optionsb, String optionsc, String optionsd, String theanswer) {
        this.id = id;
        this.idch = idch;
        this.title = title;
        this.optionsa = optionsa;
        this.optionsb = optionsb;
        this.optionsc = optionsc;
        this.optionsd = optionsd;
        this.theanswer = theanswer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdch() {
        return idch;
    }

    public void setIdch(String idch) {
        this.idch = idch;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOptionsa() {
        return optionsa;
    }

    public void setOptionsa(String optionsa) {
        this.optionsa = optionsa;
    }

    public String getOptionsb() {
        return optionsb;
    }

    public void setOptionsb(String optionsb) {
        this.optionsb = optionsb;
    }

    public String getOptionsc() {
        return optionsc;
    }

    public void setOptionsc(String optionsc) {
        this.optionsc = optionsc;
    }

    public String getOptionsd() {
        return optionsd;
    }

    public void setOptionsd(String optionsd) {
        this.optionsd = optionsd;
    }

    public String getTheanswer() {
        return theanswer;
    }

    public void setTheanswer(String theanswer) {
        this.theanswer = theanswer;
    }
}
