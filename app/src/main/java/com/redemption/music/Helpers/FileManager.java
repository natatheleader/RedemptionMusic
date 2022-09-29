package com.redemption.music.Helpers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import com.redemption.music.Models.SongData;

import java.util.ArrayList;
import java.util.List;

public class FileManager {

    Context context;

    public FileManager(Context context) {
        this.context = context;
    }

    @SuppressLint("Range")
    public List<SongData> getTracks(){
        ArrayList<SongData> allTracks = new ArrayList<SongData>();
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {
                MediaStore.Audio.Media.ALBUM,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.ARTIST
        };

        Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String album = cursor.getString(0);
                String title = cursor.getString(1);
                String duration = cursor.getString(2);
                String path = cursor.getString(3);
                String artist = cursor.getString(4);

                SongData songData = new SongData(title, artist, path);

                Log.e("path : " + path, "album : " + album);
                allTracks.add(songData);
            }
            cursor.close();
//            if (cursor.moveToFirst()) {
//                do {
//                    String name;
//                    name = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
//                    String artist;
//                    artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
//                    int x = cursor.getColumnIndex(android.provider.MediaStore.Audio.Albums.ALBUM_ART);
//                    String thisArt = cursor.getString(x);
//                    String img;
//                    img = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM_ART));
////                    img = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
//
//                    allTracks.add(new SongData(name, artist, thisArt));
//
//                } while (cursor.moveToNext());
//            }
//            cursor.close();
        }

        return allTracks;
    }
}
