package com.xmkj.md.ui.fragment;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.LineData;
import com.xmkj.md.R;
import com.xmkj.md.base.BaseFragment;
import com.xmkj.md.utils.LineChartUtil;

import butterknife.BindView;

/**
 * Created by 晴天 on 2018/7/5.
 */

public class MonthAchievement extends BaseFragment {
    @BindView(R.id.line_chart_month)
    LineChart mLineChart;

    private LineData lineData;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_month_achievement;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        initChart();

    }

    @Override
    public void setListener() {

    }

    private void initChart() {
        //设置图表的描述
        Description description = new Description();
        description.setText("666");
        mLineChart.setDescription(description);
        //设置x轴的数据
        int numX = 12;
        //设置y轴的数据
        float[] datas1 = {536, 123, 769, 432, 102, 26, 94, 85, 536, 123, 769, 432};//数据
        lineData = LineChartUtil.initSingleLineChart(getContext(), "666",mLineChart, numX, datas1);
        LineChartUtil.initDataStyle(mLineChart, lineData, getContext());
    }

}
