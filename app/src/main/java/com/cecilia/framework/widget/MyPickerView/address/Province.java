package com.cecilia.framework.widget.MyPickerView.address;

import java.io.Serializable;
import java.util.ArrayList;

public class Province implements Serializable {
    private String areaId;
    private String areaName = "";
    private ArrayList<City> cities = new ArrayList<City>();

    @Override
    public String toString() {
        return "Province{" +
                "areaId='" + areaId + '\'' +
                ", areaName='" + areaName + '\'' +
                ", cities=" + cities +
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

    public ArrayList<City> getCities() {
        return cities;
    }

    public void setCities(ArrayList<City> cities) {
        this.cities = cities;
    }

}
