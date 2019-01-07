package com.cecilia.framework.wxapi;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.cecilia.framework.R;
import com.cecilia.framework.utils.LogUtil;
import com.cecilia.framework.utils.WXShare;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

public class WXEntryActivity extends AppCompatActivity implements IWXAPIEventHandler {

    private IWXAPI api;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
//            setContentView(R.layout.activity_about);
            WXShare share = new WXShare(this);
            api = share
                    //                .register()
                    .getApi();
            //注意：
            //第三方开发者如果使用透明界面来实现WXEntryActivity，需要判断handleIntent的返回值，如果返回值为false，则说明入参不合法未被SDK处理，应finish当前透明界面，避免外部通过传递非法参数的Intent导致停留在透明界面，引起用户的疑惑
            if (!api.handleIntent(getIntent(), this)) {
                finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        try {
            setIntent(intent);
            if (!api.handleIntent(intent, this)) {
                finish();
            }
        } catch (Exception e) {
            LogUtil.e(e.getMessage());
        }

    }

    @Override
    public void onReq(BaseReq baseReq) {
        try {

        } catch (Exception e) {
            LogUtil.e(e.getMessage());
        }
    }

    @Override
    public void onResp(BaseResp baseResp) {
        try {
            Intent intent = new Intent(WXShare.ACTION_SHARE_RESPONSE);
            intent.putExtra(WXShare.EXTRA_RESULT, new WXShare.Response(baseResp));
            sendBroadcast(intent);
            finish();
        } catch (Exception e) {
            LogUtil.e(e.getMessage());
        }
    }

}
