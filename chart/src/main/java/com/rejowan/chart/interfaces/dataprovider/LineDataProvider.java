package com.rejowan.chart.interfaces.dataprovider;

import com.rejowan.chart.components.YAxis;
import com.rejowan.chart.data.LineData;

public interface LineDataProvider extends BarLineScatterCandleBubbleDataProvider {

    LineData getLineData();

    YAxis getAxis(YAxis.AxisDependency dependency);
}
