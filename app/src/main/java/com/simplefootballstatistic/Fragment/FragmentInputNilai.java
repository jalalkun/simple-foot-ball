package com.simplefootballstatistic.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.simplefootballstatistic.ActivityMain;
import com.simplefootballstatistic.AdapterInputNilai;
import com.simplefootballstatistic.Data.DataTeam;
import com.simplefootballstatistic.R;
import com.simplefootballstatistic.Respon.Pemain;
import com.simplefootballstatistic.Respon.Penilaian;
import com.simplefootballstatistic.Respon.RestListPemain;
import com.simplefootballstatistic.Respon.RestPenilaian;
import com.simplefootballstatistic.Utils.LogTest;
import com.simplefootballstatistic.Utils.Session;
import com.simplefootballstatistic.Views.ViewsActivityMain;
import com.simplefootballstatistic.Views.ViewsInputNilaiActivity;

import java.util.ArrayList;
import java.util.List;

public class FragmentInputNilai extends Fragment implements ViewsInputNilaiActivity {
    View view;
    ActivityMain activityMain;
    ViewsActivityMain viewsActivityMain;
    Session session;

    List<Penilaian> penilaian_prepare =  new ArrayList<>();
    DataTeam dataTeam;
    RecyclerView rv_penilaian;
    AdapterInputNilai adapterInputNilai;
    TextView tv_penilaian_nama_team;
    TextView tv_penilaian_nama_pelatih;
    ProgressDialog progressDialog;
    Button btn_result;
    int id_team = 0;
    int upload = 0;

