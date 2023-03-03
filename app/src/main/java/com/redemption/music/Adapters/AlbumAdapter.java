package com.redemption.music.Adapters;

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
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.redemption.music.AlbumDetailActivity;
import com.redemption.music.Models.AlbumData;
import com.redemption.music.Models.ArtistData;
import com.redemption.music.Models.SongData;
import com.redemption.music.R;

import java.util.ArrayList;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.MyViewHolder> {

    private ArrayList<SongData> albumData;
    private Context mContext;

    // RecyclerView recyclerView;
    public AlbumAdapter(Context mContext, ArrayList<SongData> albumData) {
        this.mContext = mContext;
        this.albumData = albumData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.album_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(albumData.get(holder.getAdapterPosition()).getAlbum());
        byte[] image = getAlbumArt(albumData.get(holder.getAdapterPosition()).getPath());
        if (image != null) {
            Glide.with(holder.cover.getContext()).asBitmap()
                    .load(image)
                    .into(holder.cover);
        } else {
            Glide.with(holder.cover.getContext())
                    .load(R.drawable.playlist)
                    .into(holder.cover);
        }
//        holder.cover.setImageResource(albumData.get(position).getImgId());
        holder.play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"play item: " + albumData.get(holder.getAdapterPosition()).getAlbum(), Toast.LENGTH_LONG).show();
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, AlbumDetailActivity.class);
                intent.putExtra("albumName", albumData.get(holder.getAdapterPosition()).getAlbum());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return albumData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView cover, play;
        public TextView name;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            this.cover = (ImageView) itemView.findViewById(R.id.albumImage);
            this.play = (ImageView) itemView.findViewById(R.id.albumPlay);
            this.name = (TextView) itemView.findViewById(R.id.albumName);
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
