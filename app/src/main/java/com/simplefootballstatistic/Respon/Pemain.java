package com.simplefootballstatistic.Respon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pemain {
    @SerializedName("id_pemain")
    @Expose
    public Integer idPemain;
    @SerializedName("team_id")
    @Expose
    public Integer teamId;
    @SerializedName("nama_pemain")
    @Expose
    public String namaPemain;
    @SerializedName("no_punggung")
    @Expose
    public Integer noPunggung;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("updated_at")
    @Expose
    public String updatedAt;
    @SerializedName("id_team")
    @Expose
    public Integer idTeam;
    @SerializedName("nama_team")
    @Expose
    public String namaTeam;
    @SerializedName("nama_pelatih")
    @Expose
    public String namaPelatih;

    public Integer getIdPemain() {
        return idPemain;
    }

    public void setIdPemain(Integer idPemain) {
        this.idPemain = idPemain;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public String getNamaPemain() {
        return namaPemain;
    }

    public void setNamaPemain(String namaPemain) {
        this.namaPemain = namaPemain;
    }

    public Integer getNoPunggung() {
        return noPunggung;
    }

    public void setNoPunggung(Integer noPunggung) {
        this.noPunggung = noPunggung;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(Integer idTeam) {
        this.idTeam = idTeam;
    }

    public String getNamaTeam() {
        return namaTeam;
    }

    public void setNamaTeam(String namaTeam) {
        this.namaTeam = namaTeam;
    }

    public String getNamaPelatih() {
        return namaPelatih;
    }

    public void setNamaPelatih(String namaPelatih) {
        this.namaPelatih = namaPelatih;
    }
}
