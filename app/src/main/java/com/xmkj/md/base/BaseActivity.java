package com.xmkj.md.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.xmkj.md.R;
import com.xmkj.md.model.MessageEvent;
import com.xmkj.md.model.OrderBean;
import com.xmkj.md.utils.AppUtils;
import com.xmkj.md.utils.EventBusUtil;
import com.xmkj.md.utils.SoftInputUtil;
import com.xmkj.md.utils.StatusBarSettingUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * 作者 ：晴天-cqz
 * 时间 ：2018/3/2
 * 地点 ：深圳
 */
public abstract class BaseActivity extends SupportActivity {

    protected final String TAG = this.getClass().getSimpleName() + "===";//日志输出标志
    public Context mGlobalContext;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGlobalContext = getApplicationContext();
        setContentView(getLayoutId());
        StatusBarSettingUtils.setStatusBarColor(this, R.color.toolbar_bg);
        if (isRegisterEventBus()) {
            EventBusUtil.register(this);
        }
        ButterKnife.bind(this);
        initView();
        initData();
        setListener();
    }


    /**
     * 绑定布局文件
     */
    protected abstract int getLayoutId();

    /**
     * 初始化控件
     */
    public abstract void initView();

    /**
     * 初始化数据
     */
    public abstract void initData();

    /**
     * 设置监听
     */
    public abstract void setListener();


    protected boolean isRegisterEventBus() {
        return false;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBusCome(MessageEvent event) {
        if (event != null) {
            receiveEvent(event);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onStickyEventBusCome(MessageEvent event) {
        if (event != null) {
            receiveStickyEvent(event);
        }
    }


    /**
     * 接收到分发到事件
     *
     * @param event 事件
     */
    protected void receiveEvent(MessageEvent event) {

    }

    /**
     * 接受到分发的粘性事件
     *
     * @param event 粘性事件
     */
    protected void receiveStickyEvent(MessageEvent event) {

    }


    @Override
    protected void onRestart() {
        super.onRestart();
        Logger.d(TAG, "onRestart()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Logger.d(TAG, "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Logger.d(TAG, "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy()");
        if (isRegisterEventBus()) {
            EventBusUtil.unregister(this);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                View view = getCurrentFocus();
                SoftInputUtil.hideKeyboard(ev, view, BaseActivity.this);
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    // 完成加载更多
    public void finishLoadMore(BaseQuickAdapter adapter, List data, SmartRefreshLayout refreshLayout) {
        if (adapter == null || data == null || refreshLayout == null) {
            return;
        }
        adapter.addData(data);
        if (data.size() == 0) {
            refreshLayout.finishLoadMoreWithNoMoreData();
            return;
        }
        refreshLayout.finishLoadMore();
    }


}
