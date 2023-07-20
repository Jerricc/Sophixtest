package com.by.sophixtest;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ChangeColorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_color);

        TextView tv = findViewById(R.id.tv);

        tv.setText("我的颜色是蓝色");
        tv.setTextColor(getResources().getColor(R.color.purple_500));

//        tv.setText("我的颜色是绿色");
//        tv.setTextColor(getResources().getColor(R.color.teal_200));
    }
}