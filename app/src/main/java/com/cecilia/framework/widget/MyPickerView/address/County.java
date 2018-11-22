package com.cecilia.framework.widget.MyPickerView.address;

import java.io.Serializable;

public class County implements Serializable{
    private String areaId;
    private String areaName = "";
    private String lat;
    private String lng;

    @Override
    public String toString() {
        return "County{" +
                "areaId='" + areaId + '\'' +
                ", areaName='" + areaName + '\'' +
                ", lat='" + lat + '\'' +
                ", lng='" + lng + '\'' +
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
}
