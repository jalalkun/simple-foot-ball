package com.simplefootballstatistic.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;
import com.simplefootballstatistic.ActivityMain;
import com.simplefootballstatistic.AdapterListTeam;
import com.simplefootballstatistic.Data.DataTeam;
import com.simplefootballstatistic.R;
import com.simplefootballstatistic.Respon.Team;
import com.simplefootballstatistic.Utils.LogTest;
import com.simplefootballstatistic.Utils.PopUp;
import com.simplefootballstatistic.Views.ViewsActivityMain;
import com.simplefootballstatistic.Views.ViewsListTeamActivity;

import java.util.ArrayList;
import java.util.List;

public class FragmentListTeam extends Fragment implements ViewsListTeamActivity {
    ViewsActivityMain viewsActivityMain;
    private View view;
    List<Team> teamList = new ArrayList<>();
    AdapterListTeam adapterListTeam;
    RecyclerView rv_team;
    DataTeam dataTeam;
    ActivityMain activityMain;
    public FragmentListTeam(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        activityMain = (ActivityMain) getActivity();
        assert activityMain != null;
        this.viewsActivityMain = activityMain.getViews();
        view = inflater.inflate(R.layout.fragment_list_team, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dataTeam = new DataTeam();
        dataTeam.init(this);
        rv_team = view.findViewById(R.id.rv_team);
        adapterListTeam = new AdapterListTeam(activityMain, this, teamList);
        rv_team.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_team.setAdapter(adapterListTeam);
        adapterListTeam.notifyDataSetChanged();
        view.findViewById(R.id.ivback).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewsActivityMain.pindah(0);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        dataTeam.getListTeam(getActivity());
    }

    @Override
    public void errorDataListteam() {
        new PopUp().notifikasi(getContext(), SweetAlertDialog.ERROR_TYPE, "Terjadi Kesalahan", "Gagal mendapatkan list tim, coba sekali lagi");

    }

    @Override
    public void dataListTeam(List<Team> teamList) {
        this.teamList = teamList;
        LogTest.look("dataListTeam " + new Gson().toJson(teamList));
        adapterListTeam = new AdapterListTeam(activityMain, this, teamList);
        rv_team.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_team.setAdapter(adapterListTeam);
        adapterListTeam.notifyDataSetChanged();
    }

    @Override
    public void bukaTeam(){
        viewsActivityMain.pindah(3);
    }

    @Override
    public void refresh(){
        dataTeam.getListTeam(getActivity());
    }
}
