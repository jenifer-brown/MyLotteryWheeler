package com.example.mylotterywheeler;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends AppCompatActivity {

    /**
     * LOG_TAG is the name of the current class
     */
    private static final String LOG_TAG = FullscreenActivity.class.getSimpleName();
    ImageView gif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        gif = (ImageView) findViewById(R.id.imageView);
        Glide.with(this)
                .asGif()
                .load(R.drawable.spinning_wheel)
                .into(gif);


    }


    public void launchChooseNumDigits(View view) {
        Log.d(LOG_TAG, "Button Clicked!");
        Intent intent = new Intent(this, ChooseNumDigits.class);
        startActivity(intent);
    }
}