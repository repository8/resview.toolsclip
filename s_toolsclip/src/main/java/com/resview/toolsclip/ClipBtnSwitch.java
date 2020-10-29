package com.resview.toolsclip;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;




/**
 * 控车主页面
 */
public class ClipBtnSwitch extends RelativeLayout {
    public TextView txt_name;
    private ImageView img;
    private boolean isOn = false;

    public ClipBtnSwitch(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.clip_btn_switch, this, true);
        txt_name = findViewById(R.id.btn);
        img = findViewById(R.id.img);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.androidMe);
        String name = ta.getString(R.styleable.androidMe_text);
        int textcolor = ta.getColor(R.styleable.androidMe_textcolor, Color.WHITE);
        int switchsize = ta.getDimensionPixelSize(R.styleable.androidMe_switchsize, 0);

        txt_name.setTextColor(textcolor);
        if (name != null && !name.equals("")) {
            txt_name.setText(name);
        }
        if (switchsize != 0) {
            ViewGroup.LayoutParams lp = img.getLayoutParams();
            lp.width = switchsize;
            lp.height = (int) (switchsize * 0.7);
            img.setLayoutParams(lp);
        }
        ta.recycle();
    }

    public void changeSwitch() {
        isOn = isOn ? false : true;
        img.setSelected(isOn);
    }

    public void setSwitch(boolean isOnn) {
        if (this.isOn != isOnn) img.setSelected(isOnn);
        this.isOn = isOnn;
    }

    public boolean getSwitchOn() {
        return isOn;
    }

}

