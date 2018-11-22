package com.cecilia.framework.module.main.bean;

public class AdvertisingBean {

    private int ad_id;
    private String ad_name;
    private int ad_type;
    private int ad_link_value;
    private String ad_link_http;
    private long start_time;
    private long end_time;
    private int click_count;
    private int position_id;
    private String ad_img;
    private int sort;

    public void setAd_id(int ad_id) {
        this.ad_id = ad_id;
    }

    public int getAd_id() {
        return ad_id;
    }

    public void setAd_name(String ad_name) {
        this.ad_name = ad_name;
    }

    public String getAd_name() {
        return ad_name;
    }

    public void setAd_type(int ad_type) {
        this.ad_type = ad_type;
    }

    public int getAd_type() {
        return ad_type;
    }

    public void setAd_link_value(int ad_link_value) {
        this.ad_link_value = ad_link_value;
    }

    public int getAd_link_value() {
        return ad_link_value;
    }

    public void setAd_link_http(String ad_link_http) {
        this.ad_link_http = ad_link_http;
    }

    public String getAd_link_http() {
        return ad_link_http;
    }

    public void setStart_time(long start_time) {
        this.start_time = start_time;
    }

    public long getStart_time() {
        return start_time;
    }

    public void setEnd_time(long end_time) {
        this.end_time = end_time;
    }

    public long getEnd_time() {
        return end_time;
    }

    public void setClick_count(int click_count) {
        this.click_count = click_count;
    }

    public int getClick_count() {
        return click_count;
    }

    public void setPosition_id(int position_id) {
        this.position_id = position_id;
    }

    public int getPosition_id() {
        return position_id;
    }

    public void setAd_img(String ad_img) {
        this.ad_img = ad_img;
    }

    public String getAd_img() {
        return ad_img;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getSort() {
        return sort;
    }

    @Override
    public String toString() {
        return "AdvertisingBean{" +
                "ad_id=" + ad_id +
                ", ad_name='" + ad_name + '\'' +
                ", ad_type=" + ad_type +
                ", ad_link_value=" + ad_link_value +
                ", ad_link_http='" + ad_link_http + '\'' +
                ", start_time=" + start_time +
                ", end_time=" + end_time +
                ", click_count=" + click_count +
                ", position_id=" + position_id +
                ", ad_img='" + ad_img + '\'' +
                ", sort=" + sort +
                '}';
    }
}
