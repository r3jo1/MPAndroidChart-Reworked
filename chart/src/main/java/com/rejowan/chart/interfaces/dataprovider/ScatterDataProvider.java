package com.rejowan.chart.interfaces.dataprovider;

import com.rejowan.chart.data.ScatterData;

public interface ScatterDataProvider extends BarLineScatterCandleBubbleDataProvider {

    ScatterData getScatterData();
}
