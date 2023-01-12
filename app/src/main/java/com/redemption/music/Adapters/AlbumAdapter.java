package com.redemption.music.Adapters;

import android.media.MediaMetadataRetriever;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.redemption.music.Models.AlbumData;
import com.redemption.music.Models.ArtistData;
import com.redemption.music.R;

import java.util.ArrayList;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {

    private ArrayList<AlbumData> albumData;

    // RecyclerView recyclerView;
    public AlbumAdapter(ArrayList<AlbumData> albumData) {
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
        final AlbumData newAlbumData = albumData.get(position);
        holder.name.setText(albumData.get(position).getName());
        if (newAlbumData.getPath() != null) {
            byte[] image = getAlbumArt(newAlbumData.getPath());
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
//        holder.cover.setImageResource(albumData.get(position).getImgId());
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
        return albumData.size();
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

    private byte[] getAlbumArt(String uri) {
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        retriever.setDataSource(uri);

        byte[] art = retriever.getEmbeddedPicture();
        retriever.release();
        return art;
    }
}
