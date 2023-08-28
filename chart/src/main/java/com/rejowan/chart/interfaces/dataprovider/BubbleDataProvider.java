package com.rejowan.chart.interfaces.dataprovider;

import com.rejowan.chart.data.BubbleData;

public interface BubbleDataProvider extends BarLineScatterCandleBubbleDataProvider {

    BubbleData getBubbleData();
}
