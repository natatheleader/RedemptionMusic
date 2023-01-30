package com.redemption.music.Models;

public class SongData {

    private String id, title, path, artist, album, duration;

    public SongData(String id, String title, String path, String artist, String album, String duration) {
        this.id = id;
        this.title = title;
        this.path = path;
        this.artist = artist;
        this.album = album;
        this.duration = duration;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
