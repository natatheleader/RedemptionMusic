package com.redemption.music.Models;

public class SongData {

    private String title, name, path;

    public SongData(String title, String name, String Path) {
        this.title = title;
        this.name = name;
        this.path = path;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
