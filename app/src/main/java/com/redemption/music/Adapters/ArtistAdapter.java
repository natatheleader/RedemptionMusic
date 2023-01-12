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
import com.redemption.music.Models.ArtistData;
import com.redemption.music.Models.PlaylistData;
import com.redemption.music.R;

import java.util.ArrayList;

public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.ViewHolder> {

    private ArrayList<ArtistData> artistData;

    // RecyclerView recyclerView;
    public ArtistAdapter(ArrayList<ArtistData> artistData) {
        this.artistData = artistData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.artist_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ArtistData newArtistData = artistData.get(position);
        holder.name.setText(artistData.get(position).getName());
        if (newArtistData.getPath() != null) {
            byte[] image = getAlbumArt(newArtistData.getPath());
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
//        holder.cover.setImageResource(artistData.get(position).getImgId());
        holder.play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"play item: " + newArtistData.getName(), Toast.LENGTH_LONG).show();
            }
        });
        holder.cover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"image item: " + newArtistData.getName(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return artistData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView cover, play;
        public TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.cover = (ImageView) itemView.findViewById(R.id.artistImage);
            this.play = (ImageView) itemView.findViewById(R.id.artistPlay);
            this.name = (TextView) itemView.findViewById(R.id.artistName);
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
