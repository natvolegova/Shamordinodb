package com.example.shamordinodb.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.core.text.HtmlCompat;
import androidx.viewpager.widget.ViewPager;

import com.example.shamordinodb.Adapter.ImageSliderAdapter;
import com.example.shamordinodb.App;
import com.example.shamordinodb.Data.DatabaseHelper;
import com.example.shamordinodb.Objects.ObjectPut;
import com.example.shamordinodb.R;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import me.relex.circleindicator.CircleIndicator;

public class ObjectPutItemActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ImageView mainBackground;
    private TextView title, text_detail;
    private DatabaseHelper databaseHelper;
    private ImageButton buttonStart, buttonPause;
    private MediaPlayer mediaPlayer;
    private SeekBar seekBar;
    private Handler threadHandler = new Handler();
    private String audioFile = "";
    private View bg_detail;

    private ViewPager viewPager;
    private ImageSliderAdapter imageAdapter;
    private CircleIndicator circleIndicator;
    private ObjectPut item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseHelper = App.getInstance().getDatabaseInstance();
        setContentView(R.layout.activity_object_put_item);
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        item = databaseHelper.ObjectPutDao().getById(id);

        initView();

        initElement();

        String fileAudio = item.getFile_audio();
        if (fileAudio != null) {
            int resID = getRawResIdByName(fileAudio);
            mediaPlayer = MediaPlayer.create(this, resID);
            initAudio();
        }

        String imgGallery = item.getImg_gallery();
        if(imgGallery!=null){
            initImageSlider(imgGallery);

        }
    }

    private void initElement() {
        String img = "";
        if (item.getImg_detail() != null) {
            img = item.getImg_detail();
        } else if (item.getImg_preview() != null) {
            img = item.getImg_preview();
        }
        if (!img.equals("")) {
            int resID = getRawResIdByName(img);
            mainBackground.setImageResource(resID);
        }

        title.setText(item.getName());

        String htmlText = item.getText_detail();
        if(htmlText!=null){
            text_detail.setText(HtmlCompat.fromHtml(htmlText, HtmlCompat.FROM_HTML_MODE_COMPACT));
        }
    }

    private void initImageSlider(String img_gallery) {
        String[] imageArray=img_gallery.split(";");
        imageAdapter = new ImageSliderAdapter(this, imageArray);
        viewPager.setAdapter(imageAdapter);
        viewPager.setVisibility(View.VISIBLE);
        bg_detail.setVisibility(View.VISIBLE);
        circleIndicator.setViewPager(viewPager);
        circleIndicator.setVisibility(View.VISIBLE);
    }


    private void initAudio() {
        buttonStart = findViewById(R.id.btn_start);
        buttonPause = findViewById(R.id.btn_pause);
        seekBar = findViewById(R.id.seekBar);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playAudio();
            }
        });
        buttonPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doPause();
            }
        });
        buttonStart.setVisibility(View.VISIBLE);
        seekBar.setVisibility(View.VISIBLE);
    }

    private void initView() {
        mainBackground = findViewById(R.id.mainBackground);
        title = findViewById(R.id.title);
        text_detail = findViewById(R.id.text_detail);
        viewPager = findViewById(R.id.viewPager);
        circleIndicator = findViewById(R.id.circle);

        bg_detail = findViewById(R.id.bg_detail);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ObjectPutItemActivity.this, ObjectPutListActivity.class);
                startActivity(intent);
            }
        });
    }

    private void playAudio() {
        int duration = mediaPlayer.getDuration();
        int currentPosition = this.mediaPlayer.getCurrentPosition();
        if (currentPosition == 0) {
            seekBar.setMax(duration);
        } else if (currentPosition == duration) {
            this.mediaPlayer.reset();
        }
        mediaPlayer.start();
        UpdateSeekBarThread updateSeekBarThread = new UpdateSeekBarThread();
        threadHandler.postDelayed(updateSeekBarThread, 50);
        buttonPause.setVisibility(View.VISIBLE);
        buttonStart.setVisibility(View.GONE);
    }

    class UpdateSeekBarThread implements Runnable {
        public void run() {
            int currentPosition = mediaPlayer.getCurrentPosition();
            String currentPositionStr = millisecondsToString(currentPosition);
            seekBar.setProgress(currentPosition);
            threadHandler.postDelayed(this, 50);
        }
    }

    private void doPause() {
        mediaPlayer.pause();
        buttonPause.setVisibility(View.GONE);
        buttonStart.setVisibility(View.VISIBLE);
    }

    private String millisecondsToString(int milliseconds) {
        long minutes = TimeUnit.MILLISECONDS.toMinutes((long) milliseconds);
        long seconds = TimeUnit.MILLISECONDS.toSeconds((long) milliseconds);
        return minutes + ":" + seconds;
    }

    public int getRawResIdByName(String resName) {
        String pkgName = this.getPackageName();
        int resID = this.getResources().getIdentifier(resName, "raw", pkgName);
        return resID;
    }
}
