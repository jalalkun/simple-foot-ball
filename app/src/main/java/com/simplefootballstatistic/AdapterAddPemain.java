package com.simplefootballstatistic;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;
import com.simplefootballstatistic.Data.DataTeam;
import com.simplefootballstatistic.Respon.Pemain;
import com.simplefootballstatistic.Utils.Methods;
import com.simplefootballstatistic.Utils.PopUp;
import com.simplefootballstatistic.Views.ViewsAddPemainActivity;

import java.util.List;

public class AdapterAddPemain extends RecyclerView.Adapter<AdapterAddPemain.MyViewHolder> {

    private List<Pemain> pemains;
    private Activity activity;
    private LayoutInflater inflater;
    private ViewsAddPemainActivity viewsAddPemainActivity;

    public AdapterAddPemain(Activity activity, List<Pemain> pemains, ViewsAddPemainActivity viewsAddPemainActivity) {
        this.activity = activity;
        this.pemains = pemains;
        this.viewsAddPemainActivity = viewsAddPemainActivity;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View rootView = inflater.inflate(R.layout.adapter_add_pemain, viewGroup, false);
        return new AdapterAddPemain.MyViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        Pemain pemain = pemains.get(i);
        holder.tv_no.setText(String.valueOf(pemain.getNoPunggung()));
        holder.tv_nama_pemain.setText(pemain.getNamaPemain());

    }

    @Override
    public int getItemCount() {
        return pemains.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_no;
        TextView tv_nama_pemain;
        TextView tv_edit_pemain;
        TextView tv_hapus_pemain;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            final Pemain pemain = pemains.get(getAdapterPosition() + 1);
            tv_no = itemView.findViewById(R.id.tv_no);
            tv_nama_pemain = itemView.findViewById(R.id.tv_nama_pemain);
            tv_edit_pemain = itemView.findViewById(R.id.tv_edit_pemain);
            tv_hapus_pemain = itemView.findViewById(R.id.tv_hapus_pemain);

            tv_hapus_pemain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new PopUp().YesNo(activity, SweetAlertDialog.WARNING_TYPE, "Hapus Pemain", "Yakin ingin menghapus pemain : No " + pemain.getNoPunggung() + " Nama " + pemain.getNamaPemain(), new Methods() {
                        @Override
                        public void function() {
                            viewsAddPemainActivity.startLoading();
                            DataTeam dataTeam = new DataTeam();
                            dataTeam.init(viewsAddPemainActivity);
                            dataTeam.deletePemain(activity, String.valueOf(pemain.getIdPemain()));
                        }
                    });
                }
            });

            tv_edit_pemain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewsAddPemainActivity.dialogEditPemain(pemain);
                }
            });
        }
    }
}