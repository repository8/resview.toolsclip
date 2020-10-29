package com.resview.toolsclip;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatSpinner;

import java.util.Arrays;
import java.util.List;


public class ClipText_Edit_Drop extends RelativeLayout {
    public TextView txt_left,txt_bottom;
    public AppCompatSpinner spinner;
    public AppCompatEditText txt_edit;
    private ArrayAdapter<String> arr_adapter;
    //=======================================
    private OnSpinnerSelectListener onSpinnerSelectListener;
    private OnEditTextChangeListener onEditTextChangeListener;
    public interface OnSpinnerSelectListener{
        void OnSelectPos(int pos, String item,Object tag);
    }
    public interface OnEditTextChangeListener{
        void onEditChange(String item,Object tag);
    }
    public void setOnSpinnerSelectListener(OnSpinnerSelectListener listener) {
        this.onSpinnerSelectListener = listener;
    }
    public void setOnEditTextChangeListener(OnEditTextChangeListener listener) {
        this.onEditTextChangeListener = listener;
    }
    //=======================================

    public ClipText_Edit_Drop(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.clip_text_edit_drop, this, true);
        txt_left = findViewById(R.id.txt_left);
        txt_bottom = findViewById(R.id.txt_bottom);
        txt_edit = findViewById(R.id.txt_edit);
        spinner= findViewById(R.id.spinner);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.androidMe);
        String hint = ta.getString(R.styleable.androidMe_hint);
        String text = ta.getString(R.styleable.androidMe_text);
        String textedit = ta.getString(R.styleable.androidMe_textedit);
        String inputType = ta.getString(R.styleable.androidMe_inputType);
        int leftTextWidth = ta.getDimensionPixelSize(R.styleable.androidMe_leftTextWidth, 0);
        openInput(inputTypeConvert(inputType));
        if (!TextUtils.isEmpty(text)) {
            txt_left.setText(text);
        }
        if (!TextUtils.isEmpty(hint)) {
            txt_edit.setHint(hint);
        }
        if (!TextUtils.isEmpty(textedit)) {
            String[] arr = textedit.split(";");
            if(arr.length == 1) {
                initAsEdit(textedit);
            }else{
                List<String> li = Arrays.asList(arr);
                initAsSpinner(li);
            }
        }
        if (leftTextWidth!=0) {
            ViewGroup.LayoutParams lp = txt_left.getLayoutParams();
            lp.width = leftTextWidth;
            txt_left.setLayoutParams(lp);
        }
        ta.recycle();
    }
    //===============================spinner====================================

    public void initAsTitle(String text) {
        txt_left.setVisibility(INVISIBLE);
        txt_edit.setVisibility(INVISIBLE);
        spinner.setVisibility(INVISIBLE);
        txt_bottom.setVisibility(VISIBLE);
        if (!TextUtils.isEmpty(text))txt_bottom.setText(text);
    }
    /**afterTextChanged不可用于list,只能通过tag识别数据改变**/
    public void initAsEdit(String text) {
        txt_left.setVisibility(VISIBLE);
        txt_edit.setVisibility(VISIBLE);
        spinner.setVisibility(INVISIBLE);
        txt_bottom.setVisibility(INVISIBLE);
        if (text!=null)txt_edit.setText(text);

        txt_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if(onEditTextChangeListener!=null)onEditTextChangeListener.onEditChange(s.toString(),ClipText_Edit_Drop.this.getTag());
            }
        });
    }
    public void initAsSpinner(List<String> list) {
        txt_left.setVisibility(VISIBLE);
        txt_edit.setVisibility(INVISIBLE);
        spinner.setVisibility(VISIBLE);
        txt_bottom.setVisibility(INVISIBLE);
        arr_adapter = new ArrayAdapter<String>(getContext(), R.layout.list_item_onlytext_spinner, list);
        arr_adapter.setDropDownViewResource(R.layout.list_item_onlytext_spinner_def);
        spinner.setAdapter(arr_adapter);
        spinner.setSelection(0,false);//禁用自动选一次
//        spinner.setDropDownHorizontalOffset(100);
        spinner.setDropDownVerticalOffset(Dp2Px.dipToPx(getContext(),50));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(onSpinnerSelectListener!=null)onSpinnerSelectListener.OnSelectPos(position,arr_adapter.getItem(position),ClipText_Edit_Drop.this.getTag());
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    //===============================spinner====================================
    public void setSpinnerPos(int pos){
        spinner.setSelection(pos, false);
    }
    public void setSpinnerPos(String value){
        spinner.setSelection(arr_adapter.getPosition(value), false);
    }
    public int getSpinnerPos(){
        return spinner.getSelectedItemPosition();
    }
    public String getSpinnerStr(){
        return spinner.getSelectedItem().toString();
    }
    //===============================spinner====================================

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
        txt_edit.setInputType(inputType);
    }


    public void setTextLeft(String strLeft) {
        if (!TextUtils.isEmpty(strLeft)) {
            txt_left.setText(strLeft);
        }
    }
    public void setLeftTextWidth(int leftTextWidth) {
        if (leftTextWidth!=0) {
            ViewGroup.LayoutParams lp = txt_left.getLayoutParams();
            lp.width = leftTextWidth;
            txt_left.setLayoutParams(lp);
        }
    }

    public void setTextEdit(String strLeft) {
        if (!TextUtils.isEmpty(strLeft)) {
            txt_edit.setText(strLeft);
            if(spinner.getAdapter()!=null){
                setSpinnerPos(strLeft);
            }
        }
    }

    public String getTextEdit() {
        if(spinner.getAdapter()!=null)return getSpinnerStr();
        return txt_edit.getText().toString().trim();
    }
}
