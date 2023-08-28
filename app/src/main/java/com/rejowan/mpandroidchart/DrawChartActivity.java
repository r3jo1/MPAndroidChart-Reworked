// TODO: Finish and add to main activity list
package com.rejowan.mpandroidchart;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import androidx.core.content.ContextCompat;

import com.rejowan.chart.charts.Chart;
import com.rejowan.chart.charts.LineChart;
import com.rejowan.chart.components.XAxis;
import com.rejowan.chart.components.YAxis;
import com.rejowan.chart.data.DataSet;
import com.rejowan.chart.data.Entry;
import com.rejowan.chart.data.LineData;
import com.rejowan.chart.data.LineDataSet;
import com.rejowan.chart.highlight.Highlight;
import com.rejowan.chart.interfaces.datasets.ILineDataSet;
import com.rejowan.chart.listener.OnChartValueSelectedListener;
import com.rejowan.chart.listener.OnDrawListener;
import com.rejowan.mpandroidchart.notimportant.DemoBase;

import java.util.ArrayList;
import java.util.List;

/**
 * This Activity demonstrates drawing into the Chart with the finger. Both line,
 * bar and scatter charts can be used for drawing.
 *
 * @author Philipp Jahoda
 */
public class DrawChartActivity extends DemoBase implements OnChartValueSelectedListener,
        OnDrawListener {

    private LineChart chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_draw_chart);

        setTitle("DrawChartActivity");

        chart = findViewById(R.id.chart1);

        // listener for selecting and drawing
        chart.setOnChartValueSelectedListener(this);
        chart.setOnDrawListener(this);

        // if disabled, drawn data sets with the finger will not be automatically
        // finished
        // chart.setAutoFinish(true);
        chart.setDrawGridBackground(false);

        // add dummy-data to the chart
        initWithDummyData();

        XAxis xl = chart.getXAxis();
        xl.setTypeface(tfRegular);
        xl.setAvoidFirstLastClipping(true);

        YAxis yl = chart.getAxisLeft();
        yl.setTypeface(tfRegular);

        chart.getLegend().setEnabled(false);

        // chart.setYRange(-40f, 40f, true);
        // call this to reset the changed y-range
        // chart.resetYRange(true);
    }

    private void initWithDummyData() {

        ArrayList<Entry> values = new ArrayList<>();

        // create a dataset and give it a type (0)
        LineDataSet set1 = new LineDataSet(values, "DataSet");
        set1.setLineWidth(3f);
        set1.setCircleRadius(5f);

        // create a data object with the data sets
        LineData data = new LineData(set1);

        chart.setData(data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.draw, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.actionToggleValues) {
            List<ILineDataSet> sets = chart.getData().getDataSets();

            for (ILineDataSet iSet : sets) {
                LineDataSet set = (LineDataSet) iSet;
                set.setDrawValues(!set.isDrawValuesEnabled());
            }

            chart.invalidate();
        } else if (itemId == R.id.actionToggleHighlight) {
            if (chart.getData() != null) {
                chart.getData().setHighlightEnabled(!chart.getData().isHighlightEnabled());
                chart.invalidate();
            }
        } else if (itemId == R.id.actionTogglePinch) {
            if (chart.isPinchZoomEnabled())
                chart.setPinchZoom(false);
            else
                chart.setPinchZoom(true);

            chart.invalidate();
        } else if (itemId == R.id.actionToggleAutoScaleMinMax) {
            chart.setAutoScaleMinMaxEnabled(!chart.isAutoScaleMinMaxEnabled());
            chart.notifyDataSetChanged();
        } else if (itemId == R.id.actionSave) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                saveToGallery();
            } else {
                requestStoragePermission(chart);
            }
        }

        return true;
    }

    @Override
    protected void saveToGallery() {
        saveToGallery(chart, "DrawChartActivity");
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
        Log.i("VAL SELECTED",
                "Value: " + e.getY() + ", xIndex: " + e.getX()
                        + ", DataSet index: " + h.getDataSetIndex());
    }

    @Override
    public void onNothingSelected() {
    }

    /** callback for each new entry drawn with the finger */
    @Override
    public void onEntryAdded(Entry entry) {
        Log.i(Chart.LOG_TAG, entry.toString());
    }

    /** callback when a DataSet has been drawn (when lifting the finger) */
    @Override
    public void onDrawFinished(DataSet<?> dataSet) {
        Log.i(Chart.LOG_TAG, "DataSet drawn. " + dataSet.toSimpleString());

        // prepare the legend again
        chart.getLegendRenderer().computeLegend(chart.getData());
    }

    @Override
    public void onEntryMoved(Entry entry) {
        Log.i(Chart.LOG_TAG, "Point moved " + entry.toString());
    }
}
