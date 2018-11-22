package com.cecilia.framework.common;

/**
 * @author stone
 */

public class BroadcastConstant {

    /**
     * 广播的意图
     */
    public class Actions {
        public static final String CHANGE_USER_STATE = "change_user_state";
        public static final String CHANGE_BRAND_TYPE = "change_brand_type";
        public static final String REFRESH_ORDER_LIST = "refresh_order_list";
        public static final String UPDATE_ADDRESS = "update_address";
    }

    /**
     * 广播传递时所携带的内容
     */
    public class Extras {
        public static final String LOGIN = "login";
        public static final String LOGIN_IN_OTHER = "login_in_other";
        public static final String LOGOUT = "logout";
        public static final String CANCEL_ORDER = "cancel_order";
        public static final String RETURN_FROM_ORDER_DETAIL = "return_from_order_detail";
        public static final String UPDATE_ADDRESS_DATA = "update_address_data";
    }

}
