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
import com.redemption.music.Models.GenresData;
import com.redemption.music.R;

import java.util.ArrayList;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.ViewHolder> {

    private ArrayList<GenresData> genreData;

    // RecyclerView recyclerView;
    public GenreAdapter(ArrayList<GenresData> genreData) {
        this.genreData = genreData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.genres_item, parent, false);
        GenreAdapter.ViewHolder viewHolder = new GenreAdapter.ViewHolder(listItem);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final GenresData newGenreData = genreData.get(position);
        holder.name.setText(genreData.get(position).getName());
        if (newGenreData.getPath() != null) {
            byte[] image = getAlbumArt(newGenreData.getPath());
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
//        holder.cover.setImageResource(genreData[position].getImgId());
        holder.play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"play item: " + newGenreData.getName(), Toast.LENGTH_LONG).show();
            }
        });
        holder.cover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"image item: " + newGenreData.getName(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return genreData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView cover, play;
        public TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.cover = (ImageView) itemView.findViewById(R.id.genreImage);
            this.play = (ImageView) itemView.findViewById(R.id.genrePlay);
            this.name = (TextView) itemView.findViewById(R.id.genreName);
        }
    }

    private byte[] getAlbumArt(String uri) {
        try {
            MediaMetadataRetriever retriever = new MediaMetadataRetriever();
            retriever.setDataSource(uri);

            byte[] art = retriever.getEmbeddedPicture();
            retriever.release();
            return art;
        } catch (Exception ex) {
            return null;
        }
    }
}
