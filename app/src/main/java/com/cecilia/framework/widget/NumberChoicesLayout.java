package com.cecilia.framework.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cecilia.framework.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author stone
 */
public class NumberChoicesLayout extends LinearLayout {

    @BindView(R.id.iv_minus)
    ImageView mIvMinus;
    @BindView(R.id.tv_select_number)
    TextView mTvSelectNumber;
    @BindView(R.id.iv_add)
    ImageView mIvAdd;

    private OnNumberChangeListener mOnNumberChangeListener;
    private View mView;
    private int mMinNumber = 0;
    private int mNowNumber = 0;
    private int mMaxNumber = 1;

    public NumberChoicesLayout(Context context) {
        super(context);
        initView(context);
    }

    public NumberChoicesLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        initView(context);
    }

    public NumberChoicesLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context);
    }

    private void initView(Context context) {
        if (mView == null) {
            mView = LayoutInflater.from(context).inflate(R.layout.layout_num_selector, this, true);
        }
        this.setGravity(Gravity.CENTER_VERTICAL);
        ButterKnife.bind(mView);
        if (mNowNumber == mMinNumber) {
            mIvMinus.setEnabled(false);
        }
    }

    /**
     * @param number    初始选择的数量
     * @param maxNumber 可选择的数量上限
     */
    public void setSelectNumber(String number, String maxNumber) {
        setSelectNumber("0", number, maxNumber);
    }

    /**
     * @param minNumber 可选择的数量下限
     * @param number    初始选择的数量
     * @param maxNumber 可选择的数量上限
     */
    public void setSelectNumber(String minNumber, String number, String maxNumber) {
        int nowNum = Integer.valueOf(number);
        mMaxNumber = Integer.valueOf(maxNumber) > 0 ? Integer.valueOf(maxNumber) : 1;
        mMinNumber = Integer.valueOf(minNumber) < 0 ? 0 : Integer.valueOf(minNumber) >= Integer.valueOf(maxNumber) ? Integer.valueOf(maxNumber) - 1 : Integer.valueOf(minNumber);
        if (nowNum <= mMinNumber) {
            number = minNumber;
        } else if (nowNum >= mMaxNumber) {
            number = maxNumber;
        }
        mTvSelectNumber.setText(number);
        mNowNumber = Integer.valueOf(getSelectNumber());
    }

    public String getSelectNumber() {
        return mTvSelectNumber.getText().toString();
    }

    @OnClick({R.id.iv_minus, R.id.iv_add})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_minus:
                mIvAdd.setEnabled(true);
                if (mNowNumber == mMinNumber) return;
                mTvSelectNumber.setText(String.valueOf(--mNowNumber));
                if (mNowNumber == mMinNumber) {
                    mIvMinus.setEnabled(false);
                } else {
                    mIvMinus.setEnabled(true);
                }
                break;
            case R.id.iv_add:
                mIvMinus.setEnabled(true);
                if (mNowNumber == mMaxNumber) return;
                mTvSelectNumber.setText(String.valueOf(++mNowNumber));
                if (mNowNumber == mMaxNumber) {
                    mIvAdd.setEnabled(false);
                } else {
                    mIvAdd.setEnabled(true);
                }
                break;
        }
        if (mOnNumberChangeListener != null) {
            mOnNumberChangeListener.onNumberChange(getSelectNumber());
        }
    }

    public void setNumberChangeListener(OnNumberChangeListener onNumberChangeListener) {
        mOnNumberChangeListener = onNumberChangeListener;
    }

    public interface OnNumberChangeListener {
        /**
         * @param num 变更后的数量
         */
        void onNumberChange(String num);
    }
}
