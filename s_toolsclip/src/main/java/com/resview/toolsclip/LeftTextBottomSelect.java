package com.resview.toolsclip;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;





public class LeftTextBottomSelect extends RelativeLayout {

    public TextView txtName;
    public ImageView imgSelect;

    public LeftTextBottomSelect(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.left_txt_bottom_select, this, true);
        txtName = findViewById(R.id.txt_name);
        imgSelect = findViewById(R.id.img_select);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.androidMe);
        String text = ta.getString(R.styleable.androidMe_text);
        float textsize = ta.getDimensionPixelSize(R.styleable.androidMe_textsize, 0);
        int colorTxt = ta.getColor(R.styleable.androidMe_textcolor, 0);
        if (!TextUtils.isEmpty(text)) {
            txtName.setText(text);
        }
        if (colorTxt != 0) {
            txtName.setTextColor(colorTxt);
        }
        if (textsize != 0) {
            txtName.setTextSize(TypedValue.COMPLEX_UNIT_PX,textsize);
        }
        ta.recycle();
    }


    public boolean getSelect(){
        return imgSelect.isSelected();
    }
    public void setSelect(boolean select){
        if(imgSelect.isSelected()!=select)imgSelect.setSelected(select);
    }
}
