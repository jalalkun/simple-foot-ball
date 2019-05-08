package com.simplefootballstatistic.Views;

import com.simplefootballstatistic.Respon.Chart;
import com.simplefootballstatistic.Respon.RestPenilaian;

public interface ViewsLihatNilai {
    void setInputNilai(RestPenilaian restPenilaian);

    void onDataChart(Chart chart);
}
