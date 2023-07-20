package com.by.sophixtest;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ChangeAssetsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_assets);

        TextView tv = findViewById(R.id.tv);
        //从assert中获取有资源，获得app的assert，采用getAserts()， 通过给出在assert/下面的相对路径。

        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/Android-Italic-3.ttf");
//        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/Android-Scratch-4.ttf");
        tv.setTypeface(face);
    }
}