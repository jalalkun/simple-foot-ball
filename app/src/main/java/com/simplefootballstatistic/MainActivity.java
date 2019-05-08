package com.simplefootballstatistic;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;
import com.simplefootballstatistic.Utils.LogTest;
import com.simplefootballstatistic.Utils.Methods;
import com.simplefootballstatistic.Utils.PopUp;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    boolean doubleBackToExitPressedOnce = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        Button btn_register = findViewById(R.id.btn_register);
        Button btn_list_tim = findViewById(R.id.btn_list_tim);

        btn_list_tim.setOnClickListener(this);
        btn_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_register:
                startActivity(new Intent(this, AddTimActivity.class));
                break;
            case R.id.btn_list_tim:
                startActivity(new Intent(this, ListTeamActivity.class));
                break;
        }
    }

    @Override
    public void onBackPressed() {
        new PopUp().YesNo(this, SweetAlertDialog.WARNING_TYPE, "Keluar dari aplikasi", "Yakin untuk keluar dari aplikasi?", new Methods() {
            @Override
            public void function() {
                System.exit(0);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        isStoragePermissionGranted();
    }

    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                LogTest.look("Permission is granted");
                return true;
            } else {

                LogTest.look("Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            LogTest.look("Permission is granted");
            return true;
        }
    }
}
