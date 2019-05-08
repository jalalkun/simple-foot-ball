package com.simplefootballstatistic.Utils;

import com.github.mikephil.charting.formatter.ValueFormatter;

public class XNameFormater extends ValueFormatter {
    @Override
    public String getFormattedValue(float value) {
        String x = "";
        int v = (int) value;
        switch (v) {
            case 8:
                x = "Attack";
                break;
            case 7:
                x = "Goal";
                break;
            case 6:
                x = "Foul";
                break;
            case 5:
                x = "Suspend";
                break;
            case 4:
                x = "Double";
                break;
            case 3:
                x = "Trapling";
                break;
            case 2:
                x = "Kartu Kuning";
                break;
            case 1:
                x = "Kartu Merah";
                break;
        }
        return x;
    }

}
