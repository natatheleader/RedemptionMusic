package com.redemption.music.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.redemption.music.Models.PlaylistData;
import com.redemption.music.PlayerActivity;
import com.redemption.music.R;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.ViewHolder> {

    private PlaylistData[] playlistData;

    // RecyclerView recyclerView;
    public PlaylistAdapter(PlaylistData[] playlistData) {
        this.playlistData = playlistData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.playlist_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final PlaylistData newPlaylistData = playlistData[position];
        holder.title.setText(playlistData[position].getTitle());
        holder.cover.setImageResource(playlistData[position].getImgId());
        holder.play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(holder.play.getContext(), PlayerActivity.class);
                holder.play.getContext().startActivity(myIntent);
            }
        });
        holder.menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"menu item: " + newPlaylistData.getTitle(), Toast.LENGTH_LONG).show();
            }
        });
        holder.cover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"image item: " + newPlaylistData.getTitle(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return playlistData.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView cover, play, menu;
        public TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.cover = (ImageView) itemView.findViewById(R.id.playlistImage);
            this.play = (ImageView) itemView.findViewById(R.id.playlistPlay);
            this.menu = (ImageView) itemView.findViewById(R.id.playlistMenu);
            this.title = (TextView) itemView.findViewById(R.id.playlistTitle);
        }
    }
}
