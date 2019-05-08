package com.simplefootballstatistic.Views;

import com.simplefootballstatistic.Respon.Pemain;
import com.simplefootballstatistic.Respon.RestListPemain;

public interface ViewsAddPemainActivity {
    void setDataTeam(RestListPemain restListPemain);

    void suksesAddPemain();

    void errorAddPemain();

    void dialogEditPemain(Pemain pemain);

    void startLoading();

    void errorUpdatePemain();

    void suksesUpdatePemain();
}
