package com.redemption.music;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.redemption.music.Adapters.TabAdapter;
import com.redemption.music.Helpers.FileManager;
import com.redemption.music.Models.SongData;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;

    public TabLayout tabLayout;
    public ViewPager viewPager;

    public SongData songData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        runTimePermission();

        // drawer layout instance to toggle the menu icon to open
        // drawer and back button to close drawer
        drawerLayout = findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        // to make the Navigation drawer icon always appear on the action bar
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        // tab layout
        tabLayout = (TabLayout)findViewById(R.id.tabLayout);
        viewPager = (ViewPager)findViewById(R.id.viewPager);

        tabLayout.addTab(tabLayout.newTab().setText("Playlists"));
        tabLayout.addTab(tabLayout.newTab().setText("Artists"));
        tabLayout.addTab(tabLayout.newTab().setText("Albums"));
        tabLayout.addTab(tabLayout.newTab().setText("Songs"));
        tabLayout.addTab(tabLayout.newTab().setText("Genres"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        tabLayout.setTabTextColors(getResources().getColor(R.color.cadet_blue), getResources().getColor(R.color.purple_navy));

        final TabAdapter adapter = new TabAdapter(this,getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    // override the onOptionsItemSelected()
    // function to implement
    // the item click listener callback
    // to open and close the navigation
    // drawer when the icon is clicked
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        MenuItem.OnActionExpandListener onActionExpandListener = new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem menuItem) {
                Toast.makeText(MainActivity.this, "Menu Expanded", Toast.LENGTH_LONG).show();
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem menuItem) {
                Toast.makeText(MainActivity.this, "Menu Collapsed", Toast.LENGTH_LONG).show();
                return true;
            }
        };
        menu.findItem(R.id.search).setOnActionExpandListener(onActionExpandListener);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setQueryHint("Search ...");
        return true;
    }
    
//    public void runTimePermission() {
//        Dexter.withContext(this).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE).withListener(new PermissionListener() {
//            @Override
//            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
//                //Get tracks using FileManager
//                FileManager fileManager = new FileManager(getApplicationContext());
//                // data to populate the RecyclerView with
//                ArrayList<SongData> tracks = (ArrayList<SongData>) fileManager.getTracks();
//
////                trackAdapter = new TrackAdapter(this, tracks);
////                tracksRV.setAdapter(trackAdapter);
//            }
//
//            @Override
//            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
//
//            }
//
//            @Override
//            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
//                permissionToken.continuePermissionRequest();
//            }
//        }).check();
//    }
}