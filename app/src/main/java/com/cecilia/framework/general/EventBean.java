package com.cecilia.framework.general;

public class EventBean {

    private int type;
    private String msg;

    public EventBean(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
