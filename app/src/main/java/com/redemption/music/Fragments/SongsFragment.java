package com.redemption.music.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.redemption.music.Adapters.PlaylistAdapter;
import com.redemption.music.Adapters.SongAdapter;
import com.redemption.music.Models.PlaylistData;
import com.redemption.music.Models.SongData;
import com.redemption.music.R;

public class SongsFragment extends Fragment {

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_songs, container, false);

        SongData[] songData = new SongData[] {
            new SongData("Email", "Test", android.R.drawable.ic_dialog_email),
            new SongData("Info", "Test", android.R.drawable.ic_dialog_info),
            new SongData("Delete", "Test", android.R.drawable.ic_delete),
            new SongData("Dialer", "Test", android.R.drawable.ic_dialog_dialer),
            new SongData("Alert", "Test", android.R.drawable.ic_dialog_alert),
            new SongData("Map", "Test", android.R.drawable.ic_dialog_map),
            new SongData("Email", "Test", android.R.drawable.ic_dialog_email),
            new SongData("Info", "Test", android.R.drawable.ic_dialog_info),
            new SongData("Delete", "Test", android.R.drawable.ic_delete),
            new SongData("Dialer", "Test", android.R.drawable.ic_dialog_dialer),
            new SongData("Alert", "Test", android.R.drawable.ic_dialog_alert),
            new SongData("Map", "Test", android.R.drawable.ic_dialog_map),
        };

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.songRecycler);
        SongAdapter adapter = new SongAdapter(songData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        return view;
    }
}