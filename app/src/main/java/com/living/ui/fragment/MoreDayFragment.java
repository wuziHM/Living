package com.living.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.TextView;

import com.living.R;
import com.living.bean.CountryWeatherBean;
import com.living.util.LogUtil;
import com.living.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

public class MoreDayFragment extends BaseFragment {

    private boolean hasAxes = false;
    private boolean hasAxesNames = false;
    private boolean hasLines = true;
    private boolean hasPoints = true;
    private ValueShape shape = ValueShape.CIRCLE;
    private boolean isFilled = false;
    private boolean isCubic = false;
    private boolean hasLabelForSelected = false;
    private LineChartView chartView;
    private List<PointValue> highTem;
    private List<PointValue> lowTem;
    private int tMin, tMax;

    public MoreDayFragment() {
    }

    public static Fragment newInstance(String title, int position) {
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putInt("position", position);
        MoreDayFragment fragment = new MoreDayFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initChat();
    }

    private void initChat() {
        chartView = findViewById(R.id.chart);
        chartView.setViewportCalculationEnabled(false);

    }

    private void resetViewport(int tMin, int tMax) {
        // Reset viewport height range to (0,100)
        LogUtil.e("tMin:" + tMin + "  tMax:" + tMax);
        Viewport v = new Viewport(chartView.getMaximumViewport());
        int interval = (tMax - tMin) / 3;
        v.bottom = tMin - interval;
        v.top = tMax + interval;
        v.left = 0;
        v.right = 6;
        chartView.setMaximumViewport(v);
        chartView.setCurrentViewport(v);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_more_day;
    }


    public String getTitle() {
        return getArguments().getString("title");
    }

    public int getPosition() {
        return getArguments().getInt("position");
    }

    public void setWeather(CountryWeatherBean.HeWeatherEntity entity) {

        bandingLine(entity);
        bandingDay(entity);

    }

    private void bandingDay(CountryWeatherBean.HeWeatherEntity entity) {

        ((TextView) findViewById(R.id.tv_date1)).setText(
                StringUtils.getShortDay(entity.getDaily_forecast().get(0).getDate()));

        ((TextView) findViewById(R.id.tv_date2)).setText(
                StringUtils.getShortDay(entity.getDaily_forecast().get(1).getDate()));

        ((TextView) findViewById(R.id.tv_date3)).setText(
                StringUtils.getShortDay(entity.getDaily_forecast().get(2).getDate()));

        ((TextView) findViewById(R.id.tv_date4)).setText(
                StringUtils.getShortDay(entity.getDaily_forecast().get(3).getDate()));

        ((TextView) findViewById(R.id.tv_date5)).setText(
                StringUtils.getShortDay(entity.getDaily_forecast().get(4).getDate()));

        ((TextView) findViewById(R.id.tv_date6)).setText(
                StringUtils.getShortDay(entity.getDaily_forecast().get(5).getDate()));

        ((TextView) findViewById(R.id.tv_date7)).setText(
                StringUtils.getShortDay(entity.getDaily_forecast().get(6).getDate()));


        ((TextView) findViewById(R.id.tv_week1)).setText(
                StringUtils.getWeek(entity.getDaily_forecast().get(0).getDate()));

        ((TextView) findViewById(R.id.tv_week2)).setText(
                StringUtils.getWeek(entity.getDaily_forecast().get(1).getDate()));

        ((TextView) findViewById(R.id.tv_week3)).setText(
                StringUtils.getWeek(entity.getDaily_forecast().get(2).getDate()));

        ((TextView) findViewById(R.id.tv_week4)).setText(
                StringUtils.getWeek(entity.getDaily_forecast().get(3).getDate()));

        ((TextView) findViewById(R.id.tv_week5)).setText(
                StringUtils.getWeek(entity.getDaily_forecast().get(4).getDate()));

        ((TextView) findViewById(R.id.tv_week6)).setText(
                StringUtils.getWeek(entity.getDaily_forecast().get(5).getDate()));

        ((TextView) findViewById(R.id.tv_week7)).setText(
                StringUtils.getWeek(entity.getDaily_forecast().get(6).getDate()));

        ((TextView) findViewById(R.id.tv_type1)).setText(entity.getDaily_forecast().get(0).getCond().getTxt_n());
        ((TextView) findViewById(R.id.tv_type2)).setText(entity.getDaily_forecast().get(1).getCond().getTxt_n());
        ((TextView) findViewById(R.id.tv_type3)).setText(entity.getDaily_forecast().get(2).getCond().getTxt_n());
        ((TextView) findViewById(R.id.tv_type4)).setText(entity.getDaily_forecast().get(3).getCond().getTxt_n());
        ((TextView) findViewById(R.id.tv_type5)).setText(entity.getDaily_forecast().get(4).getCond().getTxt_n());
        ((TextView) findViewById(R.id.tv_type6)).setText(entity.getDaily_forecast().get(5).getCond().getTxt_n());
        ((TextView) findViewById(R.id.tv_type7)).setText(entity.getDaily_forecast().get(6).getCond().getTxt_n());

    }

    private void bandingLine(CountryWeatherBean.HeWeatherEntity entity) {
        highTem = new ArrayList<>();
        lowTem = new ArrayList<>();
        List<CountryWeatherBean.HeWeatherEntity.DailyForecastEntity> dailyForecast = entity.getDaily_forecast();
        int size = dailyForecast.size();
        for (int i = 0; i < size; i++) {
            int min = Integer.parseInt(dailyForecast.get(i).getTmp().getMin());
            int max = Integer.parseInt(dailyForecast.get(i).getTmp().getMax());
            if (i == 0) {
                tMin = min;
                tMax = max;
            } else {
                tMin = min < tMin ? min : tMin;
                tMax = max > tMax ? max : tMax;
            }
            highTem.add(new PointValue(i, max));
            lowTem.add(new PointValue(i, min));
        }

        resetViewport(tMin, tMax);
        Line line1 = new Line(highTem).setColor(Color.RED);
        line1.setShape(shape);
        line1.setCubic(isCubic);
        line1.setFilled(isFilled);
        line1.setHasLabels(true);
        line1.setLabelsUp(true);
        line1.setHasLabelsOnlyForSelected(hasLabelForSelected);
        line1.setHasLines(hasLines);
        line1.setHasPoints(hasPoints);


        Line line2 = new Line(lowTem).setColor(Color.BLUE);
        line2.setShape(shape);
        line2.setCubic(isCubic);
        line2.setFilled(isFilled);
        line2.setHasLabels(true);
        line2.setLabelsUp(false);
        line2.setHasLabelsOnlyForSelected(hasLabelForSelected);
        line2.setHasLines(hasLines);
        line2.setHasPoints(hasPoints);

        List<Line> lines = new ArrayList<Line>();
        lines.add(line1);
        lines.add(line2);

        LineChartData data = new LineChartData();
        if (hasAxes) {
            Axis axisX = new Axis();
            Axis axisY = new Axis().setHasLines(true);
            if (hasAxesNames) {
                axisX.setName("Axis X");
                axisY.setName("Axis Y");
            }
            data.setAxisYLeft(axisY);
        } else {
            data.setAxisXBottom(null);
            data.setAxisYLeft(null);
        }

        data.setBaseValue(Float.NEGATIVE_INFINITY);
        data.setLines(lines);
        chartView.setLineChartData(data);
    }
}
