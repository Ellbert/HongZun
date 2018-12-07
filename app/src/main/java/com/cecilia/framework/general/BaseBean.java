package com.cecilia.framework.general;

/**
 * Bean通用类（data数据为对象）
 *
 * @author stone
 */

public class BaseBean<T> {

    private int code;
    private String info;
    private String other;
    private T data;

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getOther() {
        return other;
    }

    public void setData(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    @Override
    public String toString() {
        return "BaseBean{" +
                "code=" + code +
                ", info='" + info + '\'' +
                ", other='" + other + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
