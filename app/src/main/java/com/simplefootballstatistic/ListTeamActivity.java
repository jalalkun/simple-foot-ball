package com.simplefootballstatistic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;
import com.simplefootballstatistic.Data.DataTeam;
import com.simplefootballstatistic.Respon.Team;
import com.simplefootballstatistic.Utils.LogTest;
import com.simplefootballstatistic.Utils.PopUp;
import com.simplefootballstatistic.Views.ViewsListTeamActivity;

import java.util.ArrayList;
import java.util.List;

public class ListTeamActivity extends AppCompatActivity implements ViewsListTeamActivity {

    List<Team> teamList = new ArrayList<>();
    AdapterListTeam adapterListTeam;
    RecyclerView rv_team;
    DataTeam dataTeam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_team);

        dataTeam = new DataTeam();
        dataTeam.init(this);
        rv_team = findViewById(R.id.rv_team);
        adapterListTeam = new AdapterListTeam(this, this, teamList);
        rv_team.setLayoutManager(new LinearLayoutManager(this));
        rv_team.setAdapter(adapterListTeam);
        adapterListTeam.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        dataTeam.getListTeam(this);
    }

    @Override
    public void errorDataListteam() {
        new PopUp().notifikasi(this, SweetAlertDialog.ERROR_TYPE, "Terjadi Kesalahan", "Gagal mendapatkan list tim, coba sekali lagi");
    }

    @Override
    public void dataListTeam(List<Team> teamList) {
        this.teamList = teamList;
        LogTest.look("dataListTeam " + new Gson().toJson(teamList));
        adapterListTeam = new AdapterListTeam(this, this, teamList);
        rv_team.setLayoutManager(new LinearLayoutManager(this));
        rv_team.setAdapter(adapterListTeam);
        adapterListTeam.notifyDataSetChanged();
    }

    @Override
    public void bukaTeam() {

    }

    @Override
    public void refresh() {

    }
}
