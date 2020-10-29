package com.resview.toolsclip;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.InputType;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;


import androidx.appcompat.widget.AppCompatEditText;



public class LeftTextBottomEdit extends RelativeLayout {

    public TextView txtName;
    public AppCompatEditText editInput;

    public LeftTextBottomEdit(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.left_txt_bottom_edit, this, true);
        txtName = findViewById(R.id.txt_name);
        editInput = findViewById(R.id.edit_input);
        editInput.setImeOptions(EditorInfo.IME_ACTION_DONE);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.androidMe);
        String hint = ta.getString(R.styleable.androidMe_hint);
        String text = ta.getString(R.styleable.androidMe_text);
        String textedit = ta.getString(R.styleable.androidMe_textedit);
        String inputType = ta.getString(R.styleable.androidMe_inputType);
        float textsize = ta.getDimensionPixelSize(R.styleable.androidMe_textsize, 0);
        int colorTxt = ta.getColor(R.styleable.androidMe_textcolor, 0);
        boolean enableEdit = ta.getBoolean(R.styleable.androidMe_enableEdit, true);
        openInput(inputTypeConvert(inputType));
        if (!TextUtils.isEmpty(text)) {
            txtName.setText(text);
        }
        if (!TextUtils.isEmpty(hint)) {
            editInput.setHint(hint);
        }
        if (!TextUtils.isEmpty(textedit)) {
            editInput.setText(textedit);
        }
        if(colorTxt != 0){
            txtName.setTextColor(colorTxt);
        }
        if (textsize != 0) {
            txtName.setTextSize(TypedValue.COMPLEX_UNIT_PX,textsize);
            editInput.setTextSize(TypedValue.COMPLEX_UNIT_PX,textsize);
        }
        if (!enableEdit){
            editInput.setEnabled(false);
            editInput.setBackgroundColor(Color.parseColor("#B6B6B6"));
        }
        ta.recycle();
    }

    private int inputTypeConvert(String inputType) {
        if (TextUtils.isEmpty(inputType)) return 0;
        if (inputType.equals("phone")) return InputType.TYPE_CLASS_PHONE;
        if (inputType.equals("number")) return InputType.TYPE_CLASS_NUMBER;
        if (inputType.equals("text")) return InputType.TYPE_CLASS_TEXT;
        if (inputType.equals("password")) return InputType.TYPE_TEXT_VARIATION_PASSWORD;
        return 0;
    }

    private void openInput(int inputType) {
        if (inputType == 0) return;
        editInput.setInputType(inputType);
    }

    public void setText(String strLeft) {
        if (!TextUtils.isEmpty(strLeft) && !editInput.getText().toString().trim().equals(strLeft)) {
            editInput.setText(strLeft);
        }
    }
    public EditText getEditInput(){
        return editInput;
    }

    public String getInputText() {
        return editInput.getText().toString().trim();
    }
}
