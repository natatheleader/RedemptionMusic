package com.redemption.music.Adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
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
import com.redemption.music.R;

import java.util.ArrayList;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder> {

    private ArrayList<SongData> songData;

    public SongAdapter(ArrayList<SongData> songData) {
        this.songData = songData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.song_item, parent, false);
        SongAdapter.ViewHolder viewHolder = new SongAdapter.ViewHolder(listItem);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final SongData newSongData = songData.get(position);
        holder.title.setText(songData.get(position).getTitle());
        holder.name.setText(songData.get(position).getName());
        if (songData.get(position).getPath() != null) {
            byte[] image = getAlbumArt(songData.get(position).getPath());
            if (image != null) {
                Glide.with(holder.cover.getContext()).asBitmap()
                        .load(image)
                        .into(holder.cover);
            } else {
                Glide.with(holder.cover.getContext())
                        .load(R.drawable.playlist)
                        .into(holder.cover);
            }
        } else  {
            Glide.with(holder.cover.getContext())
                    .load(R.drawable.playlist)
                    .into(holder.cover);
        }
//        Drawable img = Drawable.createFromPath(songData.get(position).getImgPath());
//        holder.cover.setImageDrawable(img);;
//        holder.cover.setImageResource(songData.get(position).getImgId());
//        Toast.makeText(holder.cover.getContext(), songData.get(position).getImgPath(), Toast.LENGTH_LONG).show();
        holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                holder.like.setColorFilter(ActivityCompat.getColor(view.getContext(), R.color.maximum_yellow_red));
                Toast.makeText(view.getContext(),"like item: " + newSongData.getTitle(), Toast.LENGTH_LONG).show();
            }
        });
        holder.menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"menu item: " + newSongData.getTitle(), Toast.LENGTH_LONG).show();
            }
        });
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"layout item: " + newSongData.getTitle(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return songData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView cover, like, menu;
        public TextView title, name;
        public ConstraintLayout layout;

        public ViewHolder(@NonNull View itemView) {
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
