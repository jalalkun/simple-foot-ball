package com.simplefootballstatistic;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.simplefootballstatistic.Respon.Penilaian;
import com.simplefootballstatistic.Views.ViewsInputNilaiActivity;

import java.util.List;

public class AdapterInputNilai extends RecyclerView.Adapter<AdapterInputNilai.Holder> {

    List<Penilaian> penilaians;
    Activity activity;
    LayoutInflater inflater;
    ViewsInputNilaiActivity viewsInputNilaiActivity;

    public AdapterInputNilai(Activity activity, ViewsInputNilaiActivity viewsInputNilaiActivity, List<Penilaian> penilaians){
        this.activity = activity;
        this.viewsInputNilaiActivity = viewsInputNilaiActivity;
        this.penilaians = penilaians;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View rootView = inflater.inflate(R.layout.adapter_input_nilai, viewGroup, false);
        return new AdapterInputNilai.Holder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        Penilaian penilaian = penilaians.get(i);
        holder.tv_input_nilai_no.setText(String.valueOf(penilaian.getNoPunggung()));
        holder.tv_input_nilai_g.setText(String.valueOf(penilaian.getGoal()));
        holder.tv_input_nilai_f.setText(String.valueOf(penilaian.getFoul()));
        holder.tv_input_nilai_t.setText(String.valueOf(penilaian.getThreeStep()));
        holder.tv_input_nilai_d.setText(String.valueOf(penilaian.get_double()));
        holder.tv_input_nilai_yellow.setText(String.valueOf(penilaian.getKartuKuning()));
        holder.tv_input_nilai_red.setText(String.valueOf(penilaian.getKartuMerah()));
        holder.tv_input_nilai_z.setText(String.valueOf(penilaian.suspand));
        holder.tv_input_nilai_a.setText(String.valueOf(penilaian.getAttack()));
    }

