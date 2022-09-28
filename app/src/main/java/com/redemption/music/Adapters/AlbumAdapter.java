package com.redemption.music.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.redemption.music.Models.AlbumData;
import com.redemption.music.Models.ArtistData;
import com.redemption.music.R;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {

    private AlbumData[] albumData;

    // RecyclerView recyclerView;
    public AlbumAdapter(AlbumData[] albumData) {
        this.albumData = albumData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.album_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final AlbumData newAlbumData = albumData[position];
        holder.name.setText(albumData[position].getName());
        holder.cover.setImageResource(albumData[position].getImgId());
        holder.play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"play item: " + newAlbumData.getName(), Toast.LENGTH_LONG).show();
            }
        });
        holder.cover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"image item: " + newAlbumData.getName(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return albumData.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView cover, play;
        public TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.cover = (ImageView) itemView.findViewById(R.id.albumImage);
            this.play = (ImageView) itemView.findViewById(R.id.albumPlay);
            this.name = (TextView) itemView.findViewById(R.id.albumName);
        }
    }
}
