package com.cecilia.framework.general;

/**
 * Bean通用类（data数据为对象）
 *
 * @author stone
 */

public class BaseBean<T> {

    private int status;
    private String message;
    private PageBean page;
    private T data;

    @Override
    public String toString() {
        return "BaseBean{" +
                "result=" + status +
                ", msg='" + message + '\'' +
                ", page=" + page +
                ", data=" + data +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof BaseBean))
            return false;

        BaseBean<?> baseBean = (BaseBean<?>) o;

        return status == baseBean.status
                && message != null ? message.equals(baseBean.message) : baseBean.message == null
                && page != null ? page.equals(baseBean.page) : baseBean.page == null
                && data != null ? data.equals(baseBean.data) : baseBean.data == null;
    }

    public int getResult() {
        return status;
    }

    public void setResult(int result) {
        this.status = result;
    }

    public String getMsg() {
        return message;
    }

    public void setMsg(String msg) {
        this.message = msg;
    }

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
        this.page = page;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
