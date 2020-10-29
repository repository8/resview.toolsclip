package com.resview.toolsclip;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;



public class ClipInput2Line extends RelativeLayout {
    public TextView txt_left1,txt_left2;
    public EditText edit1,edit2;
    public ClipInput2Line(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.clip_input2line, this, true);
        txt_left1= findViewById(R.id.txt_left1);
        txt_left2= findViewById(R.id.txt_left2);
        edit1= findViewById(R.id.edit1);
        edit2= findViewById(R.id.edit2);
    }
}
