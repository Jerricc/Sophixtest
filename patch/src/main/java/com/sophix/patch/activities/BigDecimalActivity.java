package com.sophix.patch.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.ActionBar;

import com.sophix.patch.R;
import com.google.android.material.snackbar.Snackbar;
import com.sophix.patch.R;
import com.sophix.patch.R2;
import com.sophix.patch.base.BaseActivity;

import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 大数运算界面
 */
public class BigDecimalActivity extends BaseActivity
{
    @BindView(R2.id.et_big_num1)
    protected EditText mBigNum1EditText;

    @BindView(R2.id.et_big_num2)
    protected EditText mBigNum2EditText;

    @BindView(R2.id.btn_add)
    protected Button mAddButton;

    @BindView(R2.id.btn_sub)
    protected Button mSubButton;

    @BindView(R2.id.btn_mul)
    protected Button mMulButton;

    @BindView(R2.id.btn_div)
    protected Button mDivButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_decimal);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("大数运算");
        }

        ButterKnife.bind(this);
    }


    /**
     * 处理界面上所有按钮的点击事件
     * @param v 被点击的控件view
     */
    @OnClick({R2.id.btn_add, R2.id.btn_sub, R2.id.btn_mul, R2.id.btn_div})
    public void handleAllButtonsClick(View v)
    {
        String s1 = mBigNum1EditText.getText().toString();
        String s2 = mBigNum2EditText.getText().toString();

        if(s1.length() == 0)
        {
            s1 = "0";
        }

        if(s2.length() == 0)
        {
            s2 = "0";
        }

        if(s1.indexOf("..") != -1 || s2.indexOf("..") != -1)
        {
            Snackbar.make(v, "小数点不能多于一个", Snackbar.LENGTH_SHORT).show();
            return;
        }

        BigDecimal bigDecimal1 = new BigDecimal(s1);
        BigDecimal bigDecimal2 = new BigDecimal(s2);

        switch (v.getId())
        {
            case R2.id.btn_add:
                BigDecimalResultActivity.actionStart(this, bigDecimal1.add(bigDecimal2).toString());
                break;

            case R2.id.btn_sub:
                BigDecimalResultActivity.actionStart(this, bigDecimal1.subtract(bigDecimal2).toString());
                break;

            case R2.id.btn_mul:
                BigDecimalResultActivity.actionStart(this, bigDecimal1.multiply(bigDecimal2).toString());
                break;

            case R2.id.btn_div:
                if(bigDecimal2.doubleValue() == 0)
                {
                    Snackbar.make(v, "除数不能为零", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                BigDecimalResultActivity.actionStart(this,
                        bigDecimal1.divide(bigDecimal2, 30, BigDecimal.ROUND_HALF_UP).toString());
                break;

            default:
        }
    }


    public static void actionStart(Context context)
    {
        context.startActivity(new Intent(context, BigDecimalActivity.class));
    }
}
