package com.cecilia.framework.module.product;


import com.cecilia.framework.general.BaseBean;
import com.cecilia.framework.general.NetworkObserver;
import com.cecilia.framework.general.PageBean;
import com.cecilia.framework.utils.AsynchronousUtil;
import com.cecilia.framework.utils.NetworkUtil;

import static com.cecilia.framework.common.NetworkConstant.PAGE_SIZE;

public class ProductRealization {


//    public static void getProductListData(String id, String brandId, String isHot, int cuPage, NetworkObserver<PageBean<ProductListBean>> observer) {
//        NetworkUtil.getInstance().setApi(ProductApi.class)
//                .getProductListData(id, brandId, isHot, String.valueOf(cuPage), String.valueOf(PAGE_SIZE))
//                .compose(AsynchronousUtil.<BaseBean<PageBean<ProductListBean>>>setThread())
//                .subscribe(observer);
//    }


}
