package com.example.shwapnov2nav.Fragments;

import android.graphics.Color;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shwapnov2nav.API.APIService;
import com.example.shwapnov2nav.API.RetrofitInstance;
import com.example.shwapnov2nav.Model.TempGraphDataSet;
import com.example.shwapnov2nav.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class GraphFragment extends Fragment {
    APIService apiService;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private LineChart lineChart;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        apiService = RetrofitInstance.getRetrofitInstance().create(APIService.class);
        View graphView = inflater.inflate(R.layout.fragment_graph, null);
        return graphView;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        makeNetworkRequestForTemp();
        lineChart =(LineChart) view.findViewById(R.id.lineChart);
        lineChart.setDragEnabled(false);
        lineChart.setScaleEnabled(false);
        lineChart.getDescription().setText("Temperature Plot");
        lineChart.setNoDataText("No data available!");
    }



    private void makeNetworkRequestForTemp() {
        Log.e("Graph","called API");
        Observable<List<TempGraphDataSet>> myObservable = apiService.getTempDataSet()
                                                                    .subscribeOn(Schedulers.io())
                                                                    .observeOn(AndroidSchedulers.mainThread());
        myObservable.subscribe(new Observer<List<TempGraphDataSet>>() {
            @Override
            public void onSubscribe(Disposable d) {
                compositeDisposable.add(d);
            }

            @Override
            public void onNext(List<TempGraphDataSet> tempGraphDataSets) {
                Log.e("onNext"," got data");
                makeArrayListDataSet(tempGraphDataSets);

                for(int i=0; i<tempGraphDataSets.size(); i++){
                    Log.e("i-"+i, tempGraphDataSets.get(i).getTempValue().toString());
                }


            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

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


    private void makeArrayListDataSet(List<TempGraphDataSet> tempGraphDataSets) {
        ArrayList<Entry> yValues = new ArrayList<>();
        ArrayList<String> xValues = new ArrayList<>();

        for(int i=0; i<tempGraphDataSets.size(); i++){
            yValues.add(new Entry(i,tempGraphDataSets.get(i).getTempValue()));
            xValues.add(tempGraphDataSets.get(i).getTimestamp());
        }

        generateGraph(yValues, xValues);
    }

    private void generateGraph(ArrayList<Entry> yValues, final ArrayList<String> xValues) {

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return xValues.get((int)value);
            }
        });

        LineDataSet lineDataSet = new LineDataSet(yValues,"Temperature");
        lineDataSet.setFillAlpha(110);
        lineDataSet.setColor(Color.RED);

//        lineDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);

        ArrayList<ILineDataSet> iLineDataSets = new ArrayList<>();
        iLineDataSets.add(lineDataSet);

        LineData lineData = new LineData(iLineDataSets);
        lineChart.setData(lineData);
        lineChart.invalidate(); //to refresh the chart with new data
    }



}
