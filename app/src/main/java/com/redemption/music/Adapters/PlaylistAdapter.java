package com.redemption.music.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.redemption.music.Models.PlaylistData;
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
        holder.textView.setText(playlistData[position].getTitle());
        holder.imageView.setImageResource(playlistData[position].getImgId());
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"click on item: " + newPlaylistData.getTitle(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return playlistData.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public TextView textView;
        public ConstraintLayout constraintLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.imageView = (ImageView) itemView.findViewById(R.id.playlistImage);
            this.textView = (TextView) itemView.findViewById(R.id.playlistTitle);
            this.constraintLayout = (ConstraintLayout) itemView.findViewById(R.id.playlistLayout);
        }
    }
}
