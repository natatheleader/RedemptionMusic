package com.redemption.music;

import static com.redemption.music.Adapters.AlbumDetailsAdapter.albumData;
import static com.redemption.music.Adapters.SongAdapter.mSongData;
import static com.redemption.music.Helpers.ApplicationClass.ACTION_NEXT;
import static com.redemption.music.Helpers.ApplicationClass.ACTION_PLAY;
import static com.redemption.music.Helpers.ApplicationClass.ACTION_PREVIOUS;
import static com.redemption.music.Helpers.ApplicationClass.CHANNEL_ID_2;
import static com.redemption.music.MainActivity.repeatBoolean;
import static com.redemption.music.MainActivity.shuffleBoolean;
import static com.redemption.music.MainActivity.songData;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.palette.graphics.Palette;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.media.session.MediaSessionCompat;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.redemption.music.Helpers.MusicService;
import com.redemption.music.Helpers.NotificationReciever;
import com.redemption.music.Interface.ActionPlaying;
import com.redemption.music.Models.SongData;

import java.util.ArrayList;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

public class PlayerActivity extends AppCompatActivity implements ActionPlaying, ServiceConnection {

    TextView song_name, artist_name, duration_played, duration_total;
    ImageView top_album_art, add_to_playlist, menu, bg, repeat, prev, next, shuffle;
    CircleImageView center;
    SeekBar seek;
    FloatingActionButton play;

    int position = -1;

    public static ArrayList<SongData> listOfSongs = new ArrayList<>();

    static Uri uri;
//    static MediaPlayer mediaPlayer;
    private Handler handler = new Handler();
    private Thread playThread, prevThread, nextThread;
    
    MusicService musicService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        initViews();

