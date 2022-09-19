package com.redemption.music.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.redemption.music.Adapters.PlaylistAdapter;
import com.redemption.music.Models.PlaylistData;
import com.redemption.music.R;

public class PlaylistFragment extends Fragment {

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_playlist, container, false);

        PlaylistData[] playlistData = new PlaylistData[] {
            new PlaylistData("Email", android.R.drawable.ic_dialog_email),
            new PlaylistData("Info", android.R.drawable.ic_dialog_info),
            new PlaylistData("Delete", android.R.drawable.ic_delete),
            new PlaylistData("Dialer", android.R.drawable.ic_dialog_dialer),
            new PlaylistData("Alert", android.R.drawable.ic_dialog_alert),
            new PlaylistData("Map", android.R.drawable.ic_dialog_map),
            new PlaylistData("Email", android.R.drawable.ic_dialog_email),
            new PlaylistData("Info", android.R.drawable.ic_dialog_info),
            new PlaylistData("Delete", android.R.drawable.ic_delete),
            new PlaylistData("Dialer", android.R.drawable.ic_dialog_dialer),
            new PlaylistData("Alert", android.R.drawable.ic_dialog_alert),
            new PlaylistData("Map", android.R.drawable.ic_dialog_map),
        };

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.playlistRecycler);
        PlaylistAdapter adapter = new PlaylistAdapter(playlistData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        return view;
    }
}