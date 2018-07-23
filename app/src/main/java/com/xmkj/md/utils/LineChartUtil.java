package com.xmkj.md.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.xmkj.md.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 晴天 on 2018/7/5.
 */

public class LineChartUtil {

    // 初始化折线图
    public static LineData initSingleLineChart(List<Entry> entries, Context ctx) {
        Resources res = ctx.getResources();
        //设置折线的样式
        LineDataSet dataSet = new LineDataSet(entries, "");
        //用y轴的集合来设置参数
        dataSet.setDrawCircles(true);  //设置有圆点
        dataSet.setLineWidth(2f); // 线宽
        dataSet.setCircleRadius(3f);// 显示的圆形大小
        dataSet.setColor(res.getColor(R.color.md_green));// 折线显示颜色
        dataSet.setCircleColor(res.getColor(R.color.md_green));// 圆形折点的颜色
        dataSet.setValueTextColor(res.getColor(R.color.transparent)); //数值显示的颜色
        dataSet.setValueTextSize(8f);     //数值显示的大小
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(dataSet);
        //构建一个LineData  将dataSets放入
        return new LineData(dataSets);
    }

    // 初始化折线图样式
    public static void initDataStyle(LineChart lineChart, LineData lineData, int size, IAxisValueFormatter f, Context ctx) {
        Resources res = ctx.getResources();
        //设置点击折线点时，显示其数值
//        MyMakerView mv = new MyMakerView(context, R.layout.item_mark_layout);
//        mLineChart.setMarkerView(mv);
        // 隐藏label
        lineChart.setDrawBorders(false); //在折线图上添加边框
        //lineChart.setDescription("时间/数据"); //数据描述
        lineChart.setDrawGridBackground(false); //表格颜色
        lineChart.setGridBackgroundColor(Color.GRAY & 0x70FFFFFF); //表格的颜色，设置一个透明度
        lineChart.setBackgroundColor(Color.WHITE); //设置背景颜色

        lineChart.setData(lineData);

        // 隐藏图表下面的label
        lineChart.getLegend().setEnabled(false);

        // 设置x轴
        lineChart.setVisibleXRange(0, size-1);   //x轴可显示的坐标范围
        XAxis xAxis = lineChart.getXAxis();  //x轴的标示
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM); //x轴位置
        xAxis.setTextColor(res.getColor(R.color.black54));    //字体的颜色
        xAxis.setLabelCount(size, false);
        xAxis.setTextSize(10f); //字体大小
        xAxis.setGridColor(Color.GRAY);//网格线颜色
        xAxis.setValueFormatter(f);
        xAxis.setAxisMaximum(size);
//        xAxis.setDrawGridLines(false); //不显示网格线

        // 设置y轴
        YAxis axisLeft = lineChart.getAxisLeft(); //y轴左边标示
        axisLeft.setDrawGridLines(false);
        axisLeft.setDrawLabels(false);// 隐藏y轴左边标识
//        axisLeft.setTextColor(Color.GRAY); //字体颜色
//        axisLeft.setTextSize(10f); //字体大小
        //axisLeft.setAxisMaxValue(800f); //最大值
        axisLeft.setLabelCount(size, false); //显示格数
        axisLeft.setAxisMinimum(0); // y轴起始值
        axisLeft.setGridColor(R.color.black12); //网格线颜色
        YAxis axisRight = lineChart.getAxisRight(); //y轴右边标示
        axisRight.setDrawAxisLine(false);
        axisRight.setDrawGridLines(false);
        axisRight.setDrawLabels(false);

        // 设置不可缩放
        lineChart.setDoubleTapToZoomEnabled(false);
        lineChart.setPinchZoom(false);
        lineChart.invalidate();
    }
}
