package com.resview.toolsclip;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;



public class ClipTitleHead extends RelativeLayout {
    public TextView txt_title;
    public TextView txt_right;

    public ClipTitleHead(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.clip_titlehead, this, true);
        txt_title = findViewById(R.id.txt_title);
        txt_right = findViewById(R.id.txt_right);
        TypedArray ta      = context.obtainStyledAttributes(attrs, R.styleable.androidMe);
        String text    = ta.getString(R.styleable.androidMe_text);
        if (text != null && !text.equals("")) {
            txt_title.setText(text);
        }
        ta.recycle();
    }

    public void setTitle(String title) {
        txt_title.setText(title);
    }
}

