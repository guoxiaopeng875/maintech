package com.xmkj.md.ui.activity;

import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.xmkj.md.R;
import com.xmkj.md.base.BaseActivity;
import com.xmkj.md.config.Constants;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 晴天 on 2018/7/30.
 */

public class H5Detail extends BaseActivity {
    @BindView(R.id.wv_h5)
    WebView webView;
    @BindView(R.id.tv_title_h5)
    TextView mTvTitle;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_h5;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        String url = getIntent().getStringExtra("url");
        String title = getIntent().getStringExtra("title");
        mTvTitle.setText(title);




        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());
        WebSettings settings = webView.getSettings();
        settings.setUseWideViewPort(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setLoadWithOverviewMode(true);
        webView.loadUrl(Constants.PIC_BASE_URL + url);
    }

    @Override
    public void setListener() {

    }


    @OnClick(R.id.ib_back_h5)
    public void onViewClicked() {
        finish();
    }
}
