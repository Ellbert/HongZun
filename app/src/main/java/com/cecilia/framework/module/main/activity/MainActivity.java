package com.cecilia.framework.module.main.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.cecilia.framework.GcGuangApplication;
import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseActivity;
import com.cecilia.framework.general.EventBean;
import com.cecilia.framework.module.login.activity.LoginActivity;
import com.cecilia.framework.module.main.adapter.MainPagerAdapter;
import com.cecilia.framework.module.main.fragment.MainFragment;
import com.cecilia.framework.module.main.fragment.MeFragment;
import com.cecilia.framework.module.main.fragment.MoreFragment;
import com.cecilia.framework.module.main.fragment.OrderFragment;
import com.cecilia.framework.utils.DialogUtil;
import com.cecilia.framework.utils.GuangUtil;
import com.cecilia.framework.utils.LogUtil;
import com.cecilia.framework.utils.ToastUtil;
import com.cecilia.framework.utils.ViewUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnPageChange;

public class MainActivity extends BaseActivity {

    @BindView(R.id.vp_pager)
    ViewPager mVpPager;
    @BindView(R.id.rg_main_tap)
    RadioGroup mRgMainTap;
    private List<Fragment> mFragments = new ArrayList<>();
    //    private UpdateBroadCast mUpdateBroadCast;
    private Dialog mExitDialog;
//    private Dialog mOfflineDialog;
//    private CartFragment mCartFragment;

    public static void launch(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
//        ToastUtil.newSafelyShow(getHeight()+"");
//        ToastUtil.newSafelyShow(getWidth()+"");

    }

    @Override
    protected boolean isUseEventBus() {
        return true;
    }

    @Override
    protected void initDialog() {
        mExitDialog = DialogUtil.createPromptDialog(MainActivity.this,
                ViewUtil.getString(R.string.app_name), "您确定要退出吗？", ViewUtil.getString(R.string.ok), new DialogUtil.OnDialogViewButtonClickListener() {
                    @Override
                    public boolean onClick() {
                        finish();
                        return false;
                    }
                }, ViewUtil.getString(R.string.cancel), null, null);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void doEvents(EventBean event) {
        if (event.getType() == -1) {
            GuangUtil.saveUserInfo(null);
            GcGuangApplication.setUserBean(null);
            LoginActivity.launch(this);
            finish();
        }
    }

    @Override
    protected void initViews() {
        mFragments.add(new MainFragment());
        mFragments.add(new MoreFragment());
        mFragments.add(new OrderFragment());
        mFragments.add(new MeFragment());

        setBottomButtonCheck(0);
        mRgMainTap.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int index = 0; index < group.getChildCount(); index++) {
                    if (((RadioButton) group.getChildAt(index)).isChecked()) {
                        mVpPager.setCurrentItem(index);
                    }
                }
            }
        });

        mVpPager.setOffscreenPageLimit(5);
        mVpPager.setAdapter(new MainPagerAdapter(mFragments, getSupportFragmentManager()));
    }

    public void setBottomButtonCheck(int position) {
        ((RadioButton) mRgMainTap.getChildAt(position)).setChecked(true);
    }


    @OnPageChange(R.id.vp_pager)
    public void onPageSelected(int position) {
        ((RadioButton) mRgMainTap.getChildAt(position)).setChecked(true);
        // 获取当前被选中的页面, 初始化该页面数据
    }

    @Override
    protected void onDestroy() {
//        unregisterReceiver(mUpdateBroadCast);
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
//        if (mCartFragment.isPopupWindowShow()) {
//            mCartFragment.mGoodsSpecificPopupWindow.dismiss();
//        } else {
        mExitDialog.show();
//        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            setBottomButtonCheck(1);
        } else if (requestCode == 827 && resultCode == 0) {
            ToastUtil.newSafelyShow("商品评论成功");
        }
    }

//    @Override
//    public void onActivityReenter(int resultCode, Intent data) {
//        LogUtil.e("Activity onActivityReenter");
//        super.onActivityReenter(resultCode, data);
//    }


}
