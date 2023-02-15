package com.redemption.music.Adapters;

import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.Model;
import com.google.android.material.snackbar.Snackbar;
import com.redemption.music.Models.SongData;
import com.redemption.music.PlayerActivity;
import com.redemption.music.R;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.MyViewHolder> {

    public static ArrayList<SongData> mSongData;
    private Context mContext;

    public SongAdapter(Context mContext, ArrayList<SongData> mSongData) {
        this.mContext = mContext;
        this.mSongData = mSongData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.song_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.title.setText(mSongData.get(holder.getAdapterPosition()).getTitle());
        holder.name.setText(mSongData.get(holder.getAdapterPosition()).getArtist());

        byte[] image = getAlbumArt(mSongData.get(holder.getAdapterPosition()).getPath());
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
                Toast.makeText(view.getContext(),"like item: " + mSongData.get(holder.getAdapterPosition()).getTitle(), Toast.LENGTH_LONG).show();
            }
        });

        holder.menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(mContext, view);
                popupMenu.getMenuInflater().inflate(R.menu.song_more_menu, popupMenu.getMenu());
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.song_menu_delete:
                                deleteFile(holder.getAdapterPosition(), view);
                                break;
                        }
                        return true;
                    }
                });
            }
        });

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, PlayerActivity.class);
                intent.putExtra("position", holder.getAdapterPosition());
                mContext.startActivity(intent);
            }
        });
    }

    private void deleteFile(int position, View view) {
        Uri contentUri = ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, Long.parseLong(mSongData.get(position).getId()));
        File file = new File(mSongData.get(position).getPath());
        boolean deleted = file.delete();
        if (deleted) {
            mContext.getContentResolver().delete(contentUri, null, null);
            mSongData.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, mSongData.size());
            Snackbar.make(view, "File deleted!", Snackbar.LENGTH_LONG)
//                .setActionTextColor(mContext.getResources().getColor(R.color.azureish_white))
                    .show();
        } else {
            Snackbar.make(view, "File can't be deleted!", Snackbar.LENGTH_LONG)
//                .setActionTextColor(mContext.getResources().getColor(R.color.azureish_white))
                    .show();
        }

    }

    @Override
    public int getItemCount() {
        return mSongData.size();
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

    public void updateList (ArrayList<SongData> songDataFiltered) {
        mSongData = new ArrayList<>();
        mSongData.addAll(songDataFiltered);
        notifyDataSetChanged();
    }
}
