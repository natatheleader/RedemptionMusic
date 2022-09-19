package com.redemption.music.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.redemption.music.Adapters.ArtistAdapter;
import com.redemption.music.Models.ArtistData;
import com.redemption.music.R;

public class ArtistsFragment extends Fragment {

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_artists, container, false);

        ArtistData[] artistData = new ArtistData[] {
            new ArtistData("Email", android.R.drawable.ic_dialog_email),
            new ArtistData("Info", android.R.drawable.ic_dialog_info),
            new ArtistData("Delete", android.R.drawable.ic_delete),
            new ArtistData("Dialer", android.R.drawable.ic_dialog_dialer),
            new ArtistData("Alert", android.R.drawable.ic_dialog_alert),
            new ArtistData("Map", android.R.drawable.ic_dialog_map),
            new ArtistData("Email", android.R.drawable.ic_dialog_email),
            new ArtistData("Info", android.R.drawable.ic_dialog_info),
            new ArtistData("Delete", android.R.drawable.ic_delete),
            new ArtistData("Dialer", android.R.drawable.ic_dialog_dialer),
            new ArtistData("Alert", android.R.drawable.ic_dialog_alert),
            new ArtistData("Map", android.R.drawable.ic_dialog_map),
        };

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.artistRecycler);
        ArtistAdapter adapter = new ArtistAdapter(artistData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(adapter);

        return view;
    }
}