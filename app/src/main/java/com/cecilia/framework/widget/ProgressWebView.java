package com.cecilia.framework.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.cecilia.framework.R;


/**
 * Created by Administrator on 2016/10/31.
 */
public class ProgressWebView extends WebView {
    private ProgressBar progressbar;

    public ProgressWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        progressbar = new ProgressBar(context, null,
                android.R.attr.progressBarStyleHorizontal);
        progressbar.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                3, 0, 0));

        Drawable drawable = context.getResources().getDrawable(R.drawable.bg_progress_service);
        progressbar.setProgressDrawable(drawable);
        addView(progressbar);

        setWebChromeClient(new WebChromeClient());

        //是否可以缩放
        getSettings().setSupportZoom(false);
        getSettings().setBuiltInZoomControls(false);
    }

    public class WebChromeClient extends android.webkit.WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress == 100) {
                progressbar.setVisibility(GONE);
                Log.e("newProgress", "" + newProgress);
            } else {
                Log.e("newProgress",""+newProgress);
                if (progressbar.getVisibility() == GONE)
                    progressbar.setVisibility(VISIBLE);
                progressbar.setProgress(newProgress);
            }
            super.onProgressChanged(view, newProgress);
        }

    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        LayoutParams lp = (LayoutParams) progressbar.getLayoutParams();
        lp.x = l;
        lp.y = t;
        progressbar.setLayoutParams(lp);
        super.onScrollChanged(l, t, oldl, oldt);
    }
}
