package com.xmkj.md.ui.fragment;

import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.orhanobut.logger.Logger;
import com.xmkj.md.R;
import com.xmkj.md.base.BaseFragment;
import com.xmkj.md.model.AchievementBean;
import com.xmkj.md.model.YearlyAchievementBean;
import com.xmkj.md.utils.LineChartUtil;
import com.xmkj.md.utils.MdHttpHelper;
import com.xmkj.md.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 晴天 on 2018/7/5.
 */

public class YearAchievement extends BaseFragment {
    @BindView(R.id.tv_mark_year)
    TextView mTvMarkYear;
    @BindView(R.id.line_chart_year)
    LineChart mLineChart;
    @BindView(R.id.tv_date_year)
    TextView mTvDateYear;
    @BindView(R.id.tv_count_year)
    TextView mTvCountYear;
    @BindView(R.id.tv_amount_year)
    TextView mTvAmountYear;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_year_achievement;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        MdHttpHelper.getYearAchievement(getContext(), new MdHttpHelper.SuccessCallback<AchievementBean>() {
            @Override
            public void onSuccess(AchievementBean data) {
                String date = StringUtils.startOfYear() + "-" + StringUtils.endOfYear();
                mTvDateYear.setText(date);
                mTvCountYear.setText(data.wrapCount());
                mTvAmountYear.setText(data.wrapLoanAmount());
                initChart(data.getData());
            }
        });

    }

    @Override
    public void setListener() {
        mLineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                YearlyAchievementBean data = (YearlyAchievementBean) e.getData();
                mTvMarkYear.setText(data.getMark());
            }

            @Override
            public void onNothingSelected() {

            }
        });
    }

    private void initChart(List<YearlyAchievementBean> dataObj) {
        YearlyAchievementBean todayAchieve = YearlyAchievementBean.getAchievementMonth(dataObj);
        if (todayAchieve != null) {
            mTvMarkYear.setText(todayAchieve.getMark());
        }
        //设置图表的描述
        Description description = new Description();
        description.setText("");
        mLineChart.setDescription(description);
        LineData lineData = initSingleLineChart(dataObj);
        initDataStyle(mLineChart, lineData);
    }

    private LineData initSingleLineChart(List<YearlyAchievementBean> dataObj) {
        List<Entry> entries = new ArrayList<>();
        for (YearlyAchievementBean data : dataObj) {
            // turn your data into Entry objects
//            Logger.d(data.getMonth());
            entries.add(new Entry(data.getMonth(), data.getLoanAmount(), data));
        }
        Logger.d(entries.toString());
        return LineChartUtil.initSingleLineChart(entries, this.getContext());
    }

    private void initDataStyle(LineChart lineChart, LineData lineData) {
        int size = 12;
        LineChartUtil.initDataStyle(lineChart, lineData, size, new XValueFormatter(size), true, getContext());
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
            return (int) value + "月";
        }
    }
}
