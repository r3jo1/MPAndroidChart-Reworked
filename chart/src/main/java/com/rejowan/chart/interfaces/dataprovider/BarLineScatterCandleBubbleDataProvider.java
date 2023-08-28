package com.rejowan.chart.interfaces.dataprovider;

import com.rejowan.chart.components.YAxis.AxisDependency;
import com.rejowan.chart.data.BarLineScatterCandleBubbleData;
import com.rejowan.chart.utils.Transformer;

public interface BarLineScatterCandleBubbleDataProvider extends ChartInterface {

    Transformer getTransformer(AxisDependency axis);
    boolean isInverted(AxisDependency axis);
    
    float getLowestVisibleX();
    float getHighestVisibleX();

    BarLineScatterCandleBubbleData getData();
}
