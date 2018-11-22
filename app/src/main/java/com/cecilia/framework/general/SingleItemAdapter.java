package com.cecilia.framework.general;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseAdapter;
import com.cecilia.framework.base.BaseHolder;
import com.cecilia.framework.utils.ViewUtil;

import butterknife.BindView;

/**
 * @author stone
 */

public class SingleItemAdapter extends BaseAdapter<String> {

    public SingleItemAdapter(Context context) {
        super(context);
    }

    @Override
    protected BaseHolder<String> getHolder() {
        return new PromptChoiceHolder(SingleItemAdapter.this);
    }

    class PromptChoiceHolder extends BaseHolder<String> {

        @BindView(R.id.tv_message)
        TextView mTvMessage;

        PromptChoiceHolder(BaseAdapter<String> adapter) {
            super(adapter);
        }

        @Override
        protected View getHolderView() {
            return View.inflate(ViewUtil.getContext(), R.layout.item_lv_single_item, null);
        }

        @Override
        protected void refreshHolderView(String data, int position) {
            mTvMessage.setText(data);
        }
    }

}
