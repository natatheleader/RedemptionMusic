package com.redemption.music.Models;

import ir.mirrajabi.searchdialog.core.Searchable;

public class Music implements Searchable {
    private String mTitle;

    public Music(String title) {
        mTitle = title;
    }

    @Override
    public String getTitle() {
        return mTitle;
    }

    public Music setTitle(String title) {
        mTitle = title;
        return this;
    }
}
