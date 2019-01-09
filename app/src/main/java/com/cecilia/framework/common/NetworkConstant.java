package com.cecilia.framework.common;

import com.cecilia.framework.BuildConfig;

import junit.runner.Version;

/**
 * 网络请求URL常量类
 *
 * @author stone
 */

public class NetworkConstant {

    private static final String API = "/HZ";

    private static final String APP = API + "/app";

    public static final String DOWNLOAD_URL = "http://www.hongzuncctv.com/download/hongbao.apk";
    /**
     * 对于请求列表数据时，一次最多加载的数据条数
     */
    public static final int PAGE_SIZE = 10;

    public static final int SUCCESS = 0;

    public static final int OVERDUE = 9999;

    public static final int NO_SUCCESS = -1;

    private static final String VERSION = "v1/";

    public static final String IMAGE_URL = BuildConfig.BASE_URL;

    public class Login {
        public static final String SEND_SMS = APP + "/appUser/sendSms";
        public static final String REGISTER = APP + "/appUser/register";
        public static final String RETRIEVE = APP + "/appUser/retrieve";
        public static final String LOGIN = APP + "/appUser/login";
        public static final String UPDATE_PASSWORD = APP + "/appUser/updatePwd";
    }

    public class Home {
        public static final String GET_USER_INFO = APP + "/appUser/getUserInfo";
        public static final String CHECK_SHOP_STATUS = APP + "/merchant/findById";
    }

    public class Me {
        public static final String UPDATE_USER_INFO = APP + "/appUser/updateUser";
        public static final String SET_PAY_PASSWORD = APP + "/appUser/setPayPassword";
    }

    public class Recharge {
        public static final String CREATE_ORDER = APP + "/recharge/createOrder";
        public static final String RECHARGE_BUY = APP + "/recharge/buy";
    }

    public class HongBao {
        public static final String GET_WALLET = APP + "/appUser/getWallet";
        public static final String RECHARGE_FINANCIAL = APP + "/arrange/recharge";
        public static final String REDELIVERY = APP + "/arrange/redelivery";
        public static final String GET_USER_ARRANGE = APP + "/arrange/getUserArrange";
    }

    public class Cart {
        public static final String ADD_CART = APP + "/cart/save";
        public static final String FIND_LIST = APP + "/cart/findList";
        public static final String LOST_LIST = APP + "/cart/loseList";
        public static final String DELETE_CART = APP + "/cart/delete";
        public static final String UPDATE_NUMBER = APP + "/cart/updateNum";
        public static final String TEMPORARY_LIST = APP + "/cart/temporaryList";
        public static final String CREATE_ORDER_BY_CART = APP + "/goodsOrder/createOrderByCart";
    }

    public class Collect {
        public static final String GET_LIST = APP + "/collect/list";
        public static final String REMOVE_COLLECT = APP + "/collect/removeCollect";
        public static final String REMOVE_PRODUCT_COLLECT = APP + "/collect/removeByUser";
        public static final String ADD_COLLECT = APP + "/collect/collect";
    }

    public class Address {
        public static final String GET_LIST = APP + "/address/list";
        public static final String DELETE = APP + "/address/dele";
        public static final String SAVE = APP + "/address/save";
    }

    public class Message {
        public static final String FIND_MESSAGE = APP + "/userMessage/findMessage";
        public static final String FIND_BY_USER = APP + "/userMessage/findByUser";
        public static final String MESSAGE_COUNT = APP + "/userMessage/countNewMessage";
        public static final String GET_DETAIL = APP + "/userMessage/findById";
    }

    public class BankCard {
        public static final String BANK_CARD_LIST = APP + "/bankcard/list";
        public static final String SAVE_BANK_CARD = APP + "/bankcard/save";
        public static final String DELETE_BANK_CARD = APP + "/bankcard/del";
        public static final String BANK_LIST = APP + "/bank/list";
        public static final String DEFAULT_BANK_CARD = APP + "/bankcard/defaultBank";
    }

    public class Follow {
        public static final String COLLECT_LIST = APP + "/merchantCollect/list";
        public static final String REMOVE_FOLLOW = APP + "/merchantCollect/removeCollect";
        public static final String ADD_FOLLOW = APP + "/merchantCollect/save";
        public static final String REMOVE_FOLLOW_IN_GOODS = APP + "/merchantCollect/removeByUser";
    }

    public class Goods {
        public static final String RECOMMEND_LIST = APP + "/goods/recommendList";
        public static final String GOODS_DETAIL = APP + "/goods/findById";
        public static final String CREATE_ORDER = APP + "/goodsOrder/createOrder";
        public static final String COMMENT_LIST = APP + "/goodsComment/list";
        public static final String RECENTLY_LIST = APP + "/goodsComment/recentlyList";
        public static final String SUBMIT_COMMENT = APP + "/goodsComment/comment";
    }

    public class Mall {
        public static final String SEARCH = APP + "/goods/search";
        public static final String GOODS_LIST = APP + "/goods/list";
    }

    public class Order {
        public static final String BUY = APP + "/goodsOrder/buy";
        public static final String ORDER_LIST = APP + "/goodsOrder/listByUser";
        public static final String ORDER_DETAIL = APP + "/goodsOrder/findById";
        public static final String DELETE_ORDER = APP + "/goodsOrder/delete";
        public static final String RECEIVE_ORDER = APP + "/goodsOrder/receive";
        public static final String CANCEL_ORDER = APP + "/goodsOrder/cancel";
        public static final String BUY_BU_HONG_BAO = APP + "/goodsOrder/buyByBalance";
    }

    public class Image {
        public static final String UPLOAD_IMAGE = API + "/img/updateImg";
    }

    public class Shop {
        public static final String ENTER = APP + "/merchant/enter";
        public static final String GET_WALLET = APP + "/merchant/getWallet";
        public static final String GET_RATIO = APP + "/retio/getRatio";
        public static final String WITHDRAW = APP + "/merchant/withdrow";
    }

    public class Promotion {
        public static final String LIST = APP + "/promotion/list";
        public static final String LAST_NOTICE = APP + "/notice/lastOne";
        public static final String NOTICE_LIST = APP + "/notice/list";
        public static final String GET_QR_CODE = APP + "/appUser/getQrcode";
    }

    public class Friend {
        public static final String SECOND_LIST = APP + "/customer/secondList";
        public static final String FIRST_LIST = APP + "/customer/firstList";
    }

    public class Vip {
        public static final String VIP_CARD_LIST = APP + "/vipCard/list";
        public static final String CREATE_ORDER = APP + "/vipCardOrder/createOrder";
        public static final String BUY_VIP = APP + "/vipCardOrder/buy";
    }

    public class Payment {
        public static final String USER_PAYMENT_LIST = APP + "/bill/userBillList";
        public static final String MERCHANT_PAYMENT_LIST = APP + "/bill/merchantBillList";
        public static final String WITHDRAW_RECORD = APP + "/merchant/withdrowRecord";
    }

    public class Version {
        public static final String GET_VERSION = API + "/version/getVersion";
    }

}

