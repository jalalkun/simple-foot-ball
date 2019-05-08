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
import com.simplefootballstatistic.Views.ViewsLihatNilai;

import java.util.List;

public class AdapterLihatNilai extends RecyclerView.Adapter<AdapterLihatNilai.Holder> {
    private List<Penilaian> penilaians;
    private Activity activity;
    private LayoutInflater inflater;
    private ViewsLihatNilai viewsLihatNilai;

    public AdapterLihatNilai(Activity activity, List<Penilaian> penilaians, ViewsLihatNilai viewsLihatNilai){
        this.activity = activity;
        this.penilaians = penilaians;
        this.viewsLihatNilai = viewsLihatNilai;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View rootView = inflater.inflate(R.layout.adapter_input_nilai, viewGroup, false);
        return new AdapterLihatNilai.Holder(rootView);
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
            itemView.findViewById(R.id.iv_up_g).setVisibility(View.GONE);
            itemView.findViewById(R.id.iv_down_g).setVisibility(View.GONE);
            itemView.findViewById(R.id.iv_up_f).setVisibility(View.GONE);
            itemView.findViewById(R.id.iv_down_f).setVisibility(View.GONE);
            itemView.findViewById(R.id.iv_up_t).setVisibility(View.GONE);
            itemView.findViewById(R.id.iv_down_t).setVisibility(View.GONE);
            itemView.findViewById(R.id.iv_up_d).setVisibility(View.GONE);
            itemView.findViewById(R.id.iv_down_d).setVisibility(View.GONE);
            itemView.findViewById(R.id.iv_up_k).setVisibility(View.GONE);
            itemView.findViewById(R.id.iv_down_k).setVisibility(View.GONE);
            itemView.findViewById(R.id.iv_up_m).setVisibility(View.GONE);
            itemView.findViewById(R.id.iv_down_m).setVisibility(View.GONE);
            itemView.findViewById(R.id.iv_up_2).setVisibility(View.GONE);
            itemView.findViewById(R.id.iv_down_2).setVisibility(View.GONE);
            itemView.findViewById(R.id.iv_up_a).setVisibility(View.GONE);
            itemView.findViewById(R.id.iv_down_a).setVisibility(View.GONE);
        }
    }
}
