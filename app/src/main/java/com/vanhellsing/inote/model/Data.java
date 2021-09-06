package com.vanhellsing.inote.model;

public class Data {
    private String id, title, desc;

    public Data(){

    }

    public Data(String id, String title, String desc){
        this.id = id;
        this.title = title;
        this.desc = desc;
    }

    //Getter
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    //Setter
    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
