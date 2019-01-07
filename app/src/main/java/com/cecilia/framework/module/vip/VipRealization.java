package com.cecilia.framework.module.vip;

import com.cecilia.framework.GcGuangApplication;
import com.cecilia.framework.general.BaseBean;
import com.cecilia.framework.general.NetworkObserver;
import com.cecilia.framework.module.vip.bean.VipBean;
import com.cecilia.framework.module.vip.bean.VipOrderBean;
import com.cecilia.framework.utils.AsynchronousUtil;
import com.cecilia.framework.utils.NetworkUtil;

import java.util.List;

public class VipRealization {

    public static void vipList(NetworkObserver<List<VipBean>> observer) {
        NetworkUtil.getInstance().setApi(VipApi.class)
                .vipList(GcGuangApplication.getsToken())
                .compose(AsynchronousUtil.<BaseBean<List<VipBean>>>setThread())
                .subscribe(observer);
    }

    public static void createOrder(int userId,int cardId,NetworkObserver<VipOrderBean> observer) {
        NetworkUtil.getInstance().setApi(VipApi.class)
                .createOrder(userId,cardId,GcGuangApplication.getsToken())
                .compose(AsynchronousUtil.<BaseBean<VipOrderBean>>setThread())
                .subscribe(observer);
    }

    public static void buy(int userId,int cardId,String subject,NetworkObserver<String> observer) {
        NetworkUtil.getInstance().setApi(VipApi.class)
                .buy(userId,cardId,subject,GcGuangApplication.getsToken())
                .compose(AsynchronousUtil.<BaseBean<String>>setThread())
                .subscribe(observer);
    }
}
