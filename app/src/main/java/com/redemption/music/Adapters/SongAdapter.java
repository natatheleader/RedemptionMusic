package com.redemption.music.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.redemption.music.Models.SongData;
import com.redemption.music.PlayerActivity;
import com.redemption.music.R;

import java.util.ArrayList;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.MyViewHolder> {

    private ArrayList<SongData> songData;
    private Context mContext;

    public SongAdapter(Context mContext, ArrayList<SongData> songData) {
        this.mContext = mContext;
        this.songData = songData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.song_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.title.setText(songData.get(position).getTitle());
        holder.name.setText(songData.get(position).getArtist());

        byte[] image = getAlbumArt(songData.get(position).getPath());
        if (image != null) {
            Glide.with(mContext).asBitmap()
                    .load(image)
                    .into(holder.cover);
        } else {
            Glide.with(mContext)
                    .load(R.drawable.playlist)
                    .into(holder.cover);
        }

        holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                holder.like.setColorFilter(ActivityCompat.getColor(view.getContext(), R.color.maximum_yellow_red));
                Toast.makeText(view.getContext(),"like item: " + songData.get(position).getTitle(), Toast.LENGTH_LONG).show();
            }
        });

        holder.menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"menu item: " + songData.get(position).getTitle(), Toast.LENGTH_LONG).show();
            }
        });

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, PlayerActivity.class);
                intent.putExtra("position", position);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return songData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView cover, like, menu;
        public TextView title, name;
        public ConstraintLayout layout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            this.cover = (ImageView) itemView.findViewById(R.id.songImage);
            this.menu = (ImageView) itemView.findViewById(R.id.songMenu);
            this.like = (ImageView) itemView.findViewById(R.id.songLike);
            this.title = (TextView) itemView.findViewById(R.id.songTitle);
            this.name = (TextView) itemView.findViewById(R.id.artistNameSong);
            this.layout = (ConstraintLayout) itemView.findViewById(R.id.songLayout);
        }
    }

    private byte[] getAlbumArt(String uri) {
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        retriever.setDataSource(uri);

        byte[] art = retriever.getEmbeddedPicture();
        retriever.release();
        return art;
    }
}
