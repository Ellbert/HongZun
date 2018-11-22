package com.cecilia.framework.widget.CitylistSelect;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import com.cecilia.framework.R;
import com.cecilia.framework.utils.DensityUtil;
import com.cecilia.framework.utils.ViewUtil;

/**
 *
 * @author: xiaolijuan
 * @description: 自定义带有删除功能的EditText
 * @projectName: SelectCityDome
 * @date: 2016-03-01
 * @time: 15:59
 */
public class EditTextWithDel extends AppCompatEditText {
    private final static String TAG = "EditTextWithDel";
    private Drawable mImgInable;
    private Drawable mImgAble;
    private Context mContext;
    private int mImageAbleLength;
    public EditTextWithDel(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public EditTextWithDel(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        init();
    }

    public EditTextWithDel(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    private void init() {
        mImageAbleLength = DensityUtil.px2dp(mContext,60);
        mImgAble = ViewUtil.getDrawable(
                R.mipmap.icn_close_white);
        addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                setDrawable();
            }
        });
        mImgAble.setBounds(0,0,mImageAbleLength,mImageAbleLength);
        setDrawable();
    }

    // 设置删除图片
    private void setDrawable() {
        if (length() < 1) {
            setCompoundDrawables(null, null, null, null);
        } else {
            setCompoundDrawables(null, null, mImgAble, null);
        }
    }

    // 处理删除事件
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mImgAble != null && event.getAction() == MotionEvent.ACTION_UP) {
            int eventX = (int) event.getRawX();
            int eventY = (int) event.getRawY();
            Log.e(TAG, "eventX = " + eventX + "; eventY = " + eventY);
            Rect rect = new Rect();
            getGlobalVisibleRect(rect);
            rect.left = rect.right - 50;
            Log.e(TAG,"rect.left = "+rect.left+": rect.right ="+rect.right);
            if (rect.contains(eventX+50, eventY))
                setText("");
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
