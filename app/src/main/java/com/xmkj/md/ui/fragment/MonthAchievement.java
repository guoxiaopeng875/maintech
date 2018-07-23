package com.xmkj.md.ui.fragment;

import android.content.Context;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.MPPointF;
import com.xmkj.md.R;
import com.xmkj.md.base.BaseFragment;
import com.xmkj.md.model.AchievementBean;
import com.xmkj.md.model.MonthlyAchievementBean;
import com.xmkj.md.utils.LineChartUtil;
import com.xmkj.md.utils.MdHttpHelper;
import com.xmkj.md.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 晴天 on 2018/7/5.
 */

public class MonthAchievement extends BaseFragment {
    @BindView(R.id.line_chart_month)
    LineChart mLineChart;
    @BindView(R.id.tv_mark_month)
    TextView mMarkMonth;
    @BindView(R.id.tv_date_month)
    TextView mTvDateMonth;
    @BindView(R.id.tv_count_month)
    TextView mTvCountMonth;
    @BindView(R.id.tv_amount_month)
    TextView mTvAmountMonth;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_month_achievement;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        MdHttpHelper.getMonthAchievement(getContext(), new MdHttpHelper.SuccessCallback<AchievementBean>() {
            @Override
            public void onSuccess(AchievementBean data) {
                String date = StringUtils.startOfMonth() + "-" + StringUtils.endOfMonth();
                mTvDateMonth.setText(date);
                mTvCountMonth.setText(data.wrapCount());
                mTvAmountMonth.setText(data.wrapLoanAmount());
                initChart(data.getData());
            }
        });

    }

    @Override
    public void setListener() {
        mLineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                MonthlyAchievementBean data = (MonthlyAchievementBean) e.getData();
                mMarkMonth.setText(data.getMark());
            }

            @Override
            public void onNothingSelected() {

            }
        });
    }

    private void initChart(List<MonthlyAchievementBean> dataObj) {
        MonthlyAchievementBean todayAchieve = MonthlyAchievementBean.getAchievementToday(dataObj);
        if (todayAchieve != null) {
            mMarkMonth.setText(todayAchieve.getMark());
        }
        //设置图表的描述
        Description description = new Description();
        description.setText("");
        mLineChart.setDescription(description);
        LineData lineData = initSingleLineChart(dataObj);
        initDataStyle(mLineChart, lineData);
    }

    private LineData initSingleLineChart(List<MonthlyAchievementBean> dataObj) {
        List<Entry> entries = new ArrayList<>();
        for (MonthlyAchievementBean data : dataObj) {
            // turn your data into Entry objects
//            Logger.d(data.getDay());
            entries.add(new Entry(data.getDay(), data.getLoanAmount(), data));
        }
        return LineChartUtil.initSingleLineChart(entries, this.getContext());
    }

    private void initDataStyle(LineChart lineChart, LineData lineData) {
        int size = 31;
        LineChartUtil.initDataStyle(lineChart, lineData, size, new XValueFormatter(size), getContext());
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
            MonthlyAchievementBean data = (MonthlyAchievementBean) e.getData();
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
        private int size;

        XValueFormatter(int size) {
            this.size = size;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
//            Logger.d(value);
            String curMonth = StringUtils.getCurMonth();
            if (value == this.size) {
                return curMonth + "-31";
            }
            switch ((int) value) {
                case 1:
                    return curMonth + "-1";
                case 15:
                    return curMonth + "-15";
            }
            return "";
        }
    }


}
