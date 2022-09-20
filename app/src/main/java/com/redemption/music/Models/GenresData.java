package com.redemption.music.Models;

public class GenresData {

    private String name;
    private int imgId;

    public GenresData (String name, int imgId) {
        this.name = name;
        this.imgId = imgId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getImgId() {
        return imgId;
    }
    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

}