package com.simplefootballstatistic;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.simplefootballstatistic.Data.DataTeam;
import com.simplefootballstatistic.Respon.Pemain;
import com.simplefootballstatistic.Respon.Penilaian;
import com.simplefootballstatistic.Respon.RestListPemain;
import com.simplefootballstatistic.Respon.RestPenilaian;
import com.simplefootballstatistic.Utils.LogTest;
import com.simplefootballstatistic.Views.ViewsInputNilaiActivity;

import java.util.ArrayList;
import java.util.List;

public class InputNilaiActivity extends AppCompatActivity implements ViewsInputNilaiActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_nilai);
        init();
    }

    private void init(){
        rv_penilaian = findViewById(R.id.rv_penilaian);
        id_team = getIntent().getIntExtra("id_team", 0);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Update data, mohon tunggu sebentar");
        tv_penilaian_nama_team = findViewById(R.id.tv_penilaian_nama_team);
        tv_penilaian_nama_pelatih = findViewById(R.id.tv_penilaian_nama_pelatih);

        dataTeam = new DataTeam();
        dataTeam.init(this);
//        dataTeam.getListPemain(this, String.valueOf(id_team));
        dataTeam.getPenilaianTim(this, String.valueOf(id_team));

        adapterInputNilai = new AdapterInputNilai(this, this, penilaian_prepare);
        rv_penilaian.setLayoutManager(new LinearLayoutManager(this));
        rv_penilaian.setAdapter(adapterInputNilai);
        adapterInputNilai.notifyDataSetChanged();
        btn_result = findViewById(R.id.btn_result);
        btn_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData();
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
        changeList();
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
    public void setInputNilai(RestPenilaian restPenilaian) {
        LogTest.look("setInputNilai rest ");
        List<Penilaian> penilaian_hash = restPenilaian.getPenilaians();
        for (Penilaian penilaianH : penilaian_hash){
            tv_penilaian_nama_pelatih.setText(penilaianH.getNamaPelatih());
            tv_penilaian_nama_team.setText(penilaianH.getNamaTeam());
//            Penilaian penilaian = new Penilaian();
//            penilaian.setNamaPemain(penilaian.getNamaPemain());
//            penilaian.setNamaTeam(penilaian.getNamaTeam());
//            penilaian.setNamaPelatih(penilaianH.getNamaPelatih());
//            penilaian.setIdNilai(penilaianH.getIdNilai());
//            penilaian.setIdPemain(penilaianH.getIdPemain());
//            penilaian.setIdTeam(penilaianH.getIdTeam());
//            penilaian.setTeamId(penilaianH.getTeamId());
//            penilaian.setNoPunggung(penilaianH.getNoPunggung());
//            penilaian.setGoal(0);
//            penilaian.setFoul(0);
//            penilaian.setThreeStep(0);
//            penilaian.set_double(0);
//            penilaian.setKartuKuning(0);
//            penilaian.setKartuMerah(0);
//            penilaian.setSuspand(0);
//            penilaian.setAttack(0);
//            LogTest.look("penilaian " + new Gson().toJson(penilaian));
//            penilaian_prepare.add(penilaian);
        }
        penilaian_prepare = penilaian_hash;
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
        changeList();
    }

    @Override
    public void setSuspendPemain(int id_pemain, int n) {
        LogTest.look("setdata pemain id_nilai " + id_pemain + " value " + n);
        for (int i = 0;i < penilaian_prepare.size(); i++){
            if (penilaian_prepare.get(i).getIdNilai()== id_pemain){
                penilaian_prepare.get(i).setSuspand(n);
            }
        }
        changeList();
    }

    @Override
    public void setMerahPemain(int id_pemain, int n) {
        LogTest.look("setdata pemain id_nilai " + id_pemain + " value " + n);
        for (int i = 0;i < penilaian_prepare.size(); i++){
            if (penilaian_prepare.get(i).getIdNilai()== id_pemain){
                penilaian_prepare.get(i).setKartuMerah(n);
            }
        }
        changeList();
    }

    @Override
    public void setKuningPemain(int id_pemain, int n) {
        LogTest.look("setdata pemain id_nilai " + id_pemain + " value " + n);
        for (int i = 0;i < penilaian_prepare.size(); i++){
            if (penilaian_prepare.get(i).getIdNilai()== id_pemain){
                penilaian_prepare.get(i).setKartuKuning(n);
            }
        }
        changeList();
    }

    @Override
    public void setDoublePemain(int id_pemain, int n) {
        LogTest.look("setdata pemain id_nilai " + id_pemain + " value " + n);
        for (int i = 0;i < penilaian_prepare.size(); i++){
            if (penilaian_prepare.get(i).getIdNilai()== id_pemain){
                penilaian_prepare.get(i).set_double(n);
            }
        }
        changeList();
    }

    @Override
    public void setThreeStepPemain(int id_pemain, int n) {
        LogTest.look("setdata pemain id_nilai " + id_pemain + " value " + n);
        for (int i = 0;i < penilaian_prepare.size(); i++){
            if (penilaian_prepare.get(i).getIdNilai()== id_pemain){
                penilaian_prepare.get(i).setThreeStep(n);
            }
        }
        changeList();
    }

    @Override
    public void setFoulPemain(int id_pemain, int n) {
        LogTest.look("setdata pemain id_nilai " + id_pemain + " value " + n);
        for (int i = 0;i < penilaian_prepare.size(); i++){
            if (penilaian_prepare.get(i).getIdNilai()== id_pemain){
                penilaian_prepare.get(i).setFoul(n);
            }
        }
        changeList();
    }

    @Override
    public void setSuksesUpdate() {
        upload++;
        sendData();
    }

    @Override
    public void errorUpdate() {

    }


    private void changeList(){
        LogTest.look("changeList prepare " + new Gson().toJson(penilaian_prepare));
        adapterInputNilai = new AdapterInputNilai(this, this, penilaian_prepare);
        rv_penilaian.setLayoutManager(new LinearLayoutManager(this));
        rv_penilaian.setAdapter(adapterInputNilai);
        adapterInputNilai.notifyDataSetChanged();
    }

    private void sendData(){
        if (upload==0)progressDialog.show();
        if (upload<penilaian_prepare.size()) {
            Penilaian penilaian = penilaian_prepare.get(upload);
            dataTeam.updateNilai(this, String.valueOf(penilaian.getIdNilai()), penilaian);
        }else {
            progressDialog.dismiss();
            Intent intent = new Intent(this, LihatNilaiActivity.class);
            intent.putExtra("id_team", id_team);
            startActivity(intent);
        }
    }
}
