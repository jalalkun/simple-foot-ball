package com.simplefootballstatistic;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;
import com.simplefootballstatistic.Data.DataTeam;
import com.simplefootballstatistic.Respon.RestAddTeam;
import com.simplefootballstatistic.Utils.PopUp;
import com.simplefootballstatistic.Views.ViewsTambahTimActivity;

import java.util.HashMap;

public class AddTimActivity extends AppCompatActivity implements View.OnClickListener, ViewsTambahTimActivity {

    EditText et_input_nama_tim;
    EditText et_input_nama_pelatih;
    TextView tv_reset_profil;
    TextView btn_simpan_profil;
    HashMap<String, String> hashMap = new HashMap<>();
    DataTeam dataTeam;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_tim);
        init();
    }

    private void init(){
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Membuat team");
        dataTeam = new DataTeam();
        dataTeam.init(this);
        et_input_nama_tim = findViewById(R.id.et_input_nama_tim);
        et_input_nama_pelatih = findViewById(R.id.et_input_nama_pelatih);
        tv_reset_profil = findViewById(R.id.tv_reset_profil);
        btn_simpan_profil = findViewById(R.id.btn_simpan_profil);

        tv_reset_profil.setOnClickListener(this);
        btn_simpan_profil.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_simpan_profil:
                addTeam();
                break;
            case R.id.tv_reset_profil:
                et_input_nama_pelatih.setText("");
                et_input_nama_tim.setText("");
                break;
        }
    }

    private void addTeam(){
        progressDialog.show();
        if (et_input_nama_tim.getText().toString().replaceAll(" ", "").length()>0 && et_input_nama_pelatih.getText().toString().replaceAll(" ", "").length()>0) {
            hashMap.put("nama_team", et_input_nama_tim.getText().toString());
            hashMap.put("nama_pelatih", et_input_nama_pelatih.getText().toString());
            dataTeam.createTeam(this, hashMap);
        }else {
            new PopUp().notifikasi(this, SweetAlertDialog.WARNING_TYPE, "Input Data Dengan Benar", "Periksa kembali nama tim dan nama pelatih");
        }
    }


    @Override
    public void suksesBuatTeam(RestAddTeam restAddTeam) {
        progressDialog.dismiss();
        et_input_nama_pelatih.setText("");
        et_input_nama_tim.setText("");
        Intent intent = new Intent(this, AddPemainActivity.class);
        intent.putExtra("id_team", restAddTeam.getIdTeam());
        startActivity(intent);
    }

    @Override
    public void errorBuatTeam() {
        progressDialog.dismiss();
        new PopUp().notifikasi(this, SweetAlertDialog.ERROR_TYPE, "Gagal membuat tim", "Coba sekali lagi");
    }
}
