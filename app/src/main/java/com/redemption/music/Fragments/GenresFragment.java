package com.redemption.music.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.redemption.music.Adapters.AlbumAdapter;
import com.redemption.music.Adapters.ArtistAdapter;
import com.redemption.music.Adapters.GenreAdapter;
import com.redemption.music.Helpers.FileManager;
import com.redemption.music.Models.AlbumData;
import com.redemption.music.Models.ArtistData;
import com.redemption.music.Models.GenresData;
import com.redemption.music.R;

import java.util.ArrayList;

public class GenresFragment extends Fragment {

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_genres, container, false);

//        GenresData[] genreData = new GenresData[] {
//            new GenresData("Email", android.R.drawable.ic_dialog_email),
//            new GenresData("Info", android.R.drawable.ic_dialog_info),
//            new GenresData("Delete", android.R.drawable.ic_delete),
//            new GenresData("Dialer", android.R.drawable.ic_dialog_dialer),
//            new GenresData("Alert", android.R.drawable.ic_dialog_alert),
//            new GenresData("Map", android.R.drawable.ic_dialog_map),
//            new GenresData("Email", android.R.drawable.ic_dialog_email),
//            new GenresData("Info", android.R.drawable.ic_dialog_info),
//            new GenresData("Delete", android.R.drawable.ic_delete),
//            new GenresData("Dialer", android.R.drawable.ic_dialog_dialer),
//            new GenresData("Alert", android.R.drawable.ic_dialog_alert),
//            new GenresData("Map", android.R.drawable.ic_dialog_map),
//        };

        //Get tracks using FileManager
        FileManager fileManager = new FileManager(getContext());
        // data to populate the RecyclerView with
        ArrayList<GenresData> genres = (ArrayList<GenresData>) fileManager.getGenre();

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.genresRecycler);
        recyclerView.setHasFixedSize(true);

        if (!(genres.size() < 1)) {
            GenreAdapter adapter = new GenreAdapter(genres);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        } else {
//            add some layout that say's no music here
        }

        return view;
    }
}