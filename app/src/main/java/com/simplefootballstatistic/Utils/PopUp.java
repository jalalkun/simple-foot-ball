package com.simplefootballstatistic.Utils;

import android.content.Context;

import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

public class PopUp {
    public void notifikasi(Context context, int type, String title, String message){
        new SweetAlertDialog(context, type)
                .setTitleText(title)
                .setContentText(message)
                .show();
    }

    public void YesNo(Context context, int type, String title, String description,  final Methods methods) {
        new SweetAlertDialog(context, type)
                .setTitleText(title)
                .setContentText(description)
                .setConfirmText("Ya")
                .setCancelText("Tidak")
                .showCancelButton(true)
                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.cancel();
                    }
                })
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        methods.function();
                        sweetAlertDialog.dismissWithAnimation();
                    }
                })
                .show();
    }
}
