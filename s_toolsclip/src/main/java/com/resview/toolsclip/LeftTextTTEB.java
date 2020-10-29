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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**

 <com.resview.toolsclip.LeftTextTTEB
 android:id="@+id/aoi_ad2"
 android:layout_width="match_parent"
 android:layout_height="45dp"
 android:layout_marginTop="20dp"
 app:name="端口2："
 app:value="00000"
 app:nameConfirm="标定"
 app:nameWidth="80dp"
 app:valueWidth="80dp"
 app:confirmWidth="80dp"
 app:hint="00000"
 app:leftTextWidth="200dp"
 />
 */

public class LeftTextTTEB extends LinearLayout {
    public TextView txt_name,txt_value;
    public EditText edit_input;
    public Button btn_confirm;
    public LeftTextTTEB(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.left_txt_tteb, this, true);
        txt_name = findViewById(R.id.txt_name);
        txt_value = findViewById(R.id.txt_value);
        edit_input = findViewById(R.id.edit_input);
        btn_confirm = findViewById(R.id.btn_confirm);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.androidMe);
        //===========================================================
        String hint = ta.getString(R.styleable.androidMe_hint);
        if (!TextUtils.isEmpty(hint)) {
            edit_input.setHint(hint);
        }
        String name = ta.getString(R.styleable.androidMe_name);
        if (!TextUtils.isEmpty(name)) {
            txt_name.setText(name);
        }
        String value = ta.getString(R.styleable.androidMe_value);
        if (!TextUtils.isEmpty(value)) {
            txt_value.setText(value);
        }
        String nameConfirm = ta.getString(R.styleable.androidMe_nameConfirm);
        if (!TextUtils.isEmpty(nameConfirm)) {
            btn_confirm.setText(nameConfirm);
        }
        //===========================================================
        int nameWidth = ta.getDimensionPixelSize(R.styleable.androidMe_nameWidth, 0);
        if (nameWidth != 0) {
            ViewGroup.LayoutParams lp = txt_name.getLayoutParams();
            lp.width = nameWidth;
            txt_name.setLayoutParams(lp);
        }
        int valueWidth = ta.getDimensionPixelSize(R.styleable.androidMe_valueWidth, 0);
        if (valueWidth != 0) {
            ViewGroup.LayoutParams lp = txt_value.getLayoutParams();
            lp.width = valueWidth;
            txt_value.setLayoutParams(lp);
        }
        int confirmWidth = ta.getDimensionPixelSize(R.styleable.androidMe_confirmWidth, 0);
        if (confirmWidth != 0) {
            ViewGroup.LayoutParams lp = btn_confirm.getLayoutParams();
            lp.width = confirmWidth;
            btn_confirm.setLayoutParams(lp);
        }
        //===========================================================
        float textsize = ta.getDimensionPixelSize(R.styleable.androidMe_textsize, 0);
        if (textsize != 0) {
            txt_name.setTextSize(TypedValue.COMPLEX_UNIT_PX, textsize);
            txt_value.setTextSize(TypedValue.COMPLEX_UNIT_PX, textsize);
            edit_input.setTextSize(TypedValue.COMPLEX_UNIT_PX, textsize - 5);
            btn_confirm.setTextSize(TypedValue.COMPLEX_UNIT_PX, textsize);
        }
        //===========================================================
        int colorTxt = ta.getColor(R.styleable.androidMe_textcolor, 0);
        if (colorTxt != 0) {
            txt_name.setTextColor(colorTxt);
            txt_value.setTextColor(colorTxt);
        }
        //===========================================================
        String inputType = ta.getString(R.styleable.androidMe_inputType);
        int maxLength = ta.getInteger(R.styleable.androidMe_maxLength, 0);
        openInput(inputTypeConvert(inputType), maxLength);
        ta.recycle();
    }


    private int inputTypeConvert(String inputType) {
        if (TextUtils.isEmpty(inputType)) return 0;
        if (inputType.equals("phone")) return InputType.TYPE_CLASS_PHONE;
        if (inputType.equals("number")) return InputType.TYPE_CLASS_NUMBER;
        if (inputType.equals("password")) return InputType.TYPE_TEXT_VARIATION_PASSWORD;
        return 0;
    }

    private void openInput(int inputType, int maxnum) {
        if (inputType == 0) return;
        edit_input.setVisibility(VISIBLE);
        edit_input.setEnabled(true);
        edit_input.setInputType(inputType);
        if (inputType == InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS) {
            edit_input.setTransformationMethod(new AllCapTransformationMethod());
        }
        if (maxnum > 0) {
            InputFilter[] filters = {new InputFilter.LengthFilter(maxnum)};
            edit_input.setFilters(filters);
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
