package com.example.administrator.news1028;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Guide extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        Logo.start(this);
        finish();
    }

}
