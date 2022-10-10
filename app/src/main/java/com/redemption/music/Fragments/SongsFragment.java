package com.redemption.music.Fragments;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.redemption.music.Adapters.SongAdapter;
import com.redemption.music.Helpers.FileManager;
import com.redemption.music.Models.SongData;
import com.redemption.music.R;

import java.util.ArrayList;

public class SongsFragment extends Fragment {

    View view;

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_songs, container, false);

        //Get tracks using FileManager
        FileManager fileManager = new FileManager(getContext());
        // data to populate the RecyclerView with
        ArrayList<SongData> tracks = (ArrayList<SongData>) fileManager.getTracks();

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.songRecycler);
        recyclerView.setHasFixedSize(true);

        if (!(tracks.size() < 1)) {
            SongAdapter adapter = new SongAdapter(tracks);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        } else {
//            add some layout that sayes no music here
        }

        return view;
    }
}