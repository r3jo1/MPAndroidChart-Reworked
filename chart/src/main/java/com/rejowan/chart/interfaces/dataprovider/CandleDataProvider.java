package com.rejowan.chart.interfaces.dataprovider;

import com.rejowan.chart.data.CandleData;

public interface CandleDataProvider extends BarLineScatterCandleBubbleDataProvider {

    CandleData getCandleData();
}
