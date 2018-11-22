package com.cecilia.framework.widget.MyPickerView.address;

import java.io.Serializable;
import java.util.ArrayList;

public class City implements Serializable {
    private String areaId;
    private String areaName = "";
    private String lat;
    private String lng;
    private String sortLetters;//显示数据拼音的首字母
    private ArrayList<County> counties = new ArrayList<County>();

    @Override
    public String toString() {
        return "City{" +
                "areaId='" + areaId + '\'' +
                ", areaName='" + areaName + '\'' +
                ", lat='" + lat + '\'' +
                ", lng='" + lng + '\'' +
                ", sortLetters='" + sortLetters + '\'' +
                ", counties=" + counties +
                '}';
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public ArrayList<County> getCounties() {
        return counties;
    }

    public void setCounties(ArrayList<County> counties) {
        this.counties = counties;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getSortLetters() {
        return sortLetters;
    }

    public void setSortLetters(String sortLetters) {
        this.sortLetters = sortLetters;
    }
}
