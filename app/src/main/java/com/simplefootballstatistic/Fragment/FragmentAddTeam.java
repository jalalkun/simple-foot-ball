package com.simplefootballstatistic.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;
import com.simplefootballstatistic.ActivityMain;
import com.simplefootballstatistic.Data.DataTeam;
import com.simplefootballstatistic.R;
import com.simplefootballstatistic.Respon.RestAddTeam;
import com.simplefootballstatistic.Utils.PopUp;
import com.simplefootballstatistic.Utils.Session;
import com.simplefootballstatistic.Views.ViewsActivityMain;
import com.simplefootballstatistic.Views.ViewsTambahTimActivity;

import java.util.HashMap;

public class FragmentAddTeam extends Fragment implements View.OnClickListener, ViewsTambahTimActivity {
    ViewsActivityMain viewsActivityMain;
    private View view;
    ActivityMain activityMain;
    EditText et_input_nama_tim;
    EditText et_input_nama_pelatih;
    TextView tv_reset_profil;
    TextView btn_simpan_profil;
    HashMap<String, String> hashMap = new HashMap<>();
    DataTeam dataTeam;
    ProgressDialog progressDialog;
    Session session;
    public FragmentAddTeam(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        activityMain = (ActivityMain) getActivity();
        assert activityMain != null;
        this.viewsActivityMain = activityMain.getViews();
        view = inflater.inflate(R.layout.fragment_add_team, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        session = new Session(getContext());
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Membuat team");
        dataTeam = new DataTeam();
        dataTeam.init(this);
        et_input_nama_tim = view.findViewById(R.id.et_input_nama_tim);
        et_input_nama_pelatih = view.findViewById(R.id.et_input_nama_pelatih);
        tv_reset_profil = view.findViewById(R.id.tv_reset_profil);
        btn_simpan_profil = view.findViewById(R.id.btn_simpan_profil);
        view.findViewById(R.id.ivback).setOnClickListener(this);
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
            case R.id.ivback :
                viewsActivityMain.pindah(0);
                break;
        }
    }
    private void addTeam(){
        progressDialog.show();
        if (et_input_nama_tim.getText().toString().replaceAll(" ", "").length()>0 && et_input_nama_pelatih.getText().toString().replaceAll(" ", "").length()>0) {
            hashMap.put("nama_team", et_input_nama_tim.getText().toString());
            hashMap.put("nama_pelatih", et_input_nama_pelatih.getText().toString());
            dataTeam.createTeam(getActivity(), hashMap);
        }else {
            new PopUp().notifikasi(getContext(), SweetAlertDialog.WARNING_TYPE, "Input Data Dengan Benar", "Periksa kembali nama tim dan nama pelatih");
        }
    }

    @Override
    public void suksesBuatTeam(RestAddTeam restAddTeam) {
        progressDialog.dismiss();
        et_input_nama_pelatih.setText("");
        et_input_nama_tim.setText("");
        session.setSessionInteger("id_team", restAddTeam.getIdTeam());
        viewsActivityMain.pindah(3);
        /*Intent intent = new Intent(getContext(), AddPemainActivity.class);
        intent.putExtra("id_team", restAddTeam.getIdTeam());
        startActivity(intent);*/
    }

    @Override
    public void errorBuatTeam() {
        progressDialog.dismiss();
        new PopUp().notifikasi(getContext(), SweetAlertDialog.ERROR_TYPE, "Gagal membuat tim", "Coba sekali lagi");
    }
}
