package com.redemption.music.Fragments;

import static com.redemption.music.MainActivity.songData;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.redemption.music.Adapters.AlbumAdapter;
import com.redemption.music.Adapters.ArtistAdapter;
import com.redemption.music.Adapters.SongAdapter;
import com.redemption.music.Helpers.FileManager;
import com.redemption.music.Models.AlbumData;
import com.redemption.music.Models.ArtistData;
import com.redemption.music.R;

import java.util.ArrayList;

public class AlbumsFragment extends Fragment {

    RecyclerView recyclerView;
    SongAdapter albumAdapter;
//    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_albums, container, false);

//        AlbumData[] albumData = new AlbumData[] {
//                new AlbumData("Email", android.R.drawable.ic_dialog_email),
//                new AlbumData("Info", android.R.drawable.ic_dialog_info),
//                new AlbumData("Delete", android.R.drawable.ic_delete),
//                new AlbumData("Dialer", android.R.drawable.ic_dialog_dialer),
//                new AlbumData("Alert", android.R.drawable.ic_dialog_alert),
//                new AlbumData("Map", android.R.drawable.ic_dialog_map),
//                new AlbumData("Email", android.R.drawable.ic_dialog_email),
//                new AlbumData("Info", android.R.drawable.ic_dialog_info),
//                new AlbumData("Delete", android.R.drawable.ic_delete),
//                new AlbumData("Dialer", android.R.drawable.ic_dialog_dialer),
//                new AlbumData("Alert", android.R.drawable.ic_dialog_alert),
//                new AlbumData("Map", android.R.drawable.ic_dialog_map),
//        };

        //Get tracks using FileManager
//        FileManager fileManager = new FileManager(getContext());
        // data to populate the RecyclerView with
//        ArrayList<AlbumData> albums = (ArrayList<AlbumData>) fileManager.getAlbum();

        recyclerView = (RecyclerView) view.findViewById(R.id.songRecycler);
        recyclerView.setHasFixedSize(true);

        if (!(songData.size() < 1)) {
            albumAdapter = new SongAdapter(getContext(), songData);
            recyclerView.setAdapter(albumAdapter);
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        } else {
            Toast.makeText(getContext(), "no song", Toast.LENGTH_SHORT).show();
//            add some layout that say's no music here
        }

        return view;
    }
}