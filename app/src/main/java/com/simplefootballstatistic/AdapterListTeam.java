package com.simplefootballstatistic;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;
import com.simplefootballstatistic.Data.DataTeam;
import com.simplefootballstatistic.Respon.Team;
import com.simplefootballstatistic.Utils.LogTest;
import com.simplefootballstatistic.Utils.Methods;
import com.simplefootballstatistic.Utils.PopUp;
import com.simplefootballstatistic.Utils.Session;
import com.simplefootballstatistic.Views.ViewsListTeamActivity;

import java.util.List;

public class AdapterListTeam extends RecyclerView.Adapter<AdapterListTeam.Holder>{

    private List<Team> teamList;
    private Activity activity;
    private LayoutInflater inflater;
    private ViewsListTeamActivity viewsListTeamActivity;
    DataTeam dataTeam;
    Session session;

    public AdapterListTeam(Activity activity, ViewsListTeamActivity viewsListTeamActivity, List<Team> teams){
        this.activity = activity;
        this.viewsListTeamActivity = viewsListTeamActivity;
        this.teamList = teams;
        dataTeam = new DataTeam();
        dataTeam.init(this.viewsListTeamActivity);
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        session = new Session(activity);
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View rootView = inflater.inflate(R.layout.adapter_list_team, viewGroup, false);
        return new AdapterListTeam.Holder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        Team team = teamList.get(i);
        LogTest.look("adapterListTeam team " + new Gson().toJson(team));
        holder.nama_team.setText(team.getNamaTeam());
        holder.nama_pelatih.setText(team.getNamaPelatih());
    }

    @Override
    public int getItemCount() {
        return teamList.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        TextView nama_team;
        TextView nama_pelatih;
        Holder(@NonNull View itemView) {
            super(itemView);
            nama_team = itemView.findViewById(R.id.tv_nama_team);
            nama_pelatih = itemView.findViewById(R.id.tv_nama_pelatih);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Team team = teamList.get(getAdapterPosition());
//                    Intent intent = new Intent(activity, AddPemainActivity.class);
//                    intent.putExtra("id_team", team.getIdTeam());
//                    activity.startActivity(intent);
                    session.setSessionInteger("id_team", team.getIdTeam());
                    viewsListTeamActivity.bukaTeam();
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    final Team team = teamList.get(getAdapterPosition());
                    new PopUp().YesNo(activity, SweetAlertDialog.WARNING_TYPE, "Delete Tim", "Delete tim " + team.getNamaTeam(),
                            new Methods() {
                                @Override
                                public void function() {
                                    dataTeam.deleteTim(activity, String.valueOf(team.getIdTeam()));
                                }
                            });
                    return false;
                }
            });
        }
    }
}
