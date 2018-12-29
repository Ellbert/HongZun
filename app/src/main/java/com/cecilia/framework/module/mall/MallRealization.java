package com.cecilia.framework.module.mall;

import com.cecilia.framework.general.BaseBean;
import com.cecilia.framework.general.NetworkObserver;
import com.cecilia.framework.general.PageBean;
import com.cecilia.framework.module.main.bean.GoodsBean;
import com.cecilia.framework.utils.AsynchronousUtil;
import com.cecilia.framework.utils.NetworkUtil;

import java.util.List;

public class MallRealization {

    public static void search(String search, int page, NetworkObserver<PageBean<GoodsBean>> observer) {
        NetworkUtil.getInstance().setApi(MallApi.class)
                .search(search, page)
                .compose(AsynchronousUtil.<BaseBean<PageBean<GoodsBean>>>setThread())
                .subscribe(observer);
    }

    public static void goodsList(int search, int page, NetworkObserver<PageBean<GoodsBean>> observer) {
        NetworkUtil.getInstance().setApi(MallApi.class)
                .goodsList(search, page)
                .compose(AsynchronousUtil.<BaseBean<PageBean<GoodsBean>>>setThread())
                .subscribe(observer);
    }
}
