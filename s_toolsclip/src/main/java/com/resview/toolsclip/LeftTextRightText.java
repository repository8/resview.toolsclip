package com.resview.toolsclip;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**

 <com.resview.toolsclip.LeftTextRightText
 android:id="@+id/txt_data0"
 android:layout_width="match_parent"
 android:layout_height="35dp"
 app:leftTextWidth="160dp"
 app:lefttxt="当次检测结果(kg):"
 app:righttxt="0"
 app:textcolor="@color/color_normal_white"
 app:textsize="25dp" />
 */

public class LeftTextRightText extends RelativeLayout {
    public TextView txt_left, txt_right;

    public LeftTextRightText(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.left_txt_right_text, this, true);
        txt_left = findViewById(R.id.txt_left);
        txt_right = findViewById(R.id.txt_right);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.androidMe);
        String lefttxt = ta.getString(R.styleable.androidMe_lefttxt);
        String righttxt = ta.getString(R.styleable.androidMe_righttxt);
        int colorTxt = ta.getColor(R.styleable.androidMe_textcolor, 0);
        float textsize = ta.getDimensionPixelSize(R.styleable.androidMe_textsize, 0);
        int leftTextWidth = ta.getDimensionPixelSize(R.styleable.androidMe_leftTextWidth, 0);
        boolean hideRightTxt = ta.getBoolean(R.styleable.androidMe_hideRightTxt, false);
        if (!TextUtils.isEmpty(lefttxt)) {
            txt_left.setText(lefttxt);
        }
        if (!TextUtils.isEmpty(righttxt)) {
            txt_right.setText(righttxt);
        }
        if (textsize != 0) {
            txt_left.setTextSize(TypedValue.COMPLEX_UNIT_PX,textsize);
            txt_right.setTextSize(TypedValue.COMPLEX_UNIT_PX,textsize);
        }
        if(colorTxt != 0){
            txt_left.setTextColor(colorTxt);
            txt_right.setTextColor(colorTxt);
        }
        if (leftTextWidth != 0) {
            ViewGroup.LayoutParams lp = txt_left.getLayoutParams();
            lp.width = leftTextWidth;
            txt_left.setLayoutParams(lp);
        }
        if (hideRightTxt) txt_right.setVisibility(GONE);
        ta.recycle();
    }
    public void setText_a_(){
        txt_left.setText("----");
        txt_right.setText("----");
    }
    public boolean isText_a_(){
        if(txt_left.getText().toString().trim().equals("----"))return true;
        return false;
    }
}
