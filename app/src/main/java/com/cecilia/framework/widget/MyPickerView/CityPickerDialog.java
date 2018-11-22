package com.cecilia.framework.widget.MyPickerView;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;

import com.cecilia.framework.R;
import com.cecilia.framework.widget.MyPickerView.address.City;
import com.cecilia.framework.widget.MyPickerView.address.County;
import com.cecilia.framework.widget.MyPickerView.address.Province;
import com.cecilia.framework.widget.MyPickerView.wheel.OnWheelChangedListener;
import com.cecilia.framework.widget.MyPickerView.wheel.OnWheelClickedListener;
import com.cecilia.framework.widget.MyPickerView.wheel.WheelView;
import com.cecilia.framework.widget.MyPickerView.wheel.adapter.AbstractWheelTextAdapter;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;


public class CityPickerDialog extends Dialog {

    private final static int DEFAULT_ITEMS = 5;
    private final static int UPDATE_CITY_WHEEL = 11;
    private final static int UPDATE_COUNTY_WHEEL = 12;

    private Activity mActivity;
    private CityPickerHandler mHandler;

    private ArrayList<Province> mProvinces = new ArrayList<>();
    private ArrayList<City> mCities = new ArrayList<>();
    private ArrayList<County> mCounties = new ArrayList<>();
    private AbstractWheelTextAdapter mProvinceAdapter;
    private AbstractWheelTextAdapter mCityAdapter;
    private AbstractWheelTextAdapter mCountyAdapter;
    private WheelView mProvinceWheel;
    private WheelView mCitiesWheel;
    private WheelView mCountiesWheel;

    public interface onCityPickedListener {
        void onPicked(Province selectProvince, City selectCity,
                      County selectCounty);
    }

