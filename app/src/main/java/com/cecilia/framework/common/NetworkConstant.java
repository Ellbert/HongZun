package com.cecilia.framework.common;

import com.cecilia.framework.BuildConfig;

import junit.runner.Version;

/**
 * 网络请求URL常量类
 *
 * @author stone
 */

public class NetworkConstant {

    private static final String API = "api/";
    /**
     * 对于请求列表数据时，一次最多加载的数据条数
     */
    public static final int PAGE_SIZE = 10;

    public static final int SUCCESS = 200;

    public static final int SUCCESS_NO = 201;

    private static final String VERSION = "v1/";

    public class Home {
        private static final String USERS_ADDRESS = "UsersAddress/";


    }

    public class Advertising{
        public static final String GET_ADVERTISING = API + VERSION + "ads/";

    }

    public class Product{

        private static final String PRODUCT = API + VERSION + "product/";

        public static final String HOME = PRODUCT + "home/";

        public static final String RECOMMEND = PRODUCT + "recommend_product/paginate/";

        public static final String BY_CATEGORY = PRODUCT + "by_category/";

    }

    public class Category {

        private static final String PRODUCT_CATEGORY = API + VERSION + "product_category/";

        public static final String ALL_CATEGORY = PRODUCT_CATEGORY + "all/";

    }

//    public class UsersAddress {
//        private static final String USERS_ADDRESS = "UsersAddress/";
//
//        public static final String ADDRESS_LIST = API + USERS_ADDRESS + "addressList.html";
//        public static final String SET_IS_DEFAULT = API + USERS_ADDRESS + "setIsDefault.html";
//        public static final String DEL_ADDRESS = API + USERS_ADDRESS + "delAddress.html";
//        public static final String EDIT_ADDRESS = API + USERS_ADDRESS + "editAddress.html";
//    }

//    public class Version {
//        private static final String VERSION = "Version/";
//
//        public static final String VERSION_UPDATE = API + VERSION + "versionUpdate.html";
//    }
//
//    public class Money {
//        private static final String MONEY = "Money/";
//
//        public static final String GET_ACCOUNT_LIST_JK = API + MONEY + "getAccountListJk.html";
//    }
//
//    private static final String WEB_APP = "WebApp/";
//
//    public class Mobile {
//        private static final String MOBILE = "Mobile/";
//
//        public static final String GET_ORDER_LIST_JK = WEB_APP + MOBILE + "getOrderListJk.html";
//        public static final String GET_CART_INFO_JK = WEB_APP + MOBILE + "getCartInfoJk.html";
//        public static final String QUICK_DO_LOGIN = WEB_APP + MOBILE + "quickDoLogin.html";
//        public static final String GET_VERI_CODE = WEB_APP + MOBILE + "getVeriCode.html";
//    }

