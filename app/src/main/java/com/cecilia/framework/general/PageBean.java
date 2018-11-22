package com.cecilia.framework.general;

import java.util.List;

/**
 * @author stone
 */

public class PageBean<T> {

    private String pageSize;
    private String cuPage;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof PageBean))
            return false;

        PageBean pageBean = (PageBean) o;

        return pageSize != null ? pageSize.equals(pageBean.pageSize) : pageBean.pageSize == null
                && cuPage != null ? cuPage.equals(pageBean.cuPage) : pageBean.cuPage == null;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getCuPage() {
        return cuPage;
    }

    public void setCuPage(String cuPage) {
        this.cuPage = cuPage;
    }

    private int per_page;
    private int current_page;
    private boolean has_more;
    private List next_item;
    private List<T> data;

    public void setPer_page(int per_page) {
        this.per_page = per_page;
    }

    public int getPer_page() {
        return per_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public int getCurrent_page() {
        return current_page;
    }

    public void setHas_more(boolean has_more) {
        this.has_more = has_more;
    }

    public boolean getHas_more() {
        return has_more;
    }

    public void setNext_item(List next_item) {
        this.next_item = next_item;
    }

    public List getNext_item() {
        return next_item;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public List<T> getData() {
        return data;
    }

    @Override
    public String toString() {
        return "PageBean{" + "per_page=" + per_page +
                ", current_page=" + current_page +
                ", has_more=" + has_more +
                ", next_item='" + next_item + '\'' +
                ", data=" + data +
                '}';
    }
}

