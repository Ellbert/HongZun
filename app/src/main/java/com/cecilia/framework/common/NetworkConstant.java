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
    /**
     * 对于请求列表数据时，一次最多加载的数据条数
     */
    public static final int PAGE_SIZE = 10;

    public static final int SUCCESS = 0;

    public static final int NO_SUCCESS = -1;

    private static final String VERSION = "v1/";

    public static final String IMAGE_URL = BuildConfig.BASE_URL;

    public class Login {
        public static final String SEND_SMS = API + "/appUser/sendSms";
        public static final String REGISTER = API + "/appUser/register";
        public static final String RETRIEVE = API + "/appUser/retrieve";
        public static final String LOGIN = API + "/appUser/login";
        public static final String UPDATE_PASSWORD = API + "/appUser/updatePwd";
    }

    public class Home {
        public static final String GET_USER_INFO = API + "/appUser/getUserInfo";
        public static final String CHECK_SHOP_STATUS = API + "/merchant/findById";
    }

    public class Me {
        public static final String UPDATE_USER_INFO = API + "/appUser/updateUser";

    }

    public class Cart {
        public static final String ADD_CART = API + "/cart/save";
        public static final String FIND_LIST = API + "/cart/findList";
        public static final String LOST_LIST = API + "/cart/loseList";
        public static final String DELETE_CART = API + "/cart/delete";
        public static final String UPDATE_NUMBER = API + "/cart/updateNum";
        public static final String TEMPORARY_LIST = API + "/cart/temporaryList";
        public static final String CREATE_ORDER_BY_CART = API + "/goodsOrder/createOrderByCart";
    }

    public class Collect {
        public static final String GET_LIST = API + "/collect/list";
        public static final String REMOVE_COLLECT = API + "/collect/removeCollect";
        public static final String REMOVE_PRODUCT_COLLECT = API + "/collect/removeByUser";
        public static final String ADD_COLLECT = API + "/collect/collect";
    }

    public class Address {
        public static final String GET_LIST = API + "/address/list";
        public static final String DELETE = API + "/address/dele";
        public static final String SAVE = API + "/address/save";
    }

    public class Message {
        public static final String FIND_MESSAGE = API + "/userMessage/findMessage";
        public static final String FIND_BY_USER = API + "/userMessage/findByUser";
    }

    public class BankCard {
        public static final String BANK_CARD_LIST = API + "/bankcard/list";
        public static final String SAVE_BANK_CARD = API + "/bankcard/save";
        public static final String DELETE_BANK_CARD = API + "/bankcard/del";
        public static final String BANK_LIST = API + "/bank/list";
        public static final String DEFAULT_BANK_CARD = API + "/bankcard/defaultBank";
    }

    public class Follow {
        public static final String COLLECT_LIST = API + "/merchantCollect/list";
        public static final String REMOVE_FOLLOW = API + "/merchantCollect/removeCollect";
        public static final String ADD_FOLLOW = API + "/merchantCollect/save";
        public static final String REMOVE_FOLLOW_IN_GOODS = API + "/merchantCollect/removeByUser";
    }

    public class Goods {
        public static final String RECOMMEND_LIST = API + "/goods/recommendList";
        public static final String GOODS_DETAIL = API + "/goods/findById";
        public static final String CREATE_ORDER = API + "/goodsOrder/createOrder";
        public static final String COMMENT_LIST = API + "/goodsComment/list";
        public static final String RECENTLY_LIST = API + "/goodsComment/recentlyList";
        public static final String SUBMIT_COMMENT = API + "/goodsComment/comment";
    }

    public class Mall {
        public static final String SEARCH = API + "/goods/search";
        public static final String GOODS_LIST = API + "/goods/list";
    }

    public class Order {
        public static final String BUY = API + "/goodsOrder/buy";
        public static final String ORDER_LIST = API + "/goodsOrder/listByUser";
        public static final String ORDER_DETAIL = API + "/goodsOrder/findById";
        public static final String DELETE_ORDER = API + "/goodsOrder/delete";
        public static final String RECEIVE_ORDER = API + "/goodsOrder/receive";
        public static final String CANCEL_ORDER = API + "/goodsOrder/cancel";
    }

    public class Image {
        public static final String UPLOAD_IMAGE = API + "/img/updateImg";
    }

    public class Shop {
        public static final String ENTER = API + "/merchant/enter";
        public static final String GET_WALLET = API + "/merchant/getWallet";
        public static final String GET_RATIO = API + "/retio/getRatio";
        public static final String WITHDRAW = API + "/merchant/withdrow";
    }

    public class Promotion {
        public static final String LIST = API + "/promotion/list";
        public static final String LAST_NOTICE = API + "/notice/lastOne";
        public static final String NOTICE_LIST = API + "/notice/list";
        public static final String GET_QR_CODE = API + "/appUser/getQrcode";
    }

    public class Friend {
        public static final String SECOND_LIST = API + "/customer/secondList";
        public static final String FIRST_LIST = API + "/customer/firstList";
    }

    public class Vip {
        public static final String VIP_CARD_LIST = API + "/vipCard/list";
        public static final String CREATE_ORDER = API + "/vipCardOrder/createOrder";
        public static final String BUY_VIP = API + "/vipCardOrder/buy";
    }

    public class Payment {
        public static final String USER_PAYMENT_LIST = API + "/bill/userBillList";
        public static final String MERCHANT_PAYMENT_LIST = API + "/bill/merchantBillList";
        public static final String WITHDRAW_RECORD = API + "/merchant/withdrowRecord";
    }

    public class Version {
        public static final String GET_VERSION = API + "/version/getVersion";
    }

}

