package com.xmkj.md.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xmkj.md.model.MessageEvent;
import com.xmkj.md.utils.EventBusUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;


/**
 * 作者 ：晴天-cqz
 * 时间 ：2018/3/13
 * 地点 ：深圳
 */
public abstract class BaseLazyFragment extends SupportFragment {

    private boolean mIsFirstLoad = true;
    private View mRootView;
    private Unbinder unbinder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRootView = View.inflate(getContext(), getLayoutId(), null);
        unbinder = ButterKnife.bind(this, mRootView);
        if (isRegisterEventBus()) {
            EventBusUtil.register(this);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
//        mRootView = View.inflate(getContext(), getLayoutId(), null);
//        unbinder = ButterKnife.bind(this, mRootView);
//        if (isRegisterEventBus()) {
//            EventBusUtil.register(this);
//        }
        return mRootView;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        if (mIsFirstLoad) {
            mIsFirstLoad = false;
            initView();
            initData();
            setListener();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        if (isRegisterEventBus()) {
            EventBusUtil.unregister(this);
        }
    }

    protected abstract int getLayoutId();


    protected abstract void initView();


    protected abstract void initData();


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

}


