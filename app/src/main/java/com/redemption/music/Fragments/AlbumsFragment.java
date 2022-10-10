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
import com.redemption.music.Models.AlbumData;
import com.redemption.music.Models.ArtistData;
import com.redemption.music.R;

public class AlbumsFragment extends Fragment {

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_albums, container, false);

        AlbumData[] albumData = new AlbumData[] {
                new AlbumData("Email", android.R.drawable.ic_dialog_email),
                new AlbumData("Info", android.R.drawable.ic_dialog_info),
                new AlbumData("Delete", android.R.drawable.ic_delete),
                new AlbumData("Dialer", android.R.drawable.ic_dialog_dialer),
                new AlbumData("Alert", android.R.drawable.ic_dialog_alert),
                new AlbumData("Map", android.R.drawable.ic_dialog_map),
                new AlbumData("Email", android.R.drawable.ic_dialog_email),
                new AlbumData("Info", android.R.drawable.ic_dialog_info),
                new AlbumData("Delete", android.R.drawable.ic_delete),
                new AlbumData("Dialer", android.R.drawable.ic_dialog_dialer),
                new AlbumData("Alert", android.R.drawable.ic_dialog_alert),
                new AlbumData("Map", android.R.drawable.ic_dialog_map),
        };

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.albumRecycler);
        AlbumAdapter adapter = new AlbumAdapter(albumData);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(adapter);

        return view;
    }
}