    public FragmentInputNilai(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        activityMain = (ActivityMain) getActivity();
        assert activityMain != null;
        this.viewsActivityMain = activityMain.getViews();
        view = inflater.inflate(R.layout.fragment_input_nilai, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        session = new Session(getContext());
        rv_penilaian = view.findViewById(R.id.rv_penilaian);
        id_team = session.getSessionInteger("id_team", -1);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Update data, mohon tunggu sebentar");
        tv_penilaian_nama_team = view.findViewById(R.id.tv_penilaian_nama_team);
        tv_penilaian_nama_pelatih = view.findViewById(R.id.tv_penilaian_nama_pelatih);

        dataTeam = new DataTeam();
        dataTeam.init(this);
//        dataTeam.getListPemain(this, String.valueOf(id_team));
        dataTeam.getPenilaianTim(getActivity(), String.valueOf(id_team));

        adapterInputNilai = new AdapterInputNilai(getActivity(), this, penilaian_prepare);
        rv_penilaian.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_penilaian.setAdapter(adapterInputNilai);
        adapterInputNilai.notifyDataSetChanged();
        btn_result = view.findViewById(R.id.btn_result);
        btn_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData();
            }
        });
        view.findViewById(R.id.ivback).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewsActivityMain.pindah(4);
            }
        });
    }

    @Override
    public void setGoalPemain(int id_pemain, int n) {
        LogTest.look("setdata pemain id_nilai " + id_pemain + " value " + n);
        for (int i = 0;i < penilaian_prepare.size(); i++){
            if (penilaian_prepare.get(i).getIdNilai()== id_pemain){
                penilaian_prepare.get(i).setGoal(n);
            }
        }
//        changeList();
    }

    @Override
    public void setListPemain(RestListPemain restListPemain) {
        List<Pemain> pemains = restListPemain.getPemains();
        LogTest.look("setListPemain pemains " + new Gson().toJson(pemains));
        for (Pemain pemain : pemains){
            tv_penilaian_nama_pelatih.setText(pemain.getNamaPelatih());
            tv_penilaian_nama_team.setText(pemain.getNamaTeam());
            Penilaian penilaian = new Penilaian();
            penilaian.setNamaPemain(pemain.getNamaPemain());
            penilaian.setNamaTeam(pemain.getNamaTeam());
            penilaian.setNamaPelatih(pemain.getNamaPelatih());
            penilaian.setIdPemain(pemain.getIdPemain());
            penilaian.setIdTeam(pemain.getIdTeam());
            penilaian.setTeamId(pemain.getTeamId());
            penilaian.setNoPunggung(pemain.getNoPunggung());
            penilaian.setGoal(0);
            penilaian.setFoul(0);
            penilaian.setThreeStep(0);
            penilaian.set_double(0);
            penilaian.setKartuKuning(0);
            penilaian.setKartuMerah(0);
            penilaian.setSuspand(0);
            penilaian.setAttack(0);
            LogTest.look("penilaian " + new Gson().toJson(penilaian));
            penilaian_prepare.add(penilaian);
        }
        LogTest.look("setListPemain prepare " + new Gson().toJson(penilaian_prepare));
        changeList();
    }

    @Override
    public void setAttackPemain(int id_pemain, int n) {
        LogTest.look("setdata pemain id_nilai " + id_pemain + " value " + n);
        for (int i = 0;i < penilaian_prepare.size(); i++){
            if (penilaian_prepare.get(i).getIdNilai()== id_pemain){
                penilaian_prepare.get(i).setAttack(n);
            }
        }
//        changeList();
    }

    @Override
    public void setSuspendPemain(int id_pemain, int n) {
        LogTest.look("setdata pemain id_nilai " + id_pemain + " value " + n);
        for (int i = 0;i < penilaian_prepare.size(); i++){
            if (penilaian_prepare.get(i).getIdNilai()== id_pemain){
                penilaian_prepare.get(i).setSuspand(n);
            }
        }
//        changeList();
    }

    @Override
    public void setMerahPemain(int id_pemain, int n) {
        LogTest.look("setdata pemain id_nilai " + id_pemain + " value " + n);
        for (int i = 0;i < penilaian_prepare.size(); i++){
            if (penilaian_prepare.get(i).getIdNilai()== id_pemain){
                penilaian_prepare.get(i).setKartuMerah(n);
            }
        }
//        changeList();
    }

    @Override
    public void setKuningPemain(int id_pemain, int n) {
        LogTest.look("setdata pemain id_nilai " + id_pemain + " value " + n);
        for (int i = 0;i < penilaian_prepare.size(); i++){
            if (penilaian_prepare.get(i).getIdNilai()== id_pemain){
                penilaian_prepare.get(i).setKartuKuning(n);
            }
        }
//        changeList();
    }

    @Override
    public void setDoublePemain(int id_pemain, int n) {
        LogTest.look("setdata pemain id_nilai " + id_pemain + " value " + n);
        for (int i = 0;i < penilaian_prepare.size(); i++){
            if (penilaian_prepare.get(i).getIdNilai()== id_pemain){
                penilaian_prepare.get(i).set_double(n);
            }
        }
//        changeList();
    }

    @Override
    public void setThreeStepPemain(int id_pemain, int n) {
        LogTest.look("setdata pemain id_nilai " + id_pemain + " value " + n);
        for (int i = 0;i < penilaian_prepare.size(); i++){
            if (penilaian_prepare.get(i).getIdNilai()== id_pemain){
                penilaian_prepare.get(i).setThreeStep(n);
            }
        }
//        changeList();
    }

    @Override
    public void setFoulPemain(int id_pemain, int n) {
        LogTest.look("setdata pemain id_nilai " + id_pemain + " value " + n);
        for (int i = 0;i < penilaian_prepare.size(); i++){
            if (penilaian_prepare.get(i).getIdNilai()== id_pemain){
                penilaian_prepare.get(i).setFoul(n);
            }
        }
//        changeList();
    }

    @Override
    public void setSuksesUpdate() {
        upload++;
        sendData();
    }

    @Override
    public void errorUpdate() {

    }

    @Override
    public void setInputNilai(RestPenilaian restPenilaian) {
        LogTest.look("setInputNilai rest ");
        List<Penilaian> penilaian_hash = restPenilaian.getPenilaians();
        for (Penilaian penilaianH : penilaian_hash){
            tv_penilaian_nama_pelatih.setText(penilaianH.getNamaPelatih());
            tv_penilaian_nama_team.setText(penilaianH.getNamaTeam());
        }
        penilaian_prepare = penilaian_hash;
        LogTest.look("setListPemain prepare " + new Gson().toJson(penilaian_prepare));
        changeList();
    }

    private void changeList(){
        LogTest.look("changeList prepare " + new Gson().toJson(penilaian_prepare));
        adapterInputNilai = new AdapterInputNilai(getActivity(), this, penilaian_prepare);
        rv_penilaian.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_penilaian.setAdapter(adapterInputNilai);
        adapterInputNilai.notifyDataSetChanged();
    }

    private void sendData(){
        if (upload==0)progressDialog.show();
        if (upload<penilaian_prepare.size()) {
            Penilaian penilaian = penilaian_prepare.get(upload);
            dataTeam.updateNilai(getActivity(), String.valueOf(penilaian.getIdNilai()), penilaian);
        }else {
            progressDialog.dismiss();
            session.setSessionInteger("id_team", id_team);
            viewsActivityMain.pindah(4);
        }
    }
}
