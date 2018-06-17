package com.xmkj.md.utils;

import com.scwang.smartrefresh.layout.api.RefreshLayout;

public class RefreshUtil {

    public static void finish(RefreshLayout refreshLayout,boolean isRefresh){
        if (refreshLayout == null){
            return;
        }
        if (isRefresh) {
            refreshLayout.finishRefresh();
        } else {
            refreshLayout.finishLoadMore();
        }
    }
}
