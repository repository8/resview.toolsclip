package com.resview.toolsclip;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatSpinner;


import java.util.Arrays;
import java.util.List;


/**
 List<String> li = new ArrayList<>();
 li.add("SmokeM");
 li.add("SmokeH");
 spinner_smoke.initAsSpinner(li);
 spinner_smoke.setSpinnerPos(ManagerCommon.getSysSetup().typeSmoke - 10000);
 */
public class LeftTextBottomSpinner extends RelativeLayout {
    public TextView txt_left;
    public AppCompatSpinner spinner;
    private ArrayAdapter<String> arr_adapter;
    //=======================================
    private OnSpinnerSelectListener onSpinnerSelectListener;
    public interface OnSpinnerSelectListener{
        void OnSelectPos(int pos, String item);
    }
    public void setOnSpinnerSelectListener(OnSpinnerSelectListener listener) {
        this.onSpinnerSelectListener = listener;
    }

    //=======================================
    public LeftTextBottomSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.left_txt_bottom_spinner, this, true);
        txt_left= findViewById(R.id.txt_left);
        spinner = findViewById(R.id.spinner_leftterisp);

        TypedArray ta      = context.obtainStyledAttributes(attrs, R.styleable.androidMe);

        String text      = ta.getString(R.styleable.androidMe_text);
        boolean hideLeftTxt  = ta.getBoolean(R.styleable.androidMe_hideLeftTxt,false);
        float  textsize  = ta.getDimensionPixelSize(R.styleable.androidMe_textsize, 0);
        int colorTxt = ta.getColor(R.styleable.androidMe_textcolor, 0);
        String textedit = ta.getString(R.styleable.androidMe_textedit);
        int leftTextWidth = ta.getDimensionPixelSize(R.styleable.androidMe_leftTextWidth, 0);

        if (!TextUtils.isEmpty(text)) {
            txt_left.setText(text);
        }
        if(textsize!=0){
            txt_left.setTextSize(TypedValue.COMPLEX_UNIT_PX,textsize);
        }
        if(hideLeftTxt){
            txt_left.setVisibility(GONE);
        }
        if(colorTxt != 0){
            txt_left.setTextColor(colorTxt);
        }
        if (leftTextWidth!=0) {
            ViewGroup.LayoutParams lp = txt_left.getLayoutParams();
            lp.width = leftTextWidth;
            txt_left.setLayoutParams(lp);
        }
        if(!TextUtils.isEmpty(textedit)){
            String[] li = textedit.split(";");
            initSpinner(Arrays.asList(li));
        }
        ta.recycle();
    }

    public void setTextLeft(String strLeft) {
        if (!TextUtils.isEmpty(strLeft)) {
            txt_left.setText(strLeft);
        }
    }
    public void setTextEdit(String strLeft) {
        if (!TextUtils.isEmpty(strLeft)) {
            if(spinner.getAdapter()!=null){
                setSpinnerPos(strLeft);
            }
        }
    }
    public int getSpinnerPos(){
        return spinner.getSelectedItemPosition();
    }
    public String getSpinnerStr(){
        return spinner.getSelectedItem().toString();
    }

    public void initSpinner(List<String> list) {
        arr_adapter = new ArrayAdapter<String>(getContext(), R.layout.list_item_onlytext_spinner, list);
        arr_adapter.setDropDownViewResource(R.layout.list_item_onlytext_spinner_def);
        spinner.setAdapter(arr_adapter);
        spinner.setSelection(0,false);//禁用自动选一次
//        spinner.setDropDownHorizontalOffset(100);
        spinner.setDropDownVerticalOffset(Dp2Px.dipToPx(getContext(),50));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(onSpinnerSelectListener!=null)onSpinnerSelectListener.OnSelectPos(position,arr_adapter.getItem(position));
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    public void setSpinnerPos(int pos){
        spinner.setSelection(pos, false);
    }
    public void setSpinnerPos(String value){
        spinner.setSelection(arr_adapter.getPosition(value), false);
    }
    public void lockSpinner(boolean isLock){
        spinner.setEnabled(!isLock);
    }
}
