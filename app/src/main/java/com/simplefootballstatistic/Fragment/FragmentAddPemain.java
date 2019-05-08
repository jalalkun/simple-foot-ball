package com.simplefootballstatistic.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;
import com.simplefootballstatistic.ActivityMain;
import com.simplefootballstatistic.AdapterAddPemain;
import com.simplefootballstatistic.Data.DataTeam;
import com.simplefootballstatistic.R;
import com.simplefootballstatistic.Respon.Pemain;
import com.simplefootballstatistic.Respon.RestListPemain;
import com.simplefootballstatistic.Utils.PopUp;
import com.simplefootballstatistic.Utils.Session;
import com.simplefootballstatistic.Views.ViewsActivityMain;
import com.simplefootballstatistic.Views.ViewsAddPemainActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FragmentAddPemain extends Fragment implements View.OnClickListener, ViewsAddPemainActivity {
    View view;
    ActivityMain activityMain;
    ViewsActivityMain viewsActivityMain;
    int id_team;
    DataTeam dataTeam;
    List<Pemain> pemains = new ArrayList<>();
    AdapterAddPemain adapterAddPemain;
    RecyclerView rv_add_pemain;
    Button btn_tambah_pemain;
    Button btn_nilai_tim;
    ImageView iv_home;
    Button btn_penilaian_tim;
    AlertDialog.Builder builder;
    AlertDialog alertDialog;
    ProgressDialog progressDialog;
    Session session;
    public FragmentAddPemain(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        activityMain = (ActivityMain) getActivity();
        assert activityMain != null;
        this.viewsActivityMain = activityMain.getViews();
        view = inflater.inflate(R.layout.fragment_add_pemain, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        session = new Session(getContext());
        id_team = session.getSessionInteger("id_team", -1);
        dataTeam = new DataTeam();
        dataTeam.init(this);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Menambahkan pemain");

        rv_add_pemain = view.findViewById(R.id.rv_add_pemain);
        btn_tambah_pemain = view.findViewById(R.id.btn_tambah_pemain);
        iv_home = view.findViewById(R.id.iv_home);
        iv_home.setOnClickListener(this);
        btn_penilaian_tim = view.findViewById(R.id.btn_penilaian_tim);
        btn_nilai_tim = view.findViewById(R.id.btn_nilai_tim);

        view.findViewById(R.id.ivback).setOnClickListener(this);

        btn_nilai_tim.setOnClickListener(this);
        btn_penilaian_tim.setOnClickListener(this);
        btn_tambah_pemain.setOnClickListener(this);
        adapterAddPemain = new AdapterAddPemain(getActivity(), pemains, this);
        rv_add_pemain.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_add_pemain.setAdapter(adapterAddPemain);
        adapterAddPemain.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        dataTeam.getListPemain(getActivity(), String.valueOf(id_team));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_tambah_pemain:
                dialogAddTeam();
                break;
            case R.id.iv_home:
                viewsActivityMain.pindah(0);
                break;
            case R.id.btn_penilaian_tim:
                if (pemains.size()>0) {
                    session.setSessionInteger("id_team", id_team);
                    viewsActivityMain.pindah(5);
                    /*Intent intent = new Intent(getContext(), InputNilaiActivity.class);
                    intent.putExtra("id_team", id_team);
                    startActivity(intent);*/
                }else Toast.makeText(getContext(), "Tim belum memiliki pemain", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_nilai_tim:
                if (pemains.size()>0){
                    session.setSessionInteger("id_team", id_team);
                    /*Intent intent = new Intent(getContext(), LihatNilaiActivity.class);
                    intent.putExtra("id_team", id_team);
                    startActivity(intent);*/
                    viewsActivityMain.pindah(4);
                }else Toast.makeText(getContext(), "Tim belum memiliki pemain", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ivback : viewsActivityMain.pindah(1);
                break;
        }
    }

    @Override
    public void setDataTeam(RestListPemain restListPemain) {
        pemains = restListPemain.getPemains();
        adapterAddPemain = new AdapterAddPemain(getActivity(), pemains, this);
        rv_add_pemain.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_add_pemain.setAdapter(adapterAddPemain);
        adapterAddPemain.notifyDataSetChanged();
    }

    @Override
    public void suksesAddPemain() {
        progressDialog.dismiss();
        if (alertDialog!=null && alertDialog.isShowing())alertDialog.dismiss();
        dataTeam.getListPemain(getActivity(), String.valueOf(id_team));
    }

    @Override
    public void errorAddPemain() {
        progressDialog.dismiss();
        new PopUp().notifikasi(getContext(), SweetAlertDialog.ERROR_TYPE, "Terjadi Kesalahan", "Gagal menambahkan pemain, coba sekali lagi");
    }

    @Override
    public void dialogEditPemain(final Pemain pemain) {
        LayoutInflater inflater = getLayoutInflater();
        builder = new AlertDialog.Builder(getContext());
        View viewDialog = inflater.inflate(R.layout.dialog_tambah_pemain, null);
        builder.setView(viewDialog);
        final EditText no_punggung = viewDialog.findViewById(R.id.et_no_punggung);
        final EditText nama_pemain = viewDialog.findViewById(R.id.et_nama_pemain);
        Button batalkan = viewDialog.findViewById(R.id.btn_batalkan_tambah_pemain);
        final Button tambah_pemain = viewDialog.findViewById(R.id.btn_tambah_pemain);
        alertDialog = builder.create();
        alertDialog.show();
        no_punggung.setText(String.valueOf(pemain.getNoPunggung()));
        nama_pemain.setText(pemain.getNamaPemain());
        batalkan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (alertDialog != null && alertDialog.isShowing()) {
                    alertDialog.dismiss();
                }
            }
        });
        tambah_pemain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String no = no_punggung.getText().toString();
                String nama = nama_pemain.getText().toString();
                if (no.replaceAll(" ", "").length() > 0 && nama.replaceAll(" ", "").length() > 0)
                    editPemain(String.valueOf(pemain.getIdPemain()), no, nama);
                else
                    new PopUp().notifikasi(getActivity(), SweetAlertDialog.WARNING_TYPE, "Cek Data", "Cek kembali data yanbg kamu masukkan");
            }
        });
    }

    @Override
    public void startLoading() {
        progressDialog.show();
    }

    @Override
    public void errorUpdatePemain() {
        progressDialog.dismiss();
        new PopUp().notifikasi(getContext(), SweetAlertDialog.ERROR_TYPE, "Terjadi Kesalahan", "Gagal edit pemain, coba sekali lagi");
    }

    @Override
    public void suksesUpdatePemain() {
        progressDialog.dismiss();
        if (alertDialog!=null && alertDialog.isShowing())alertDialog.dismiss();
        dataTeam.getListPemain(getActivity(), String.valueOf(id_team));
    }

    private void dialogAddTeam(){
        if (pemains.size()<25) {
            LayoutInflater inflater = getLayoutInflater();
            builder = new AlertDialog.Builder(getContext());
            View viewDialog = inflater.inflate(R.layout.dialog_tambah_pemain, null);
            builder.setView(viewDialog);
            final EditText no_punggung = viewDialog.findViewById(R.id.et_no_punggung);
            final EditText nama_pemain = viewDialog.findViewById(R.id.et_nama_pemain);
            Button batalkan = viewDialog.findViewById(R.id.btn_batalkan_tambah_pemain);
            final Button tambah_pemain = viewDialog.findViewById(R.id.btn_tambah_pemain);
            alertDialog = builder.create();
            alertDialog.show();
            batalkan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (alertDialog != null && alertDialog.isShowing()) {
                        alertDialog.dismiss();
                    }
                }
            });
            tambah_pemain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String no = no_punggung.getText().toString();
                    String nama = nama_pemain.getText().toString();
                    if (no.replaceAll(" ", "").length() > 0 && nama.replaceAll(" ", "").length() > 0)
                        tambahPemain(no, nama);
                    else
                        new PopUp().notifikasi(getActivity(), SweetAlertDialog.WARNING_TYPE, "Cek Data", "Cek kembali data yanbg kamu masukkan");
                }
            });
        }else new PopUp().notifikasi(getContext(), SweetAlertDialog.WARNING_TYPE,"Tidak bisa menambah pemain", "Maksimal pemain adalah 25");
    }

    private void tambahPemain(String no, String nama){
        if (pemains.size()<25) {
            progressDialog.show();
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("no_punggung", no);
            hashMap.put("nama_pemain", nama);
            dataTeam.addPemain(getActivity(), String.valueOf(id_team), hashMap);
        }else new PopUp().notifikasi(getContext(), SweetAlertDialog.WARNING_TYPE,"Tidak bisa menambah pemain", "Maksimal pemain adalah 25");
    }

    private void editPemain(String id_pemain, String no, String nama){
        progressDialog.show();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("no_punggung", no);
        hashMap.put("nama_pemain", nama);
        dataTeam.updatePemain(getActivity(), id_pemain, hashMap);
    }
}
