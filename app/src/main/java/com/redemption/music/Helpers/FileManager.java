package com.redemption.music.Helpers;

import static com.redemption.music.MainActivity.albumData;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import com.redemption.music.Models.AlbumData;
import com.redemption.music.Models.ArtistData;
import com.redemption.music.Models.GenresData;
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
        ArrayList<String> duplicate = new ArrayList<>();
        ArrayList<SongData> allTracks = new ArrayList<>();
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {
                MediaStore.Audio.Media.ALBUM,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media._ID
        };

        Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String album = cursor.getString(0);
                String title = cursor.getString(1);
                String duration = cursor.getString(2);
                String path = cursor.getString(3);
                String artist = cursor.getString(4);
                String id = cursor.getString(5);

                SongData songData = new SongData(id, title, path, artist, album, duration);
                allTracks.add(songData);
                if (!duplicate.contains(album)) {
                    albumData.add(songData);
                    duplicate.add(album);
                }
            }
            cursor.close();

        }

        return allTracks;
    }

    @SuppressLint("Range")
    public List<ArtistData> getArtists(){
        ArrayList<ArtistData> allArtists = new ArrayList<>();
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {
                MediaStore.Audio.Media.ALBUM,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.ARTIST,
        };

        Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
//                String album = cursor.getString(0);
//                String title = cursor.getString(1);
//                String duration = cursor.getString(2);
                String path = cursor.getString(3);
                String artist = cursor.getString(4);

                if (containsArtist(allArtists, artist)) {

                } else {
                    ArtistData artistData = new ArtistData(artist, path);

                    allArtists.add(artistData);
                }
            }
            cursor.close();

        }

        return allArtists;
    }

    @SuppressLint("Range")
    public List<AlbumData> getAlbum(){
        ArrayList<AlbumData> allAlbum = new ArrayList<>();
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {
                MediaStore.Audio.Media.ALBUM,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.ARTIST,
        };

        Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String album = cursor.getString(0);
//                String title = cursor.getString(1);
//                String duration = cursor.getString(2);
                String path = cursor.getString(3);
//                String artist = cursor.getString(4);

                if (containsAlbum(allAlbum, album)) {

                } else {
                    AlbumData albumData = new AlbumData(album, path);

                    allAlbum.add(albumData);
                }
            }
            cursor.close();

        }

        return allAlbum;
    }

    @SuppressLint("Range")
    public List<GenresData> getGenre(){
        ArrayList<GenresData> allGenre = new ArrayList<>();
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String[] projection = new String[0];
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
            projection = new String[]{
                    MediaStore.Audio.Media.ALBUM,
                    MediaStore.Audio.Media.TITLE,
                    MediaStore.Audio.Media.DURATION,
                    MediaStore.Audio.Media.DATA,
                    MediaStore.Audio.Media.ARTIST,
                    MediaStore.Audio.Media.GENRE,
            };
        }

        Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String album = cursor.getString(0);
//                String title = cursor.getString(1);
//                String duration = cursor.getString(2);
                String path = cursor.getString(3);
//                String artist = cursor.getString(4);
                String genre = cursor.getString(5);

                if (containsGenre(allGenre, genre)) {

                } else {
                    GenresData genreData = new GenresData(album, path);

                    allGenre.add(genreData);
                }
            }
            cursor.close();

        }

        return allGenre;
    }

    boolean containsArtist(ArrayList<ArtistData> list, String name) {
        for (ArtistData item : list) {
            if (item.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    boolean containsAlbum(ArrayList<AlbumData> list, String name) {
        for (AlbumData item : list) {
            if (item.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    boolean containsGenre(ArrayList<GenresData> list, String name) {
        for (GenresData item : list) {
            if (item.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

}
