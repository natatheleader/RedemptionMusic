package com.redemption.music.Fragments;

import static com.redemption.music.MainActivity.songData;

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

    RecyclerView recyclerView;
    static SongAdapter songAdapter;
//    View view;

    public SongsFragment() {

    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_songs, container, false);

        //Get tracks using FileManager
//        FileManager fileManager = new FileManager(getContext());
        // data to populate the RecyclerView with
//        ArrayList<SongData> tracks = (ArrayList<SongData>) fileManager.getTracks();

        recyclerView = (RecyclerView) view.findViewById(R.id.songRecycler);
        recyclerView.setHasFixedSize(true);

        if (!(songData.size() < 1)) {
            songAdapter = new SongAdapter(getContext(), songData);
            recyclerView.setAdapter(songAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        } else {
            Toast.makeText(getContext(), "no song", Toast.LENGTH_SHORT).show();
//            add some layout that say's no music here
        }

        return view;
    }
}