package com.xmkj.md.utils;

import android.content.Context;
import android.graphics.Color;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

/**
 * Created by 晴天 on 2018/7/5.
 */

public class LineChartUtil {

    public static LineData initSingleLineChart(Context context, String lineName, LineChart mLineChart, int count, float[] datas) {
        ArrayList<String> xValues = new ArrayList<String>();
        for (int i = 0; i < count; i++) {
            // x轴显示的数据，这里默认使用数字下标显示
            xValues.add(String.valueOf(i));
        }
        // y轴的数据
        ArrayList<Entry> yValues = new ArrayList<Entry>();
        for (int i = 0; i < count; i++) {
            yValues.add(new Entry(datas[i], i));
        }
        //设置折线的样式
        LineDataSet dataSet = new LineDataSet(yValues, lineName);
        //用y轴的集合来设置参数
        dataSet.setDrawCircles(true);  //设置有圆点
        dataSet.setLineWidth(1.75f); // 线宽
        dataSet.setCircleSize(2f);// 显示的圆形大小
        dataSet.setColor(Color.rgb(89, 194, 230));// 折线显示颜色
        dataSet.setCircleColor(Color.rgb(89, 194, 230));// 圆形折点的颜色
        dataSet.setHighLightColor(Color.GREEN); // 高亮的线的颜色
        dataSet.setHighlightEnabled(true);
        dataSet.setValueTextColor(Color.rgb(89, 194, 230)); //数值显示的颜色
        dataSet.setValueTextSize(8f);     //数值显示的大小
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(dataSet);
        //构建一个LineData  将dataSets放入
        LineData lineData = new LineData(dataSets);
        return lineData;
    }

    /**
     * @Description:初始化图表的样式
     */
    public static void initDataStyle(LineChart lineChart, LineData lineData, Context context) {
        //设置点击折线点时，显示其数值
//        MyMakerView mv = new MyMakerView(context, R.layout.item_mark_layout);
//        mLineChart.setMarkerView(mv);
        lineChart.setDrawBorders(false); //在折线图上添加边框
        //lineChart.setDescription("时间/数据"); //数据描述
        lineChart.setDrawGridBackground(false); //表格颜色
        lineChart.setGridBackgroundColor(Color.GRAY & 0x70FFFFFF); //表格的颜色，设置一个透明度
        lineChart.setTouchEnabled(true); //可点击
        lineChart.setDragEnabled(true);  //可拖拽
        lineChart.setScaleEnabled(true);  //可缩放
        lineChart.setPinchZoom(false);
        lineChart.setBackgroundColor(Color.WHITE); //设置背景颜色
        lineChart.setData(lineData);
        Legend mLegend = lineChart.getLegend(); //设置标示，就是那个一组y的value的
        mLegend.setForm(Legend.LegendForm.SQUARE); //样式
        mLegend.setFormSize(6f); //字体
        mLegend.setTextColor(Color.GRAY); //颜色
        lineChart.setVisibleXRange(0, 1);   //x轴可显示的坐标范围
        XAxis xAxis = lineChart.getXAxis();  //x轴的标示
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM); //x轴位置
        xAxis.setTextColor(Color.GRAY);    //字体的颜色
        xAxis.setLabelCount(12,true);
        xAxis.setTextSize(10f); //字体大小
        xAxis.setGridColor(Color.GRAY);//网格线颜色
        xAxis.setDrawGridLines(false); //不显示网格线
        YAxis axisLeft = lineChart.getAxisLeft(); //y轴左边标示
        YAxis axisRight = lineChart.getAxisRight(); //y轴右边标示
        axisLeft.setTextColor(Color.GRAY); //字体颜色
        axisLeft.setTextSize(10f); //字体大小
        //axisLeft.setAxisMaxValue(800f); //最大值
        axisLeft.setLabelCount(5, false); //显示格数
        axisLeft.setGridColor(Color.GRAY); //网格线颜色
        axisRight.setDrawAxisLine(false);
        axisRight.setDrawGridLines(false);
        axisRight.setDrawLabels(false);

        //设置动画效果
//        lineChart.animateY(2000, Easing.EasingOption.Linear);
//        lineChart.animateX(2000, Easing.EasingOption.Linear);
        lineChart.invalidate();
        //lineChart.animateX(2500);  //立即执行动画
    }

}
