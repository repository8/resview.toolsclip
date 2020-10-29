package com.resview.toolsclip;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.RelativeLayout;



public class ClipInput4View extends RelativeLayout {
    public EditText txt_edit;
    public ClipInput4View(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.clip_input4_view, this, true);
        txt_edit= (EditText) findViewById(R.id.txt_no);
    }
}
