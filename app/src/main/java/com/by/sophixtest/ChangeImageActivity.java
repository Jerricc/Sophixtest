package com.by.sophixtest;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ChangeImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_image);

        ImageView image = findViewById(R.id.image);
        image.setBackground(getResources().getDrawable(R.mipmap.ic_launcher));
//        image.setBackground(getResources().getDrawable(R.drawable.chat_pull_down_ic));
        TestAddClass.prinf();
    }

}