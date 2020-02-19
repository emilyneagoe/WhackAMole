package com.example.whackamole;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private GridLayout grid;
    private Drawable moleImage;
    private ImageView[] imageViews;
    private int moleLocation;
    private Random rand;
    public Handler handler;
    public int count;
    public UpdateMole update;
    public boolean on;
    public int score;
    private TextView scoreDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scoreDisplay = findViewById(R.id.scoreDisplay);
        grid = findViewById(R.id.gridLayout);
        moleImage = getDrawable(R.drawable.mole);
        imageViews = new ImageView[16];
        rand = new Random();
        handler = new Handler();
        count = 0;
        score = 0;
        update = new UpdateMole();
        moleLocation = rand.nextInt(16);
        for(int i=0; i<16; i++) {
            imageViews[i] = (ImageView) getLayoutInflater().inflate(R.layout.mole_view, null);
            imageViews[i].setMinimumWidth(275);
            imageViews[i].setMinimumHeight(275);
            if (i == moleLocation) imageViews[i].setImageDrawable(moleImage);
            grid.addView(imageViews[i]);
        }
    }


    public void startPressed(View v) {
        if (on)  {
            on = false;
            handler.removeCallbacks(update);
        }  else {
            on = true;
            handler.postDelayed(update, 800);
        }
    }

    private class UpdateMole implements Runnable {

        public void run() {
            imageViews[moleLocation].setImageDrawable(null);
            moleLocation = rand.nextInt(16);
            imageViews[moleLocation].setImageDrawable(moleImage);
            handler.postDelayed(update,800);
        }
    }

    public void moleClicked(View v) {
        if (v == imageViews[moleLocation]) {
            if (on == true) {
                imageViews[moleLocation].setImageDrawable(null);
                score++;
                scoreDisplay.setText(score + "");
            }
        }
    }

}


