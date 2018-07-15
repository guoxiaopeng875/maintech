package com.xmkj.md.ui.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.MPPointF;
import com.xmkj.md.R;
import com.xmkj.md.base.BaseFragment;
import com.xmkj.md.model.AchievementBean;
import com.xmkj.md.ui.activity.Achievement;
import com.xmkj.md.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 晴天 on 2018/7/5.
 */

public class MonthAchievement extends BaseFragment {
    @BindView(R.id.line_chart_month)
    LineChart mLineChart;
    @BindView(R.id.tv_mark_month)
    TextView mMarkMonth;

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
        mLineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                AchievementBean data = (AchievementBean) e.getData();
                mMarkMonth.setText(data.getMark());
            }

            @Override
            public void onNothingSelected() {

            }
        });
    }

    private void initChart() {
        //设置图表的描述
        Description description = new Description();
        description.setText("");
        mLineChart.setDescription(description);
        List<AchievementBean> dataObj = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            AchievementBean achievementBean = new AchievementBean();
            achievementBean.setMonth(i + 1);
            achievementBean.setLoanAmount((float) Math.random());
            dataObj.add(achievementBean);
        }
        lineData = initSingleLineChart(dataObj);
        initDataStyle(mLineChart, lineData, getContext());
    }

    private LineData initSingleLineChart(List<AchievementBean> dataObj) {
        List<Entry> entries = new ArrayList<>();
        for (AchievementBean data : dataObj) {
            // turn your data into Entry objects
            entries.add(new Entry(data.getMonth(), data.getLoanAmount(), data));
        }
        //设置折线的样式
        LineDataSet dataSet = new LineDataSet(entries, "");
        //用y轴的集合来设置参数
        dataSet.setDrawCircles(true);  //设置有圆点
        dataSet.setLineWidth(3f); // 线宽
        dataSet.setCircleRadius(4f);// 显示的圆形大小
        dataSet.setColor(getResources().getColor(R.color.md_green));// 折线显示颜色
        dataSet.setCircleColor(getResources().getColor(R.color.md_green));// 圆形折点的颜色
        dataSet.setValueTextColor(getResources().getColor(R.color.transparent)); //数值显示的颜色
        dataSet.setValueTextSize(8f);     //数值显示的大小
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(dataSet);
        //构建一个LineData  将dataSets放入
        return new LineData(dataSets);
    }

    private void initDataStyle(LineChart lineChart, LineData lineData, Context context) {
        //设置点击折线点时，显示其数值
//        MyMakerView mv = new MyMakerView(context, R.layout.item_mark_layout);
//        mLineChart.setMarkerView(mv);
        // 隐藏label
        lineChart.setDrawBorders(false); //在折线图上添加边框
        //lineChart.setDescription("时间/数据"); //数据描述
        lineChart.setDrawGridBackground(false); //表格颜色
        lineChart.setGridBackgroundColor(Color.GRAY & 0x70FFFFFF); //表格的颜色，设置一个透明度
        lineChart.setBackgroundColor(Color.WHITE); //设置背景颜色
        // 设置不可缩放
        lineChart.setDoubleTapToZoomEnabled(false);
        lineChart.setPinchZoom(false);
        lineChart.setData(lineData);

        // 隐藏图表下面的label
        lineChart.getLegend().setEnabled(false);

        // 设置x轴
//        lineChart.setVisibleXRange(0, 12);   //x轴可显示的坐标范围
        XAxis xAxis = lineChart.getXAxis();  //x轴的标示
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM); //x轴位置
        xAxis.setTextColor(getResources().getColor(R.color.black54));    //字体的颜色
        xAxis.setLabelCount(12, true);
        xAxis.setTextSize(10f); //字体大小
        xAxis.setGridColor(Color.GRAY);//网格线颜色
        xAxis.setValueFormatter(new XValueFormatter());
//        xAxis.setDrawGridLines(false); //不显示网格线

        // 设置y轴
        YAxis axisLeft = lineChart.getAxisLeft(); //y轴左边标示
        axisLeft.setDrawGridLines(false);
        axisLeft.setDrawLabels(false);// 隐藏y轴左边标识
//        axisLeft.setTextColor(Color.GRAY); //字体颜色
//        axisLeft.setTextSize(10f); //字体大小
        //axisLeft.setAxisMaxValue(800f); //最大值
        axisLeft.setLabelCount(5, false); //显示格数
        axisLeft.setGridColor(R.color.black54); //网格线颜色
        YAxis axisRight = lineChart.getAxisRight(); //y轴右边标示
        axisRight.setDrawAxisLine(false);
        axisRight.setDrawGridLines(false);
        axisRight.setDrawLabels(false);

        lineChart.setPadding(0, 0, 0, 10);
        lineChart.invalidate();
    }

    // 点击圆点显示的view
    class MonthlyMakerView extends MarkerView {

        public MonthlyMakerView(Context context, int layoutResource) {
            super(context, layoutResource);
        }

        /* 每次画 MakerView 时都会触发 Callback 方法，通常会在此方法内更新 View 的內容 */
        @Override
        public void refreshContent(Entry e, Highlight highlight) {
//            tvContent.setText("" + e.getVal());
            AchievementBean data = (AchievementBean) e.getData();
            mMarkMonth.setText(data.getMark());
        }

        /* * offset 是以点到的那个点(0,0) 中心然后向右下角画出来 * 所以如果要显示在点上方 * X=宽度的一半，负数 * Y=高度的负数 */
        @Override
        public MPPointF getOffset() {
            int xOffset = -(getWidth() / 2);
            int yOffset = -getHeight();
            return new MPPointF(xOffset, yOffset);
        }

    }


    // x轴文字格式化
    class XValueFormatter implements IAxisValueFormatter {

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return (int) value + "月";
        }
    }


}
