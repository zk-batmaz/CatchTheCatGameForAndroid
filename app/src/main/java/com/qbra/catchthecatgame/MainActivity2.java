package com.qbra.catchthecatgame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity2 extends AppCompatActivity
{
    Handler handler;
    Runnable runnable;
    TextView scoreText;
    TextView timeText;
    int scoreNum = 0;
    SharedPreferences sharedPreferences;
    ImageView cat1;
    ImageView cat2;
    ImageView cat3;
    ImageView cat4;
    ImageView cat5;
    ImageView cat6;
    ImageView cat7;
    ImageView cat8;
    ImageView cat9;
    ImageView[] imageArray;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        scoreText = findViewById(R.id.scoreText);
        timeText = findViewById(R.id.timeText);

        cat1 = findViewById(R.id.imageView1);
        cat2 = findViewById(R.id.imageView2);
        cat3 = findViewById(R.id.imageView3);
        cat4 = findViewById(R.id.imageView4);
        cat5 = findViewById(R.id.imageView5);
        cat6 = findViewById(R.id.imageView6);
        cat7 = findViewById(R.id.imageView7);
        cat8 = findViewById(R.id.imageView8);
        cat9 = findViewById(R.id.imageView9);

        imageArray = new ImageView[] {cat1, cat2, cat3, cat4, cat5, cat6, cat7, cat8, cat9};

        hideCats();

        sharedPreferences = this.getSharedPreferences("com.qbra.catchthecatgame", Context.MODE_PRIVATE);
        int lastScore = sharedPreferences.getInt("storedScore", 0);

        new CountDownTimer(30000,1000)
        {

            @Override
            public void onTick(long l)
            {
                timeText.setText("Time: " + (l/1000));
            }

            @Override
            public void onFinish()
            {
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                sharedPreferences.edit().putInt("storedScore", scoreNum).apply();
                intent.putExtra("userScore", scoreNum);
                startActivity(intent);
            }
        }.start();
    }

    public void catClick(View view)
    {
        scoreNum++;
        scoreText.setText("Score: " + scoreNum);
    }

    public void hideCats()
    {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                for (ImageView img : imageArray)
                {
                    img.setVisibility(View.INVISIBLE);
                }
                Random random = new Random();
                int i = random.nextInt(9);
                imageArray[i].setVisibility(View.VISIBLE);
                handler.postDelayed(this,500 );
            }
        };
        handler.post(runnable);
    }

}