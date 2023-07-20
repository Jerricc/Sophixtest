package com.by.sophixtest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.taobao.sophix.SophixManager;

public class MainActivity extends AppCompatActivity {

    public String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SophixManager.getInstance().queryAndLoadNewPatch();


//        Button btn1 = findViewById(R.id.btn1);
        Button btn2 = findViewById(R.id.btn2);
//        Button btn3 = findViewById(R.id.btn3);
        Button btn4 = findViewById(R.id.btn4);

//        btn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //修复java
//                startActivity(new Intent(MainActivity.this, ChangeJavaActivity.class));
//            }
//        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //修复图片
                startActivity(new Intent(MainActivity.this, ChangeImageActivity.class));
            }
        });
//        btn3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //修复color
//                startActivity(new Intent(MainActivity.this, ChangeColorActivity.class));
//            }
//        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //修复资源
                startActivity(new Intent(MainActivity.this, ChangeJavaActivity.class));
            }
        });
    }
}