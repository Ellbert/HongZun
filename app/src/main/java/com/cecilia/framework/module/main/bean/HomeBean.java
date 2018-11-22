package com.cecilia.framework.module.main.bean;

import java.util.List;

public class HomeBean {

    private List<BoutiqueBean> brand;
    private List<HotBean> hot;

    public void setBrand(List<BoutiqueBean> brand) {
        this.brand = brand;
    }

    public List<BoutiqueBean> getBrand() {
        return brand;
    }

    public void setHot(List<HotBean> hot) {
        this.hot = hot;
    }

    public List<HotBean> getHot() {
        return hot;
    }

}
