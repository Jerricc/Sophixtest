package com.by.sophixtest;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ChangeJavaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_java);

        TextView tv = findViewById(R.id.tv);

        tv.setText("未修复1.1.0");
//        tv.setText("修复后V1.1.0");
//        Toast.makeText(ChangeJavaActivity.this,"修复成功",Toast.LENGTH_LONG).show();
    }
}