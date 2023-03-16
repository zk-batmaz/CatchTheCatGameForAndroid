package com.qbra.catchthecatgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    TextView scoreText;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        int lastScore = intent.getIntExtra("userScore",0);

        scoreText = findViewById(R.id.lastScoreText);
        scoreText.setText("Score: " + lastScore);
    }

    public void play(View view)
    {
        Intent intent = new Intent(MainActivity.this,MainActivity2.class);
        startActivity(intent);
    }



}