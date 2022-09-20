package com.redemption.music.Models;

public class SongData {

    private String title, name;
    private int imgId;

    public SongData(String title, String name, int imgId) {
        this.title = title;
        this.name = name;
        this.imgId = imgId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
