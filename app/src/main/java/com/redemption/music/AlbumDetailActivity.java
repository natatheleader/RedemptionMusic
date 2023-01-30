package com.redemption.music;

import static com.redemption.music.MainActivity.songData;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;

import com.redemption.music.Models.SongData;

import java.util.ArrayList;

public class AlbumDetailActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ImageView albumPhoto;
    String albumName;
    ArrayList<SongData> albumSongs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_detail);

        recyclerView = findViewById(R.id.album_detail_recycler);
        albumPhoto = findViewById(R.id.album_detail_image);
        albumName = getIntent().getStringExtra("albumName");

        int j = 0;
        for (int i = 0; i < songData.size(); i++) {
            if (albumName.equals(songData.get(i).getAlbum())) {
                albumSongs.add(j, songData.get(i));
                j++;
            }
        }

    }
}