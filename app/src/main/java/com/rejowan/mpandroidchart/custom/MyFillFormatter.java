package com.rejowan.mpandroidchart.custom;

import com.rejowan.chart.formatter.IFillFormatter;
import com.rejowan.chart.interfaces.dataprovider.LineDataProvider;
import com.rejowan.chart.interfaces.datasets.ILineDataSet;

/**
 * Created by Philipp Jahoda on 12/09/15.
 */
@SuppressWarnings("unused")
public class MyFillFormatter implements IFillFormatter
{

    private float fillPos;

    public MyFillFormatter(float fillPos) {
        this.fillPos = fillPos;
    }

    @Override
    public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
        // your logic could be here
        return fillPos;
    }
}
