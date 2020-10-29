package com.resview.toolsclip;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.ReplacementTransformationMethod;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;



public class LeftTextRightEditSelect extends RelativeLayout {
    public TextView txt_left;
    public EditText txt_edit;
    public Button btn_confirm;
    public LeftTextRightEditSelect(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.left_txt_right_edit_select, this, true);
        txt_left= (TextView) findViewById(R.id.txt_left);
        txt_edit= (EditText) findViewById(R.id.txt_center);
        btn_confirm = findViewById(R.id.btn_confirm);
        TypedArray ta      = context.obtainStyledAttributes(attrs, R.styleable.androidMe);
        String hint    = ta.getString(R.styleable.androidMe_hint);
        String text    = ta.getString(R.styleable.androidMe_text);
        String rightTxt    = ta.getString(R.styleable.androidMe_righttxt);
        String inputType = ta.getString(R.styleable.androidMe_inputType);
        int leftTextWidth = ta.getDimensionPixelSize(R.styleable.androidMe_leftTextWidth, 0);
        boolean        hideRightTxt  = ta.getBoolean(R.styleable.androidMe_hideRightTxt, false);
        boolean        hideCenterTxt  = ta.getBoolean(R.styleable.androidMe_hideCenterTxt, false);
        int    maxLength = ta.getInteger(R.styleable.androidMe_maxLength, 0);
        openInput(inputTypeConvert(inputType), maxLength);

        if (!TextUtils.isEmpty(text)) {
            txt_left.setText(text);
        }
        if (!TextUtils.isEmpty(rightTxt)) {
            btn_confirm.setText(rightTxt);
        }
        if (!TextUtils.isEmpty(hint)) {
            txt_edit.setHint(hint);
        }
        if (leftTextWidth!=0) {
            ViewGroup.LayoutParams lp = txt_left.getLayoutParams();
            lp.width = leftTextWidth;
            txt_left.setLayoutParams(lp);
        }
        if(hideRightTxt)btn_confirm.setVisibility(GONE);
        if(hideCenterTxt)txt_edit.setVisibility(GONE);
        ta.recycle();
    }
    public boolean getSelect(){
        return btn_confirm.isSelected();
    }
    public void setSelect(boolean select){
        btn_confirm.setSelected(select);
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
