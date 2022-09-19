package com.redemption.music.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.redemption.music.Models.ArtistData;
import com.redemption.music.Models.PlaylistData;
import com.redemption.music.R;

public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.ViewHolder> {

    private ArtistData[] artistData;

    // RecyclerView recyclerView;
    public ArtistAdapter(ArtistData[] artistData) {
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
        final ArtistData newArtistData = artistData[position];
        holder.name.setText(artistData[position].getTitle());
        holder.cover.setImageResource(artistData[position].getImgId());
        holder.play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"play item: " + newArtistData.getTitle(), Toast.LENGTH_LONG).show();
            }
        });
        holder.cover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"image item: " + newArtistData.getTitle(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return artistData.length;
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
}
