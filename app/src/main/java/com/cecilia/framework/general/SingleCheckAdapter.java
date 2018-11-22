package com.cecilia.framework.general;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseAdapter;
import com.cecilia.framework.base.BaseHolder;
import com.cecilia.framework.utils.ViewUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.OnCheckedChanged;

/**
 * @author stone
 */

public class SingleCheckAdapter extends BaseAdapter<String> {

    private int mSelectedPosition = -1;

    public SingleCheckAdapter(Context context) {
        super(context);
    }

    @Override
    protected BaseHolder<String> getHolder() {
        return new PromptChoiceHolder(SingleCheckAdapter.this);
    }

    public List<String> getData() {
        return mData;
    }

    public int getSelectedPosition() {
        return mSelectedPosition;
    }

    public void setSelectedPosition(int selectedPosition) {
        mSelectedPosition = selectedPosition;
    }

    class PromptChoiceHolder extends BaseHolder<String> {

        @BindView(R.id.tv_message)
        TextView mTvMessage;
        @BindView(R.id.cb_choice)
        CheckBox mCbChoice;

        PromptChoiceHolder(BaseAdapter<String> adapter) {
            super(adapter);
        }

        @Override
        protected View getHolderView() {
            return View.inflate(ViewUtil.getContext(), R.layout.item_lv_single_check, null);
        }

        @OnCheckedChanged(R.id.cb_choice)
        void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (mTvMessage.getTextColors().getDefaultColor() == ViewUtil.getColor(R.color.txt_first)) {
                mTvMessage.setTextColor(ViewUtil.getColor(R.color.color_main));
                mHolderView.setBackgroundColor(ViewUtil.getColor(R.color.bg_view));
            } else if (mTvMessage.getTextColors().getDefaultColor() == ViewUtil.getColor(R.color.color_main)) {
                mTvMessage.setTextColor(ViewUtil.getColor(R.color.txt_first));
                mHolderView.setBackgroundColor(ViewUtil.getColor(android.R.color.white));
            }
        }

        @Override
        protected void refreshHolderView(String data, final int position) {
            mTvMessage.setText(data);
            mHolderView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mSelectedPosition == position) {
                        mSelectedPosition = -1;
                    } else {
                        mSelectedPosition = position;
                    }
                    mAdapter.notifyDataSetChanged();
                }
            });
            if (position == mSelectedPosition) {
                mCbChoice.setChecked(true);
            } else {
                mCbChoice.setChecked(false);
            }
        }
    }

}
