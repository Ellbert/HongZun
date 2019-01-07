package com.cecilia.framework.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXTextObject;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class WXShare {

    public static final String APP_ID = "wx5e4476c11d90d3cf";
    public static final String ACTION_SHARE_RESPONSE = "action_wx_share_response";
    public static final String EXTRA_RESULT = "result";

    private final Context context;
    private final IWXAPI api;
    private OnResponseListener listener;
    private ResponseReceiver receiver;

    public WXShare(Context context) {
        api = WXAPIFactory.createWXAPI(context, APP_ID);
        this.context = context;
    }

    public WXShare register() {
        // 微信分享
        api.registerApp(APP_ID);
        receiver = new ResponseReceiver();
        IntentFilter filter = new IntentFilter(ACTION_SHARE_RESPONSE);
        context.registerReceiver(receiver, filter);
        return this;
    }

    public void unregister() {
        try {
            api.unregisterApp();
            context.unregisterReceiver(receiver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public WXShare share(String text) {
        WXTextObject textObj = new WXTextObject();
        textObj.text = text;

        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = textObj;
        //        msg.title = "Will be ignored";
        msg.description = text;

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("text");
        req.message = msg;
        req.scene = SendMessageToWX.Req.WXSceneSession;

        boolean result = api.sendReq(req);
        LogUtil.e("text shared: " + result);
        return this;
    }

    /**
     * 分享网页类型至微信
     *
     * @param context 上下文
     * @param appId   微信的appId
     * @param webUrl  网页的url
     * @param title   网页标题
     * @param content 网页描述
     * @param bitmap  位图
     */
    public void shareWeb(Context context, String appId, String webUrl, String title, String content, Bitmap bitmap) {
        // 通过appId得到IWXAPI这个对象
        IWXAPI wxapi = WXAPIFactory.createWXAPI(context, appId);
        // 检查手机或者模拟器是否安装了微信
        if (!wxapi.isWXAppInstalled()) {
            ToastUtil.newSafelyShow("您还没有安装微信");
            return;
        }

        // 初始化一个WXWebpageObject对象
        WXWebpageObject webpageObject = new WXWebpageObject();
        // 填写网页的url
        webpageObject.webpageUrl = webUrl;

        // 用WXWebpageObject对象初始化一个WXMediaMessage对象
        WXMediaMessage msg = new WXMediaMessage(webpageObject);
        // 填写网页标题、描述、位图
        msg.title = title;
        msg.description = content;
        // 如果没有位图，可以传null，会显示默认的图片
        msg.setThumbImage(bitmap);

        // 构造一个Req
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        // transaction用于唯一标识一个请求（可自定义）
        req.transaction = "webpage";
        // 上文的WXMediaMessage对象
        req.message = msg;
        // SendMessageToWX.Req.WXSceneSession是分享到好友会话
        // SendMessageToWX.Req.WXSceneTimeline是分享到朋友圈
        req.scene = SendMessageToWX.Req.WXSceneSession;

        // 向微信发送请求
        wxapi.sendReq(req);
    }

    //flag用来判断是分享到微信好友还是分享到微信朋友圈，
    //0代表分享到微信好友，1代表分享到朋友圈
    public WXShare shareUrl(int flag, Context context, String url, String title, String description, Bitmap bitmap) {
        //初始化一个WXWebpageObject填写url
        WXWebpageObject webpageObject = new WXWebpageObject();
        webpageObject.webpageUrl = url;
        //用WXWebpageObject对象初始化一个WXMediaMessage，天下标题，描述
        WXMediaMessage msg = new WXMediaMessage(webpageObject);
        msg.title = title;
        msg.description = description;
        //这块需要注意，图片的像素千万不要太大，不然的话会调不起来微信分享，
        //我在做的时候和我们这的UIMM说随便给我一张图，她给了我一张1024*1024的图片
        //当时也不知道什么原因，后来在我的机智之下换了一张像素小一点的图片好了！
        msg.setThumbImage(bitmap);
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());
        req.message = msg;
        req.scene = flag == 0 ? SendMessageToWX.Req.WXSceneSession : SendMessageToWX.Req.WXSceneTimeline;
        api.sendReq(req);
        return this;
    }

    public IWXAPI getApi() {
        return api;
    }

    public void setListener(OnResponseListener listener) {
        this.listener = listener;
    }

    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }

    private class ResponseReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                Response response = intent.getParcelableExtra(EXTRA_RESULT);
                String result;
                if (listener != null) {
                    if (response.errCode == BaseResp.ErrCode.ERR_OK) {
                        listener.onSuccess();
                    } else if (response.errCode == BaseResp.ErrCode.ERR_USER_CANCEL) {
                        listener.onCancel();
                    } else {
                        switch (response.errCode) {
                            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                                result = "发送被拒绝";
                                break;
                            case BaseResp.ErrCode.ERR_UNSUPPORT:
                                result = "不支持错误";
                                break;
                            default:
                                result = "发送返回";
                                break;
                        }
                        listener.onFail(result);
                    }
                }
            } catch (Exception e) {
                LogUtil.e(e.getMessage());
            }

        }
    }

    public static class Response extends BaseResp implements Parcelable {

        public int errCode;
        public String errStr;
        public String transaction;
        public String openId;

        private int type;
        private boolean checkResult;

        public Response(BaseResp baseResp) {
            errCode = baseResp.errCode;
            errStr = baseResp.errStr;
            transaction = baseResp.transaction;
            openId = baseResp.openId;
            type = baseResp.getType();
            checkResult = baseResp.checkArgs();
        }

        @Override
        public int getType() {
            return type;
        }

        @Override
        public boolean checkArgs() {
            return checkResult;
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.errCode);
            dest.writeString(this.errStr);
            dest.writeString(this.transaction);
            dest.writeString(this.openId);
            dest.writeInt(this.type);
            dest.writeByte(this.checkResult ? (byte) 1 : (byte) 0);
        }

        protected Response(Parcel in) {
            this.errCode = in.readInt();
            this.errStr = in.readString();
            this.transaction = in.readString();
            this.openId = in.readString();
            this.type = in.readInt();
            this.checkResult = in.readByte() != 0;
        }

        public static final Parcelable.Creator<Response> CREATOR = new Creator<Response>() {
            @Override
            public Response createFromParcel(Parcel source) {
                return new Response(source);
            }

            @Override
            public Response[] newArray(int size) {
                return new Response[size];
            }
        };
    }

    public interface OnResponseListener {
        void onSuccess();

        void onCancel();

        void onFail(String message);
    }
}
