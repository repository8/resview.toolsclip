package com.resview.toolsclip;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.ReplacementTransformationMethod;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;



public class LeftTextRightEditConfirm extends RelativeLayout {
    public TextView txt_left;
    public View view_splitline;
    public EditText txt_edit;
    public Button btn_confirm;
    public LeftTextRightEditConfirm(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.left_txt_right_edit_confirm, this, true);
        txt_left= findViewById(R.id.txt_left);
        txt_edit= findViewById(R.id.txt_center);
        btn_confirm = findViewById(R.id.btn_confirm);
        view_splitline= findViewById(R.id.view_splitline);
        TypedArray ta      = context.obtainStyledAttributes(attrs, R.styleable.androidMe);
        String hint    = ta.getString(R.styleable.androidMe_hint);
        String text    = ta.getString(R.styleable.androidMe_text);
        String rightTxt    = ta.getString(R.styleable.androidMe_righttxt);
        boolean        showbg  = ta.getBoolean(R.styleable.androidMe_showbg, false);
        boolean        splitLine  = ta.getBoolean(R.styleable.androidMe_splitLine, false);

        boolean        hideRightTxt  = ta.getBoolean(R.styleable.androidMe_hideRightTxt, false);
        boolean        hideLeftTxt  = ta.getBoolean(R.styleable.androidMe_hideLeftTxt, false);
        float        textsize  = ta.getDimensionPixelSize(R.styleable.androidMe_textsize, 0);
        int leftTextWidth = ta.getDimensionPixelSize(R.styleable.androidMe_leftTextWidth, 0);
        int colorTxt = ta.getColor(R.styleable.androidMe_textcolor, 0);


        String inputType = ta.getString(R.styleable.androidMe_inputType);
        int    maxLength = ta.getInteger(R.styleable.androidMe_maxLength, 0);
        openInput(inputTypeConvert(inputType), maxLength);

        if(textsize!=0){
            txt_left.setTextSize(TypedValue.COMPLEX_UNIT_PX,textsize);
            btn_confirm.setTextSize(TypedValue.COMPLEX_UNIT_PX,textsize);
            txt_edit.setTextSize(TypedValue.COMPLEX_UNIT_PX,textsize-5);
        }
        if(colorTxt != 0){
            txt_left.setTextColor(colorTxt);
        }
        if (!TextUtils.isEmpty(text)) {
            txt_left.setText(text);
        }
        if (!TextUtils.isEmpty(rightTxt)) {
            btn_confirm.setText(rightTxt);
        }
        if (!TextUtils.isEmpty(hint)) {
            txt_edit.setHint(hint);
        }
        if (showbg) {
            this.setBackgroundColor(getResources().getColor(R.color.color_normal_white));
        }
        if (splitLine) {
            view_splitline.setVisibility(VISIBLE);
        }else{
            view_splitline.setVisibility(INVISIBLE);
        }
        if (leftTextWidth != 0) {
            ViewGroup.LayoutParams lp = txt_left.getLayoutParams();
            lp.width = leftTextWidth;
            txt_left.setLayoutParams(lp);
        }
        if(hideLeftTxt)txt_left.setVisibility(GONE);
        if(hideRightTxt)txt_edit.setVisibility(GONE);
        ta.recycle();
    }



    private int inputTypeConvert(String inputType) {
        if(TextUtils.isEmpty(inputType))return 0;
        if (inputType.equals("phone")) return InputType.TYPE_CLASS_PHONE;
        if (inputType.equals("number")) return InputType.TYPE_CLASS_NUMBER;
        if (inputType.equals("password")) return InputType.TYPE_TEXT_VARIATION_PASSWORD;
        return 0;
    }

    private void openInput(int inputType, int maxnum) {
        if(inputType == 0)return;
        txt_edit.setVisibility(VISIBLE);
        txt_edit.setEnabled(true);
        txt_edit.setInputType(inputType);
        if (inputType == InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS) {
            txt_edit.setTransformationMethod(new AllCapTransformationMethod());
        }
        if (maxnum > 0) {
            InputFilter[] filters = {new InputFilter.LengthFilter(maxnum)};
            txt_edit.setFilters(filters);
        }
    }

    private class AllCapTransformationMethod extends ReplacementTransformationMethod {
        @Override
        protected char[] getOriginal() {
            char[] aa = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
            return aa;
        }

        @Override
        protected char[] getReplacement() {
            char[] cc = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
            return cc;
        }
    }
}