    //--------------------------------------------------
    //以上的为旧接口
    //--------------------------------------------------


//    public class Personal {
//        private static final String PERSONAL = APPS + "Personal/";
//
//        public static final String ADDRESS_LIST = PERSONAL + "addressList/";
//        public static final String ADD_OR_EDIT_ADDRESS = PERSONAL + "addOrEditAddress/";
//        public static final String SET_DEFAULT_ADDRESS = PERSONAL + "setDefaultAddress/";
//        public static final String DEL_ADDRESS = PERSONAL + "delAddress/";
//        public static final String PERSONAL_INFO = PERSONAL + "personalInfo/";
//        public static final String EDIT_PERSONAL_INFO = PERSONAL + "editPersonalInfo/";
//    }

//    public class Personal2 {
//        private static final String PERSONAL2 = APPS + "Personal2/";
//
//        public static final String GET_COLLECTION_LIST = PERSONAL2 + "getCollectionList/";
//        public static final String COLLECT = PERSONAL2 + "collect/";
//        public static final String DEL_COLLECTION = PERSONAL2 + "delCollection/";
//        public static final String GET_ORDER_DETAIL = PERSONAL2 + "getOrderDetail/";
//        public static final String GET_ORDER_LIST = PERSONAL2 + "getOrderList/";
//        public static final String CANCEL_ORDER = PERSONAL2 + "cancelOrder/";
//        public static final String FEEDBACK = PERSONAL2 + "feedback/";
//        public static final String SEND_VALIDATE_CODE = PERSONAL2 + "send_validate_code/";//获取手机验证码
//        /*我的订单*/
//        public static final String GOODS_COMMENT = PERSONAL2 + "goodsComment/";
//        public static final String LOGISTICS_DET = PERSONAL2 + "logisticsDet/";
//        public static final String RETURNS_DETAIL = PERSONAL2 + "returnsDetail/";
//        public static final String RETURN_PAGE = PERSONAL2 + "returnPage/";
//        public static final String RETURN_ORDER = PERSONAL2 + "returnsOrder/";
//        public static final String SET_PAY_PASSWORD = PERSONAL2 + "setPayPassword/";
//
//        /*我的收益*/
//        public static final String MY_INCOME = PERSONAL2 + "myIncome/";
//        // 我的足迹
//        public static final String MY_FOOTMARK = PERSONAL2 + "myFootMark/";
//        // 清空我的足迹
//        public static final String CLEAR_MY_FOOTMARK = PERSONAL2 + "clearMyFootMark/";
//
//        //登录以及设置
//        public static final String SEND_LOGIN_CODE = PERSONAL2 + "loginSendCode/";
//        public static final String CHECK_CODE = PERSONAL2 + "checkRegisterOrLoginVerify/";
//        public static final String SEND_REGISTER_CODE = PERSONAL2 + "registerSendCode/";
//        public static final String REGISTER_PASSWORD = PERSONAL2 + "setPwd/";
//        public static final String MESSAGE_LOGIN = PERSONAL2 + "checkLoginVerify/";
//        public static final String SEND_FORGOT_PASSWORD_CODE = PERSONAL2 + "forgotPwdGetCode/";
//        public static final String FIND_PASSWORD = PERSONAL2 + "findPwd/";
//        public static final String REBUILD_PASSWORD = PERSONAL2 + "revisePwd/";
//        public static final String REBUILD_PAY_PASSWORD = PERSONAL2 + "revisePayPwd/";
//        public static final String FIND_PAY_PASSWORD = PERSONAL2 + "findPayPwd/";
//
//        //我的卡券
//        public static final String MY_CARD_COUPONlIST = PERSONAL2 + "getCouponList/";
//        public static final String DELECT_COUPON = PERSONAL2 + "delCoupon/";
//        public static final String ADD_COUPON = PERSONAL2 + "addConpouCode/";
//        public static final String CHECK_PAY_PASSWORD = PERSONAL2 + "makePayCheckPwd/";
//
//        //我的收益
//        public static final String GET_VISITE_CODE = PERSONAL2 + "visitCode/";
//        public static final String GET_MY_OFFLINE = PERSONAL2 + "getMyOffLine/";
//        public static final String GET_MY_INCOME_LIST = PERSONAL2 + "getMyIncomeList/";
//    }
//
//    public class Common {
//        private static final String COMMON = APPS + "Common/";
//
//        public static final String LOGIN = COMMON + "login/";
//        public static final String REGISTER = COMMON + "register/";
//        public static final String CHECK_NEW_VERSION = COMMON + "checkNewVersion/";
//
//    }


//    public class HomePage {
//        private static final String HOMEPAGE = APPS + "Homepage/";
//
//        public static final String SHOP_LIST = HOMEPAGE + "shopList/";
//        public static final String ADVERTISE = HOMEPAGE + "advertise/";
//        public static final String GET_HOME_PAGE_CAT = HOMEPAGE + "getHomePageCat/";
//        public static final String GET_CAT2 = HOMEPAGE + "getCat2/";
//        public static final String GUESS_LIKE = HOMEPAGE + "guessLike/";
//        public static final String GET_ALL_CAT2 = HOMEPAGE + "getAllCat2/";
//        public static final String SHOP_INFO = HOMEPAGE + "shopInfo/";
//        public static final String GET_MORE = HOMEPAGE + "getMore/";
//        public static final String GOODS_LIST = HOMEPAGE + "goodsList/";
//        public static final String GET_CAT3 = HOMEPAGE + "getAllCat3/";
//        public static final String GET_ALL_CAT1 = HOMEPAGE + "getAllCat1/";
//        public static final String GROUP_LIST = HOMEPAGE + "getGroupGoodsByPageJk/";
//        // 商铺信息
//        public static final String GET_SHOP_INFO = HOMEPAGE + "getShopInfo/";
//        public static final String GET_SHOP_HOMEPAGE = HOMEPAGE + "getShopHomePage/";
//        public static final String GET_SHOP_ALL_GOODS_LIST = HOMEPAGE + "getShopAllGoodsList/";
//        public static final String GET_SHOP_DISCOUNT = HOMEPAGE + "getShopDiscount/";
//        public static final String GET_SHOP_FULL_CUT = HOMEPAGE + "getShopFullCut/";
//        // 搜索
//        public static final String SEARCH_LIST_CAT = HOMEPAGE + "getAreaGoodsOrShop/";
//        public static final String SEARCH_COMMODITY = HOMEPAGE + "searchGoods/";
//        public static final String SEARCH_SHOP = HOMEPAGE + "searchShop/";
//        public static final String SEARCH_HOT = HOMEPAGE + "hotSearch/";
//        // 购物车
//        public static final String CART_LIST = HOMEPAGE + "cartList/";
//        public static final String CART_NUM = HOMEPAGE + "cartNum/";
//        public static final String DEL_CART = HOMEPAGE + "delCart/";
//        public static final String CART_SPEC = HOMEPAGE + "cartSpec/";
//        public static final String GET_SKU = HOMEPAGE + "getSKU/";
//        // 提交订单
//        public static final String ORDER_INFO = HOMEPAGE + "orderInfo/";
//        // 创建订单
//        public static final String CREATE_ORDER = HOMEPAGE + "createOrder/";
//        // 产品详细信息
//        public static final String GOODS_INFO = HOMEPAGE + "goodsInfo/";
//        // 领取优惠券
//        public static final String GET_COUPON = HOMEPAGE + "getCoupon/";
//        // 商品评价
//        public static final String GOODS_EVALUATION = HOMEPAGE + "goodsEvaluation/";
//        // 添加购物车
//        public static final String ADD_CART = HOMEPAGE + "addCart/";
//        // 取消收藏
//        public static final String CANCEL_ORDER = HOMEPAGE + "cancleOrder/";
//        /*品牌接口*/
//        public static final String BRAND_TYPE = HOMEPAGE + "getCatTag/";
//        public static final String BRAND_BOUTIQUE_LIST = HOMEPAGE + "getBrandSelection/";
//        public static final String BRAND_CHANGE = HOMEPAGE + "change/";
//        public static final String BRAND_ALL_LIST = HOMEPAGE + "getCatBrandList/";
//
//        // 参团
//        public static final String TO_GROUP = HOMEPAGE + "toGroup/";
//        // 开团
//        public static final String GROUP_INFO = HOMEPAGE + "groupInfo/";
//
//        // 获取第三级列表
//        public static final String GET_CATE3 = HOMEPAGE + "getCate3/";
//
//    }
//
//    public class Payments {
//        private static final String PAYMENTS = APPS + "Payments/";
//
//        public static final String PAYMENT = PAYMENTS + "payments/";
//    }
//
//    public class H5 {
//        private static final String MOBILE_ACTIVITY = BuildConfig.BASE_URL + "mobile/activity/";
//
//        // 限时抢购
//        public static final String LIMIT_TIME_SALES = MOBILE_ACTIVITY + "limitTimeSales/";
//        // 限天抢购
//        public static final String LIMIT_DAY_SALES = MOBILE_ACTIVITY + "limitDaySales/";
//        // 满减
//        public static final String FULL_CUT = MOBILE_ACTIVITY + "fullCut/";
//        // 天天特价
//        public static final String SPECIAL_PRICE = MOBILE_ACTIVITY + "specialprice/";
//        // 拼团
//        public static final String TUAN = MOBILE_ACTIVITY + "tuan/";
//        // 套餐
//
//    }
}
