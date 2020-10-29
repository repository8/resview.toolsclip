package com.resview.toolsclip;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputType;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatEditText;

/**

 <com.resview.toolsclip.LeftTextRightEdit
 android:id="@+id/aoi_ip"
 android:layout_width="match_parent"
 android:layout_height="45dp"
 android:layout_marginTop="30dp"
 app:text="AOI_IP："
 app:textcolor="#287961"
 app:textsize="20sp"
 app:leftTextWidth="200dp"
 app:hint="192.168.0.0"/>
 */
public class LeftTextRightEdit extends RelativeLayout {
    public TextView txt_left;
    //    public View view_splitline;
    public AppCompatEditText txt_edit;

    public LeftTextRightEdit(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.left_txt_right_edit, this, true);
        txt_left = (TextView) findViewById(R.id.txt_left);
        txt_edit =  findViewById(R.id.txt_center);
        txt_edit.setImeOptions(EditorInfo.IME_ACTION_DONE);
//        view_splitline= findViewById(R.id.view_splitline);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.androidMe);
        String hint = ta.getString(R.styleable.androidMe_hint);
        String text = ta.getString(R.styleable.androidMe_text);
        String textedit = ta.getString(R.styleable.androidMe_textedit);
        String inputType = ta.getString(R.styleable.androidMe_inputType);
        int colorTxt = ta.getColor(R.styleable.androidMe_textcolor, 0);
        boolean enableSafeKeyBoard = ta.getBoolean(R.styleable.androidMe_enableSafeKeyBoard, true);
        float textsize = ta.getDimensionPixelSize(R.styleable.androidMe_textsize, 0);
        int leftTextWidth = ta.getDimensionPixelSize(R.styleable.androidMe_leftTextWidth, 0);
        boolean hideRightTxt = ta.getBoolean(R.styleable.androidMe_hideRightTxt, false);
        openInput(inputTypeConvert(inputType));
        if (!TextUtils.isEmpty(text)) {
            txt_left.setText(text);
        }
        if (!TextUtils.isEmpty(hint)) {
            txt_edit.setHint(hint);
        }
        if (!TextUtils.isEmpty(textedit)) {
            txt_edit.setText(textedit);
        }
        if(colorTxt != 0){
            txt_left.setTextColor(colorTxt);
        }
//        if (splitLine) {
//            view_splitline.setVisibility(VISIBLE);
//        }else{
//            view_splitline.setVisibility(INVISIBLE);
//        }
        if (textsize != 0) {
            txt_left.setTextSize(TypedValue.COMPLEX_UNIT_PX,textsize);
            txt_edit.setTextSize(TypedValue.COMPLEX_UNIT_PX,textsize);
        }
        if (leftTextWidth!=0) {
            ViewGroup.LayoutParams lp = txt_left.getLayoutParams();
            lp.width = leftTextWidth;
            txt_left.setLayoutParams(lp);
        }
        if (hideRightTxt) txt_edit.setVisibility(GONE);
        ta.recycle();
    }

    private int inputTypeConvert(String inputType) {
        if (TextUtils.isEmpty(inputType)) return 0;
        if (inputType.equals("phone")) return InputType.TYPE_CLASS_PHONE;
        if (inputType.equals("number")) return InputType.TYPE_CLASS_NUMBER;
        if (inputType.equals("text")) return InputType.TYPE_CLASS_TEXT;
        if (inputType.equals("passwordShow")) return InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD;
        if (inputType.equals("password")) return InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD;
        return 0;
    }
    /**外部使用**/
    public void changePasswordDis(boolean isDisplay) {
        if(isDisplay){
            txt_edit.setInputType(inputTypeConvert("passwordShow"));
        }else{
            txt_edit.setInputType(inputTypeConvert("password"));
        }
    }

    private void openInput(int inputType) {
        if (inputType == 0) return;
        txt_edit.setInputType(inputType);
    }

    public void setText(String strLeft) {
        if (!TextUtils.isEmpty(strLeft)) {
            txt_left.setText(strLeft);
        }
    }
    public void setEdit(String strEdit) {
        if (!TextUtils.isEmpty(strEdit)) {
            txt_edit.setText(strEdit);
        }
    }

    public String getInputText() {
        return txt_edit.getText().toString().trim();
    }
}
