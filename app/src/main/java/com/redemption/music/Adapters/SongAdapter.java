package com.redemption.music.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.redemption.music.Models.PlaylistData;
import com.redemption.music.Models.SongData;
import com.redemption.music.R;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder> {

    private SongData[] songData;

    public SongAdapter(SongData[] songData) {
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
        final SongData newSongData = songData[position];
        holder.title.setText(songData[position].getTitle());
        holder.name.setText(songData[position].getName());
        holder.cover.setImageResource(songData[position].getImgId());
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
        return songData.length;
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
}
