package com.sophix.patch.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;

import com.sophix.patch.R;
import com.sophix.patch.base.BaseActivity;

/**
 * 关于界面
 */
public class AboutActivity extends BaseActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("关于");
        }
    }

    public static void actionStart(Context context)
    {
        context.startActivity(new Intent(context, AboutActivity.class));
    }
}
