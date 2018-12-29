package com.cecilia.framework.common;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.http.SslError;
import android.os.Build;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseActivity;
import com.cecilia.framework.general.EventBean;
import com.cecilia.framework.widget.ProgressWebView;
import com.cecilia.framework.widget.SharePopupWindow;

import java.util.HashMap;
import java.util.Map;


/**
 * @author by Administrator on 2016/5/19.
 */
public class WebActivity extends BaseActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    private ProgressWebView webView;
    private SwipeRefreshLayout mSrlWeb;
    private String title;

    private Dialog dialog;
    private String urlStr = "";

    private SharePopupWindow mSharePopupWindow;
//    private SHARE_MEDIA mShareMedia;
    private static final int SHARE_MSG = 0;

//    boolean isChat = false;//是否是从会话页面点击头像进入个人主页
//    String imId = "";

    public static void launch(Context c, String url, String title) {
        Intent intent = new Intent(c, WebActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("title", title);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        c.startActivity(intent);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_web_page;
    }

    @Override
    protected void initViews() {
        mSharePopupWindow = new SharePopupWindow();

        urlStr = getIntent().getStringExtra("url");
        title = getIntent().getStringExtra("title");
//        String str = "<a href='www.dsf.jsp'>123</a><a href='www.dsf.jsp'>456</a>";
//        Pattern p = Pattern.compile("<a.*?>(.+?)</a>");
//        Matcher m = p.matcher(str);
//        while (m.find()) {
//            LogUtil.e(m.group(1));
//        }
        initMyView();
        setPopupWindow();
        setAwardPopupWindow();
        setPwdPopupWindow();

        webView.getSettings().setJavaScriptEnabled(true);

        String ua = webView.getSettings().getUserAgentString();

        webView.getSettings().setDefaultTextEncodingName("utf-8");
        webView.getSettings().setUserAgentString(ua.replace(ua, "gcguang"));
        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);

        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
    /* 大部分网页需要自己保存一些数据,这个时候就的设置下面这个属性 */
        webView.getSettings().setDomStorageEnabled(true);
    /* 设置为使用webview推荐的窗口 */
        webView.getSettings().setUseWideViewPort(true);
    /* 设置网页自适应屏幕大小 ---这个属性应该是跟上面一个属性一起用 */
        webView.getSettings().setLoadWithOverviewMode(true);
    /* HTML5的地理位置服务,设置为true,启用地理定位 */
        webView.getSettings().setGeolocationEnabled(true);
    /* 设置是否允许webview使用缩放的功能,我这里设为false,不允许 */
        webView.getSettings().setBuiltInZoomControls(false);
    /* 提高网页渲染的优先级 */
        webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
    /* 设置显示水平滚动条,就是网页右边的滚动条.我这里设置的不显示 */
        webView.setHorizontalScrollBarEnabled(false);
    /* 指定垂直滚动条是否有叠加样式 */
        webView.setVerticalScrollbarOverlay(true);
    /* 设置滚动条的样式 */
        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);

        //支持 H5 的 和local storage
        webView.getSettings().setDomStorageEnabled(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        //WebView加载web资源

        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                synCookies(WebActivity.this, url);
                if (url.contains("yr://")) {

                } else if (url.contains("ArticleReview")) {
                    //    加载评论页面

                } else {
//                    synCookies(WebActivity.this, url);
                    view.loadUrl(url);
                }
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }


            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {

                handler.proceed();//接受证书

            }
        });

        webView.setWebChromeClient(webView.new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {

            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress == 100) {
                    mSrlWeb.setRefreshing(false);
                }
            }
        });

        //android添加javascript代码，让H5页面能够调用，第二个参数对应的是H5的
        webView.addJavascriptInterface(new PayJavaScriptInterface(), "AndroidFunction");

        synCookies(WebActivity.this, urlStr);
        Map<String, String> map = new HashMap<String, String>();
//        map.put("Youren-Version", YouRenUtils.VERSION_NAME);
//        map.put("Youren-System", "ANDROID");

//        map.put("CakeCookie[COO_JSID]", ""+ h5Jsid.getData().getJsid());
//        map.put("CakeCookie[COO_userId]", ""+ h5Jsid.getData().getUserId());

//        webView.loadUrl(urlStr, map);
//        urlStr = "http://www.gcguang.com";

        Map<String, String> extraHeaders = new HashMap<String, String>();
        extraHeaders.put("User-Agent", "wapp");
        webView.loadUrl(urlStr, extraHeaders);