        getIntentMethod();

        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (musicService != null && b) {
                    musicService.seekTo(i * 1000);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        PlayerActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (musicService != null) {
                    int mCurrentPosition = musicService.getCurrentPosition() / 1000;
                    seek.setProgress(mCurrentPosition);
                    duration_played.setText(formattedTime(mCurrentPosition));
                }
                handler.postDelayed(this, 1000);
            }
        });

        shuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (shuffleBoolean) {
                    shuffleBoolean = false;
                    shuffle.setColorFilter(getApplicationContext().getResources().getColor(R.color.cadet_blue));
                } else {
                    shuffleBoolean = true;
                    shuffle.setColorFilter(getApplicationContext().getResources().getColor(R.color.purple_navy));
                }
            }
        });

        repeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (repeatBoolean) {
                    repeatBoolean = false;
                    repeat.setColorFilter(getApplicationContext().getResources().getColor(R.color.cadet_blue));
                } else {
                    repeatBoolean = true;
                    repeat.setColorFilter(getApplicationContext().getResources().getColor(R.color.purple_navy));
                }
            }
        });

        if (repeatBoolean) {
            repeat.setColorFilter(getApplicationContext().getResources().getColor(R.color.purple_navy));
        } else {
            repeat.setColorFilter(getApplicationContext().getResources().getColor(R.color.cadet_blue));
        }

        if (shuffleBoolean) {
            shuffle.setColorFilter(getApplicationContext().getResources().getColor(R.color.purple_navy));
        } else {
            shuffle.setColorFilter(getApplicationContext().getResources().getColor(R.color.cadet_blue));
        }
    }

    @Override
    protected void onResume() {
        Intent intent = new Intent(this, MusicService.class);
        bindService(intent, this, BIND_AUTO_CREATE);
        playThreadBtn();
        previousThreadBtn();
        nextThreadBtn();
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unbindService(this);
    }

    private void nextThreadBtn() {
        nextThread = new Thread() {
            @Override
            public void run() {
                super.run();
                next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        nextBtnClicked();
                    }
                });
            }
        };
        nextThread.start();
    }

    public void nextBtnClicked() {
        if (musicService.isPlaying()) {
            musicService.stop();
            musicService.release();
            if (shuffleBoolean && !repeatBoolean) {
                position = getRandom(listOfSongs.size() - 1);
            } else if (!shuffleBoolean && !repeatBoolean) {
                position = ((position + 1) > listOfSongs.size() ? (1) : (position + 1));
            } else {
                position = position;
            }
//            position = ((position + 1) % listOfSongs.size());
            uri = Uri.parse(listOfSongs.get(position).getPath());
            musicService.createMediaPlayer(position);
            metaData(uri);
            song_name.setText(listOfSongs.get(position).getTitle());
            artist_name.setText(listOfSongs.get(position).getArtist());
            seek.setMax(musicService.getDuration() / 1000);

            PlayerActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (musicService != null) {
                        int mCurrentPosition = musicService.getCurrentPosition() / 1000;
                        seek.setProgress(mCurrentPosition);
                    }
                    handler.postDelayed(this, 1000);
                }
            });
            musicService.OnCompleted();
            musicService.showNotification(R.drawable.pause);
            play.setImageResource(R.drawable.pause);
            musicService.start();
        } else {
            musicService.stop();
            musicService.release();
            if (shuffleBoolean && !repeatBoolean) {
                position = getRandom(listOfSongs.size() - 1);
            } else if (!shuffleBoolean && !repeatBoolean) {
                position = ((position + 1) > listOfSongs.size() ? (1) : (position + 1));
            } else {
                position = position;
            }
//            position = ((position + 1) % listOfSongs.size());
            uri = Uri.parse(listOfSongs.get(position).getPath());
            musicService.createMediaPlayer(position);
            metaData(uri);
            song_name.setText(listOfSongs.get(position).getTitle());
            artist_name.setText(listOfSongs.get(position).getArtist());
            seek.setMax(musicService.getDuration() / 1000);

            PlayerActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (musicService != null) {
                        int mCurrentPosition = musicService.getCurrentPosition() / 1000;
                        seek.setProgress(mCurrentPosition);
                    }
                    handler.postDelayed(this, 1000);
                }
            });
            musicService.OnCompleted();
            musicService.showNotification(R.drawable.pause);
            play.setImageResource(R.drawable.pause);
            musicService.start();
        }
    }

    private int getRandom(int i) {
        Random random = new Random();
        return random.nextInt(i + 1);
    }

    private void previousThreadBtn() {
        prevThread = new Thread() {
            @Override
            public void run() {
                super.run();
                prev.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        prevBtnClicked();
                    }
                });
            }
        };
        prevThread.start();
    }

    public void prevBtnClicked() {
        if (musicService.isPlaying()) {
            musicService.stop();
            musicService.release();
            if (shuffleBoolean && !repeatBoolean) {
                position = getRandom(listOfSongs.size() - 1);
            } else if (!shuffleBoolean && !repeatBoolean) {
                position = ((position - 1) < 0 ? (listOfSongs.size() - 1) : (position - 1));
            } else {
                position = position;
            }
            uri = Uri.parse(listOfSongs.get(position).getPath());
            musicService.createMediaPlayer(position);
            metaData(uri);
            song_name.setText(listOfSongs.get(position).getTitle());
            artist_name.setText(listOfSongs.get(position).getArtist());
            seek.setMax(musicService.getDuration() / 1000);

            PlayerActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (musicService != null) {
                        int mCurrentPosition = musicService.getCurrentPosition() / 1000;
                        seek.setProgress(mCurrentPosition);
                    }
                    handler.postDelayed(this, 1000);
                }
            });
            musicService.OnCompleted();
            musicService.showNotification(R.drawable.pause);
            play.setImageResource(R.drawable.pause);
            musicService.start();
        } else {
            musicService.stop();
            musicService.release();
            if (shuffleBoolean && !repeatBoolean) {
                position = getRandom(listOfSongs.size() - 1);
            } else if (!shuffleBoolean && !repeatBoolean) {
                position = ((position - 1) < 0 ? (listOfSongs.size() - 1) : (position - 1));
            } else {
                position = position;
            }
            uri = Uri.parse(listOfSongs.get(position).getPath());
            musicService.createMediaPlayer(position);
            metaData(uri);
            song_name.setText(listOfSongs.get(position).getTitle());
            artist_name.setText(listOfSongs.get(position).getArtist());
            seek.setMax(musicService.getDuration() / 1000);

            PlayerActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (musicService != null) {
                        int mCurrentPosition = musicService.getCurrentPosition() / 1000;
                        seek.setProgress(mCurrentPosition);
                    }
                    handler.postDelayed(this, 1000);
                }
            });
            musicService.OnCompleted();
            musicService.showNotification(R.drawable.pause);
            play.setImageResource(R.drawable.pause);
            musicService.start();
        }
    }

    private void playThreadBtn() {
        playThread = new Thread() {
            @Override
            public void run() {
                super.run();
                play.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        playBtnClicked();
                    }
                });
            }
        };
        playThread.start();
    }

    public void playBtnClicked() {
        if (musicService.isPlaying()) {
            play.setImageResource(R.drawable.play);
            musicService.showNotification(R.drawable.play);
            musicService.pause();
            seek.setMax(musicService.getDuration() / 1000);

            PlayerActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (musicService != null) {
                        int mCurrentPosition = musicService.getCurrentPosition() / 1000;
                        seek.setProgress(mCurrentPosition);
                    }
                    handler.postDelayed(this, 1000);
                }
            });
        } else {
            play.setImageResource(R.drawable.pause);
            musicService.showNotification(R.drawable.pause);
            musicService.start();
            seek.setMax(musicService.getDuration() / 1000);

            PlayerActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (musicService != null) {
                        int mCurrentPosition = musicService.getCurrentPosition() / 1000;
                        seek.setProgress(mCurrentPosition);
                    }
                    handler.postDelayed(this, 1000);
                }
            });
        }
    }

    @NonNull
    private String formattedTime(int mCurrentPosition) {
        String totalOut, totalNew = "";
        String seconds = String.valueOf(mCurrentPosition % 60);
        String minutes = String.valueOf(mCurrentPosition / 60);
        totalOut = minutes + ":" + seconds;
        totalNew = minutes + ":" + "0" + seconds;
        if (seconds.length() == 1) {
            return totalNew;
        } else {
            return totalOut;
        }
    }

    private void getIntentMethod() {
        position = getIntent().getIntExtra("position", -1);
        String sender = getIntent().getStringExtra("sender");
        if(sender != null && sender.equals("albumDetails")) {
            listOfSongs = albumData;
        } else {
            listOfSongs = mSongData;
        }
        if (listOfSongs != null) {
            play.setImageResource(R.drawable.pause);
            uri = Uri.parse(listOfSongs.get(position).getPath());
        }


        Intent intent = new Intent(this, MusicService.class);
        intent.putExtra("servicePosition", position);
        startService(intent);
    }

    private void initViews() {
        song_name = findViewById(R.id.player_song_name);
        artist_name = findViewById(R.id.player_artist_name);
        duration_played = findViewById(R.id.player_current_min);
        duration_total = findViewById(R.id.player_remaining_min);
        top_album_art = findViewById(R.id.player_album_art_thumb);
        add_to_playlist = findViewById(R.id.player_add_to_playlist);
        menu = findViewById(R.id.player_menu);
        bg = findViewById(R.id.player_bg);
        center = findViewById(R.id.player_album_art_center);
        repeat = findViewById(R.id.player_repeat);
        prev = findViewById(R.id.player_prev);
        next = findViewById(R.id.player_next);
        shuffle = findViewById(R.id.player_shuffle);
        seek = findViewById(R.id.player_seek);
        play = findViewById(R.id.player_play);
    }

    private void metaData(Uri uri) {
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        retriever.setDataSource(uri.toString());
        int durationTotal = Integer.parseInt(listOfSongs.get(position).getDuration()) / 1000;
        duration_total.setText(formattedTime(durationTotal));

        byte[] art = retriever.getEmbeddedPicture();

        Bitmap bitmap = null;
        if (art != null) {
//            Glide.with(this)
//                    .asBitmap()
//                    .load(art)
//                    .into(top_album_art);

            ImageAnimation(getApplicationContext(), bg, art);
            ImageAnimation(getApplicationContext(), top_album_art, art);
            ImageAnimation(getApplicationContext(), center, art);

//            Glide.with(this)
//                    .asBitmap()
//                    .load(art)
//                    .into(bg);

//            Glide.with(this)
//                    .asBitmap()
//                    .load(art)
//                    .into(center);

            bitmap = BitmapFactory.decodeByteArray(art, 0, art.length);

            Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                @Override
                public void onGenerated(@Nullable Palette palette) {
                    Palette.Swatch swatch = palette.getDominantSwatch();
                    if (swatch != null) {
                        center.setBorderColor(swatch.getRgb());
                    } else {
                        center.setBorderColor(getResources().getColor(R.color.purple_navy));
                    }
                }
            });
        } else {
            Glide.with(this)
                    .asBitmap()
                    .load(R.drawable.playlist)
                    .into(top_album_art);

            Glide.with(this)
                    .asBitmap()
                    .load(R.drawable.playlist)
                    .into(bg);

            Glide.with(this)
                    .asBitmap()
                    .load(R.drawable.playlist)
                    .into(center);
        }
    }

    public void ImageAnimation(Context context, ImageView imageView, byte[] bitmap) {
        Animation animOut = AnimationUtils.loadAnimation(context, android.R.anim.fade_out);
        Animation animIn = AnimationUtils.loadAnimation(context, android.R.anim.fade_in);
        animOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Glide.with(context)
                        .asBitmap()
                        .load(bitmap)
                        .into(imageView);
                animIn.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                imageView.startAnimation(animIn);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        imageView.startAnimation(animOut);
    }

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        MusicService.MyBinder myBinder = (MusicService.MyBinder) iBinder;
        musicService = myBinder.getService();
        musicService.setCallBack(this);
        Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show();

        seek.setMax(musicService.getDuration() / 1000);

        metaData(uri);

        song_name.setText(listOfSongs.get(position).getTitle());
        artist_name.setText(listOfSongs.get(position).getArtist());

        musicService.OnCompleted();

        musicService.showNotification(R.drawable.pause);
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {
        musicService = null;
    }
}