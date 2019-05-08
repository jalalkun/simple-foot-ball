package com.simplefootballstatistic.Respon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Team {
    @SerializedName("id_team")
    @Expose
    public Integer idTeam;
    @SerializedName("nama_team")
    @Expose
    public String namaTeam;
    @SerializedName("nama_pelatih")
    @Expose
    public String namaPelatih;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("updated_at")
    @Expose
    public String updatedAt;

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
}