//        webView.loadUrl(urlStr);


    }

    @Override
    protected void initData() {
//        mSharePopupWindow.setShareListener(new SharePopupWindow.ShareListener() {
//            @Override
//            public void ShareMedia(SHARE_MEDIA shareMedia) {
//                shareMsg(mShareMedia = shareMedia);
//            }
//        });
    }

    @Override
    public void onRequestPermissionsSucceed(int requestCode) {
        super.onRequestPermissionsSucceed(requestCode);
        if (requestCode == SHARE_MSG) {
            Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.icn_qq_zone);
//            ShareUtil.shareMessage(WebActivity.this, bmp, mShareMedia);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

//    private void shareMsg(SHARE_MEDIA shareMedia) {
//        if (PermissionUtil.checkRequestPermissionInActivity(WebActivity.this, SHARE_MSG, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
//            Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.icn_qq_zone);
//            ShareUtil.shareMessage(WebActivity.this, bmp, shareMedia);
//        }
//    }


    @Override
    protected void initDialog() {

    }

    @Override
    protected boolean isUseEventBus() {
        return false;
    }

    public void initMyView() {
        ImageView mImagBack = (ImageView) findViewById(R.id.mImagBack);
        ImageView ShuaXing = (ImageView) findViewById(R.id.ShuaXing);
        webView = (ProgressWebView) findViewById(R.id.webView);
        ImageView iv_finish_web = (ImageView) findViewById(R.id.iv_finish_web);
        iv_finish_web.setOnClickListener(this);
        TextView titleTV = (TextView) findViewById(R.id.titleTV);
        mSrlWeb = (SwipeRefreshLayout) findViewById(R.id.srl_web);

//        urlStr = "www.baidu.com";
        titleTV.setText(title);
//      titleTV.setText(titleStr);
        mImagBack.setOnClickListener(this);
        ShuaXing.setOnClickListener(this);
        mSrlWeb.setOnRefreshListener(this);
//        sd = new ShareDialog(this);
//        sd.intListener();
    }

    @Override
    protected void doEvents(EventBean event) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mImagBack:
                if (webView.canGoBack()) {
                    webView.goBack();
                } else {
                    finish();
                }
                break;

            case R.id.ShuaXing:
                mSharePopupWindow.initView(this);
                mSharePopupWindow.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
//                webView.reload();
                break;

            case R.id.iv_finish_web:
                finish();
                break;

        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack();
            return true;
        } else
            return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (dialog != null) {
            dialog.cancel();
            dialog = null;
        }
        webView.destroy();


    }

    public void synCookies(Context context, String url) {
//        CookieSyncManager cookieSyncManager = CookieSyncManager.createInstance(context);
//        cookieSyncManager.sync();
        CookieManager cookieManager = CookieManager.getInstance();
//        if (YouRenApplication.getLogin()){
//            if (h5Jsid != null) {
//                cookieManager.setCookie(YouRenUtils.WEB_COOKI, "CakeCookie[COO_JSID]=" + URLEncoder.encode(h5Jsid.getData().getJsid()));
//                cookieManager.setCookie(YouRenUtils.WEB_COOKI, "CakeCookie[COO_UID]=" + URLEncoder.encode(h5Jsid.getData().getUserId()));
//            } else {
//
//            }
//        }else{
//            cookieManager.removeAllCookie();
//        }

        CookieSyncManager.getInstance().sync();
    }

    public void comfimType() {
        //免费或已付费

//        webView.reload();
    }


    public void initListener() {

    }

    /**
     * 获取积分数量以及是否需要输入支付密码
     */
    private void getScore() {

    }

    private void setPopupWindow() {

    }


    private void setAwardPopupWindow() {

    }


    private void DaShang() {

    }

    @Override
    public void onRefresh() {
        webView.reload();

    }

    private void setPwdPopupWindow() {


    }

    public void dismissDialog() {
        if (dialog != null) {
            dialog.dismiss();
        }

    }

    public void payPwd() {

    }

    // 给h5调用的方法
    private class PayJavaScriptInterface {

        @JavascriptInterface
        public void jumpToGoodsDetail(String goodsId, String type) {
//            GoodsDetailActivity.launch(WebActivity.this, goodsId, type);
        }

        @JavascriptInterface
        public void jumpToGroup(String groupUid) {
//            GroupActivity.launch(WebActivity.this, GroupActivity.GroupType.TO_JOIN, groupUid);
        }

        @JavascriptInterface
        public void jumpToShop(String shopId) {
//            HandpickActivity.launch(WebActivity.this, shopId);
        }
    }


}

