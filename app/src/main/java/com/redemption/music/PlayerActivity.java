package com.redemption.music;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class PlayerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        String name = getIntent().getStringExtra("name");
        Toast.makeText(this, name, Toast.LENGTH_SHORT).show();

//        if (newSongData.getPath() != null) {
//            byte[] image = getAlbumArt(newSongData.getPath());
//            if (image != null) {
//                Glide.with(holder.cover.getContext()).asBitmap()
//                        .load(image)
//                        .into(holder.cover);
//            } else {
//                Glide.with(holder.cover.getContext())
//                        .load(R.drawable.playlist)
//                        .into(holder.cover);
//            }
//        } else  {
//            Glide.with(holder.cover.getContext())
//                    .load(R.drawable.playlist)
//                    .into(holder.cover);
//        }
    }
}