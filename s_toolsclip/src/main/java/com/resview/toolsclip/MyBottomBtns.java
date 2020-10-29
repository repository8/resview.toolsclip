package com.resview.toolsclip;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.constraintlayout.widget.ConstraintLayout;


public class MyBottomBtns extends ConstraintLayout {
    public Button btn_left,btn_right1,btn_right2,btn_right3,btn_right4,btn_right5,btn_right6;

    public MyBottomBtns(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.asm_clip_bottombtns, this, true);
        btn_left = findViewById(R.id.btn_left);
        btn_right1 = findViewById(R.id.btn_right1);
        btn_right2 = findViewById(R.id.btn_right2);
        btn_right3 = findViewById(R.id.btn_right3);
        btn_right4 = findViewById(R.id.btn_right4);
        btn_right5 = findViewById(R.id.btn_right5);
        btn_right6 = findViewById(R.id.btn_right6);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.androidMe);
        int textsize = ta.getDimensionPixelSize(R.styleable.androidMe_textsize,0);
        int maxLength = ta.getInteger(R.styleable.androidMe_maxLength,0);
        if(textsize!=0){
            btn_left.setTextSize(TypedValue.COMPLEX_UNIT_PX,textsize);
            btn_right1.setTextSize(TypedValue.COMPLEX_UNIT_PX,textsize);
            btn_right2.setTextSize(TypedValue.COMPLEX_UNIT_PX,textsize);
            btn_right3.setTextSize(TypedValue.COMPLEX_UNIT_PX,textsize);
            btn_right4.setTextSize(TypedValue.COMPLEX_UNIT_PX,textsize);
            btn_right5.setTextSize(TypedValue.COMPLEX_UNIT_PX,textsize);
            btn_right6.setTextSize(TypedValue.COMPLEX_UNIT_PX,textsize);
        }
        String text = ta.getString(R.styleable.androidMe_text);
        if (!TextUtils.isEmpty(text)) {
            String[] arr = text.split(";");
            if(arr.length == 0)return;
            if(maxLength<=1 || maxLength>=6)maxLength = 4;
            if(TextUtils.isEmpty(arr[0]))maxLength+=1;
            if(arr.length>=1 && !TextUtils.isEmpty(arr[0]))btn_left.setText(arr[0]);
            else btn_left.setVisibility(GONE);
            if(arr.length>=2 && !TextUtils.isEmpty(arr[1]))btn_right1.setText(arr[1]);
            else btn_right1.setVisibility(maxLength>=2 ? INVISIBLE : GONE);
            if(arr.length>=3 && !TextUtils.isEmpty(arr[2]))btn_right2.setText(arr[2]);
            else btn_right2.setVisibility(maxLength>=3 ? INVISIBLE : GONE);
            if(arr.length>=4 && !TextUtils.isEmpty(arr[3]))btn_right3.setText(arr[3]);
            else btn_right3.setVisibility(maxLength>=4 ? INVISIBLE : GONE);
            if(arr.length>=5 && !TextUtils.isEmpty(arr[4]))btn_right4.setText(arr[4]);
            else btn_right4.setVisibility(maxLength>=5 ? INVISIBLE : GONE);
            if(arr.length>=6 && !TextUtils.isEmpty(arr[5]))btn_right5.setText(arr[5]);
            else btn_right5.setVisibility(maxLength>=6 ? INVISIBLE : GONE);
            if(arr.length>=7 && !TextUtils.isEmpty(arr[6]))btn_right6.setText(arr[6]);
            else btn_right6.setVisibility(GONE);
        }
        ta.recycle();
    }
}
