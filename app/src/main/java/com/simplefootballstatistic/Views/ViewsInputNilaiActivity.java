package com.simplefootballstatistic.Views;

import com.simplefootballstatistic.Respon.RestListPemain;
import com.simplefootballstatistic.Respon.RestPenilaian;

public interface ViewsInputNilaiActivity {
    void setGoalPemain(int id_pemain, int i);

    void setListPemain(RestListPemain restListPemain);

    void setAttackPemain(int id_pemain, int n);

    void setSuspendPemain(int id_pemain, int n);

    void setMerahPemain(int id_pemain, int n);

    void setKuningPemain(int id_pemain, int n);

    void setDoublePemain(int id_pemain, int n);

    void setThreeStepPemain(int id_pemain, int n);

    void setFoulPemain(int id_pemain, int n);

    void setSuksesUpdate();

    void errorUpdate();

    void setInputNilai(RestPenilaian restPenilaian);

}
