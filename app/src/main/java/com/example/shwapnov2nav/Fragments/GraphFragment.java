package com.example.shwapnov2nav.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shwapnov2nav.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

public class GraphFragment extends Fragment {

    private LineChart lineChart;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View graphView = inflater.inflate(R.layout.fragment_graph, null);
        return graphView;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        lineChart =(LineChart) view.findViewById(R.id.lineChart);

//        lineChart.setOnChartGestureListener(MainActivity.this);
//        lineChart.setOnChartValueSelectedListener(MainActivity.this);
        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(true);

        LineDataSet lineDataSet = new LineDataSet(yValues(),"Temperature");
        lineDataSet.setFillAlpha(110);
        lineDataSet.setColor(Color.RED);
//        lineDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet);

        LineData lineData = new LineData(dataSets);
        lineChart.setData(lineData);
        lineChart.invalidate(); //to refresh the chart with new data

    }

    private ArrayList<Entry> yValues() {
        ArrayList<Entry> yValues = new ArrayList<>();
        yValues.add(new Entry(0, 3));
        yValues.add(new Entry(1, 5));
        yValues.add(new Entry(2, 10));
        yValues.add(new Entry(3, 18));
        yValues.add(new Entry(4, 23));
        yValues.add(new Entry(5, 24));
        yValues.add(new Entry(6, 21));
        yValues.add(new Entry(7, 17));
        yValues.add(new Entry(8, 19));
        yValues.add(new Entry(9, 15));

        return yValues;
    }
}
