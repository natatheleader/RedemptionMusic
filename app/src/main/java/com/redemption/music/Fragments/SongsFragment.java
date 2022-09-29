package com.redemption.music.Fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.redemption.music.Adapters.PlaylistAdapter;
import com.redemption.music.Adapters.SongAdapter;
import com.redemption.music.Helpers.FileManager;
import com.redemption.music.Models.PlaylistData;
import com.redemption.music.Models.SongData;
import com.redemption.music.R;

import java.util.ArrayList;

public class SongsFragment extends Fragment {

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_songs, container, false);

        isStoragePermissionGranted();   //Ask for permission runtime

//        SongData[] songData = new SongData[] {
//            new SongData("Email", "Test", android.R.drawable.ic_dialog_email),
//            new SongData("Info", "Test", android.R.drawable.ic_dialog_info),
//            new SongData("Delete", "Test", android.R.drawable.ic_delete),
//            new SongData("Dialer", "Test", android.R.drawable.ic_dialog_dialer),
//            new SongData("Alert", "Test", android.R.drawable.ic_dialog_alert),
//            new SongData("Map", "Test", android.R.drawable.ic_dialog_map),
//            new SongData("Email", "Test", android.R.drawable.ic_dialog_email),
//            new SongData("Info", "Test", android.R.drawable.ic_dialog_info),
//            new SongData("Delete", "Test", android.R.drawable.ic_delete),
//            new SongData("Dialer", "Test", android.R.drawable.ic_dialog_dialer),
//            new SongData("Alert", "Test", android.R.drawable.ic_dialog_alert),
//            new SongData("Map", "Test", android.R.drawable.ic_dialog_map),
//        };

        //Get tracks using FileManager
        FileManager fileManager = new FileManager(getContext());
        // data to populate the RecyclerView with
        ArrayList<SongData> tracks = (ArrayList<SongData>) fileManager.getTracks();

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.songRecycler);
        SongAdapter adapter = new SongAdapter(tracks);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        return view;
    }

    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("PERMINIT","Permission is granted");
                return true;
            } else {

                Log.v("PERMINIT","Permission is revoked");
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else {
            Log.v("PERMINIT","Permission is granted");
            return true;
        }
    }
}