    public CityPickerDialog(Activity activity, List<Province> provinces,
                            Province defaultProvince, City defaultCity, County defaultCounty,
                            final onCityPickedListener listener) {
        super(activity);
        mActivity = activity;
        mHandler = new CityPickerHandler(this);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setGravity(Gravity.BOTTOM);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00000000")));
        getWindow().setWindowAnimations(R.style.style_bottom_animation);
        View rootView = getLayoutInflater().inflate(R.layout.dialog_city_picker, null);
        int screenWidth = mActivity.getWindowManager().getDefaultDisplay().getWidth();
        LayoutParams params = new LayoutParams(screenWidth, LayoutParams.MATCH_PARENT);
        super.setContentView(rootView, params);

        mProvinces.addAll(provinces);

        initViews();
        setDefaultArea(defaultProvince, defaultCity, defaultCounty);

        View done = findViewById(R.id.done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    Province province = mProvinces.size() > 0 ? mProvinces.get(mProvinceWheel.getCurrentItem()) : null;
                    City city = mCities.size() > 0 ? mCities.get(mCitiesWheel.getCurrentItem()) : null;
                    County county = mCounties.size() > 0 ? mCounties.get(mCountiesWheel.getCurrentItem()) : null;
                    listener.onPicked(province, city, county);
                }
                dismiss();
            }
        });

        View cancel = findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    private void setDefaultArea(Province defaultProvince, City defaultCity, County defaultCounty) {
        int provinceItem = 0;
        int cityItem = 0;
        int countyItem = 0;

        if (defaultProvince == null) {
            defaultProvince = mProvinces.get(0);
            provinceItem = 0;
        } else {
            for (int i = 0; i < mProvinces.size(); i++) {
                if (mProvinces.get(i).getAreaId().equals(defaultProvince.getAreaId())) {
                    provinceItem = i;
                    break;
                }
            }
        }
        mCities.clear();
        mCities.addAll(defaultProvince.getCities());
        if (mCities.size() == 0) {
            mCities.add(new City());
            cityItem = 0;
        } else if (defaultCity == null) {
            defaultCity = mCities.get(0);
            cityItem = 0;
        } else {
            for (int i = 0; i < mCities.size(); i++) {
                if (mCities.get(i).getAreaId().equals(defaultCity.getAreaId())) {
                    cityItem = i;
                    break;
                }
            }
        }

        mCounties.clear();
        mCounties.addAll(defaultCity.getCounties());
        if (mCounties.size() == 0) {
            mCounties.add(new County());
            countyItem = 0;
        } else if (defaultCounty == null) {
            defaultCounty = mCounties.get(0);
            countyItem = 0;
        } else {
            for (int i = 0; i < mCounties.size(); i++) {
                if (mCounties.get(i).getAreaId()
                        .equals(defaultCounty.getAreaId())) {
                    countyItem = i;
                    break;
                }
            }
        }
        mProvinceWheel.setCurrentItem(provinceItem, false);
        mCitiesWheel.setCurrentItem(cityItem, false);
        mCountiesWheel.setCurrentItem(countyItem, false);
    }

    private void initViews() {
        mProvinceWheel = (WheelView) findViewById(R.id.provinceWheel);
        mCitiesWheel = (WheelView) findViewById(R.id.citiesWheel);
        mCountiesWheel = (WheelView) findViewById(R.id.countiesWheel);

        mProvinceAdapter = new AbstractWheelTextAdapter(mActivity, R.layout.item_iv_wheel_text) {
            @Override
            public int getItemsCount() {
                return mProvinces.size();
            }

            @Override
            protected CharSequence getItemText(int index) {
                return mProvinces.get(index).getAreaName();
            }
        };

        mCityAdapter = new AbstractWheelTextAdapter(mActivity, R.layout.item_iv_wheel_text) {
            @Override
            public int getItemsCount() {
                return mCities.size();
            }

            @Override
            protected CharSequence getItemText(int index) {
                return mCities.get(index).getAreaName();
            }
        };

        mCountyAdapter = new AbstractWheelTextAdapter(mActivity, R.layout.item_iv_wheel_text) {
            @Override
            public int getItemsCount() {
                return mCounties.size();
            }

            @Override
            protected CharSequence getItemText(int index) {
                return mCounties.get(index).getAreaName();
            }
        };

        mProvinceWheel.setViewAdapter(mProvinceAdapter);
        mProvinceWheel.setCyclic(false);
        mProvinceWheel.setVisibleItems(DEFAULT_ITEMS);

        mCitiesWheel.setViewAdapter(mCityAdapter);
        mCitiesWheel.setCyclic(false);
        mCitiesWheel.setVisibleItems(DEFAULT_ITEMS);

        mCountiesWheel.setViewAdapter(mCountyAdapter);
        mCountiesWheel.setCyclic(false);
        mCountiesWheel.setVisibleItems(DEFAULT_ITEMS);

        OnWheelClickedListener clickListener = new OnWheelClickedListener() {

            @Override
            public void onItemClicked(WheelView wheel, int itemIndex) {
                if (itemIndex != wheel.getCurrentItem()) {
                    wheel.setCurrentItem(itemIndex, true, 500);
                }
            }
        };

        mProvinceWheel.addClickingListener(clickListener);
        mCitiesWheel.addClickingListener(clickListener);
        mCountiesWheel.addClickingListener(clickListener);

        mProvinceWheel.addChangingListener(new OnWheelChangedListener() {

            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                mHandler.removeMessages(UPDATE_CITY_WHEEL);
                Message msg = Message.obtain();
                msg.what = UPDATE_CITY_WHEEL;
                msg.arg1 = newValue;
                mHandler.sendMessageDelayed(msg, 50);
            }
        });

        mCitiesWheel.addChangingListener(new OnWheelChangedListener() {

            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                mHandler.removeMessages(UPDATE_COUNTY_WHEEL);
                Message msg = Message.obtain();
                msg.what = UPDATE_COUNTY_WHEEL;
                msg.arg1 = newValue;
                mHandler.sendMessageDelayed(msg, 50);

            }
        });
    }

    private static class CityPickerHandler extends Handler {

        private final WeakReference<CityPickerDialog> mDialog;

        private CityPickerHandler(CityPickerDialog dialog) {
            mDialog = new WeakReference<>(dialog);
        }

        @Override
        public void handleMessage(Message msg) {
            CityPickerDialog cityPickerDialog = mDialog.get();
            if (!cityPickerDialog.isShowing()) {
                return;
            }
            switch (msg.what) {
                case UPDATE_CITY_WHEEL:
                    cityPickerDialog.mCities.clear();
                    cityPickerDialog.mCities.addAll(cityPickerDialog.mProvinces.get(msg.arg1).getCities());
                    cityPickerDialog.mCitiesWheel.invalidateWheel(true);
                    cityPickerDialog.mCitiesWheel.setCurrentItem(0, false);

                    cityPickerDialog.mCounties.clear();
                    cityPickerDialog.mCounties.addAll(cityPickerDialog.mCities.get(0).getCounties());
                    cityPickerDialog.mCountiesWheel.invalidateWheel(true);
                    cityPickerDialog.mCountiesWheel.setCurrentItem(0, false);
                    break;
                case UPDATE_COUNTY_WHEEL:
                    cityPickerDialog.mCounties.clear();
                    cityPickerDialog.mCounties.addAll(cityPickerDialog.mCities.get(msg.arg1).getCounties());
                    cityPickerDialog.mCountiesWheel.invalidateWheel(true);
                    cityPickerDialog.mCountiesWheel.setCurrentItem(0, false);
                    break;
                default:
                    break;
            }
        }
    }
}