    @Override
    public int getItemCount() {
        return penilaians.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        TextView tv_input_nilai_no;
        TextView tv_input_nilai_g;
        TextView tv_input_nilai_f;
        TextView tv_input_nilai_t;
        TextView tv_input_nilai_d;
        TextView tv_input_nilai_yellow;
        TextView tv_input_nilai_red;
        TextView tv_input_nilai_z;
        TextView tv_input_nilai_a;
        Holder(@NonNull View itemView) {
            super(itemView);
            tv_input_nilai_no = itemView.findViewById(R.id.tv_input_nilai_no);
            tv_input_nilai_g = itemView.findViewById(R.id.tv_input_nilai_g);
            tv_input_nilai_f = itemView.findViewById(R.id.tv_input_nilai_f);
            tv_input_nilai_t = itemView.findViewById(R.id.tv_input_nilai_t);
            tv_input_nilai_d = itemView.findViewById(R.id.tv_input_nilai_d);
            tv_input_nilai_yellow = itemView.findViewById(R.id.tv_input_nilai_yellow);
            tv_input_nilai_red = itemView.findViewById(R.id.tv_input_nilai_red);
            tv_input_nilai_z = itemView.findViewById(R.id.tv_input_nilai_z);
            tv_input_nilai_a = itemView.findViewById(R.id.tv_input_nilai_a);

            itemView.findViewById(R.id.iv_up_g).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Penilaian penilaian = penilaians.get(getAdapterPosition());
                    int i = 0;
                    i = Integer.parseInt(tv_input_nilai_g.getText().toString());
                    i++;
                    viewsInputNilaiActivity.setGoalPemain(penilaian.getIdNilai(), i);
                    tv_input_nilai_g.setText(String.valueOf(i));
                }
            });

            itemView.findViewById(R.id.iv_down_g).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Penilaian penilaian = penilaians.get(getAdapterPosition());
                    int i = 0;
                    i = Integer.parseInt(tv_input_nilai_g.getText().toString());
                    i--;
                    viewsInputNilaiActivity.setGoalPemain(penilaian.getIdNilai(), i);
                    tv_input_nilai_g.setText(String.valueOf(i));
                }
            });
            itemView.findViewById(R.id.iv_up_f).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Penilaian penilaian = penilaians.get(getAdapterPosition());
                    int i = 0;
                    i = Integer.parseInt(tv_input_nilai_f.getText().toString());
                    i++;
                    viewsInputNilaiActivity.setFoulPemain(penilaian.getIdNilai(), i);
                    tv_input_nilai_f.setText(String.valueOf(i));
                }
            });
            itemView.findViewById(R.id.iv_down_f).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Penilaian penilaian = penilaians.get(getAdapterPosition());
                    int i = 0;
                    i = Integer.parseInt(tv_input_nilai_f.getText().toString());
                    i--;
                    viewsInputNilaiActivity.setFoulPemain(penilaian.getIdNilai(), i);
                    tv_input_nilai_f.setText(String.valueOf(i));
                }
            });
            itemView.findViewById(R.id.iv_up_t).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Penilaian penilaian = penilaians.get(getAdapterPosition());
                    int i = 0;
                    i = Integer.parseInt(tv_input_nilai_t.getText().toString());
                    i++;
                    viewsInputNilaiActivity.setThreeStepPemain(penilaian.getIdNilai(), i);
                    tv_input_nilai_t.setText(String.valueOf(i));
                }
            });
            itemView.findViewById(R.id.iv_down_t).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Penilaian penilaian = penilaians.get(getAdapterPosition());
                    int i = 0;
                    i = Integer.parseInt(tv_input_nilai_t.getText().toString());
                    i--;
                    viewsInputNilaiActivity.setThreeStepPemain(penilaian.getIdNilai(), i);
                    tv_input_nilai_t.setText(String.valueOf(i));
                }
            });
            itemView.findViewById(R.id.iv_up_d).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Penilaian penilaian = penilaians.get(getAdapterPosition());
                    int i = 0;
                    i = Integer.parseInt(tv_input_nilai_d.getText().toString());
                    i++;
                    viewsInputNilaiActivity.setDoublePemain(penilaian.getIdNilai(), i);
                    tv_input_nilai_d.setText(String.valueOf(i));
                }
            });
            itemView.findViewById(R.id.iv_down_d).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Penilaian penilaian = penilaians.get(getAdapterPosition());
                    int i = 0;
                    i = Integer.parseInt(tv_input_nilai_d.getText().toString());
                    i--;
                    viewsInputNilaiActivity.setDoublePemain(penilaian.getIdNilai(), i);
                    tv_input_nilai_d.setText(String.valueOf(i));
                }
            });
            itemView.findViewById(R.id.iv_up_k).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Penilaian penilaian = penilaians.get(getAdapterPosition());
                    int i = 0;
                    i = Integer.parseInt(tv_input_nilai_yellow.getText().toString());
                    i++;
                    viewsInputNilaiActivity.setKuningPemain(penilaian.getIdNilai(), i);
                    tv_input_nilai_yellow.setText(String.valueOf(i));
                }
            });
            itemView.findViewById(R.id.iv_down_k).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Penilaian penilaian = penilaians.get(getAdapterPosition());
                    int i = 0;
                    i = Integer.parseInt(tv_input_nilai_yellow.getText().toString());
                    i--;
                    viewsInputNilaiActivity.setKuningPemain(penilaian.getIdNilai(), i);
                    tv_input_nilai_yellow.setText(String.valueOf(i));
                }
            });
            itemView.findViewById(R.id.iv_up_m).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Penilaian penilaian = penilaians.get(getAdapterPosition());
                    int i = 0;
                    i = Integer.parseInt(tv_input_nilai_red.getText().toString());
                    i++;
                    viewsInputNilaiActivity.setMerahPemain(penilaian.getIdNilai(), i);
                    tv_input_nilai_red.setText(String.valueOf(i));
                }
            });
            itemView.findViewById(R.id.iv_down_m).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Penilaian penilaian = penilaians.get(getAdapterPosition());
                    int i = 0;
                    i = Integer.parseInt(tv_input_nilai_red.getText().toString());
                    i--;
                    viewsInputNilaiActivity.setMerahPemain(penilaian.getIdNilai(), i);
                    tv_input_nilai_red.setText(String.valueOf(i));
                }
            });
            itemView.findViewById(R.id.iv_up_2).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Penilaian penilaian = penilaians.get(getAdapterPosition());
                    int i = 0;
                    i = Integer.parseInt(tv_input_nilai_z.getText().toString());
                    i++;
                    viewsInputNilaiActivity.setSuspendPemain(penilaian.getIdNilai(), i);
                    tv_input_nilai_z.setText(String.valueOf(i));
                }
            });
            itemView.findViewById(R.id.iv_down_2).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Penilaian penilaian = penilaians.get(getAdapterPosition());
                    int i = 0;
                    i = Integer.parseInt(tv_input_nilai_z.getText().toString());
                    i--;
                    viewsInputNilaiActivity.setSuspendPemain(penilaian.getIdNilai(), i);
                    tv_input_nilai_z.setText(String.valueOf(i));
                }
            });
            itemView.findViewById(R.id.iv_up_a).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Penilaian penilaian = penilaians.get(getAdapterPosition());
                    int i = 0;
                    i = Integer.parseInt(tv_input_nilai_a.getText().toString());
                    i++;
                    viewsInputNilaiActivity.setAttackPemain(penilaian.getIdNilai(), i);
                    tv_input_nilai_a.setText(String.valueOf(i));
                }
            });
            itemView.findViewById(R.id.iv_down_a).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Penilaian penilaian = penilaians.get(getAdapterPosition());
                    int i = 0;
                    i = Integer.parseInt(tv_input_nilai_a.getText().toString());
                    i--;
                    viewsInputNilaiActivity.setAttackPemain(penilaian.getIdNilai(), i);
                    tv_input_nilai_a.setText(String.valueOf(i));
                }
            });
        }

    }
}
