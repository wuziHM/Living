package com.living.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.living.R;

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

    private boolean hasAxes = true;
    private boolean hasAxesNames = true;
    private boolean hasLines = true;
    private boolean hasPoints = true;
    private ValueShape shape = ValueShape.CIRCLE;
    private boolean isFilled = false;
    private boolean isCubic = false;
    private boolean hasLabelForSelected = false;
    private LineChartView chartView;

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
        resetViewport();

        List<PointValue> values1 = new ArrayList<PointValue>();
        values1.add(new PointValue(0, 30));
        values1.add(new PointValue(1, 34));
        values1.add(new PointValue(2, 33));
        values1.add(new PointValue(3, 38));
        values1.add(new PointValue(4, 29));
        values1.add(new PointValue(5, 35));

        List<PointValue> values2 = new ArrayList<PointValue>();
        values2.add(new PointValue(0, 19));
        values2.add(new PointValue(1, 25));
        values2.add(new PointValue(2, 19));
        values2.add(new PointValue(3, 21));
        values2.add(new PointValue(4, 20));
        values2.add(new PointValue(5, 29));

        //In most cased you can call data model methods in builder-pattern-like manner.
        Line line1 = new Line(values1).setColor(Color.RED);
        line1.setShape(shape);
        line1.setCubic(isCubic);
        line1.setFilled(isFilled);
        line1.setHasLabels(true);
        line1.setLabelsUp(true);
        line1.setHasLabelsOnlyForSelected(hasLabelForSelected);
        line1.setHasLines(hasLines);
        line1.setHasPoints(hasPoints);


        Line line2 = new Line(values2).setColor(Color.BLUE);
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
            data.setAxisXBottom(axisX);
            data.setAxisYLeft(axisY);
        } else {
            data.setAxisXBottom(null);
            data.setAxisYLeft(null);
        }

        data.setBaseValue(Float.NEGATIVE_INFINITY);

        data.setLines(lines);
        chartView.setLineChartData(data);
    }

    private void resetViewport() {
        // Reset viewport height range to (0,100)
        final Viewport v = new Viewport(chartView.getMaximumViewport());
        v.bottom = 15;
        v.top = 50;
        v.left = 0;
        v.right = 5;
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
}
