package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ExActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex);
    }

    public void StartLevel(View v) {
        Intent intent = new Intent(this, LevelActivity.class);
        intent.putExtra("level", ((Button) v).getText().toString());
        startActivity(intent);
    }
}