package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void StartMainActivity(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void StartBotActivity(View v) {
        Intent intent = new Intent(this, BotActivity.class);
        startActivity(intent);
    }

    public void StartExActivity(View v) {
        Intent intent = new Intent(this, ExActivity.class);
        startActivity(intent);
    }

}