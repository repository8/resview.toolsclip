package com.resview.toolsclip;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.RelativeLayout;



public class ClipInput1View extends RelativeLayout {
    public EditText txt_edit;
    public ClipInput1View(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.clip_input1_view, this, true);
        txt_edit= findViewById(R.id.txt_input);
    }
}
