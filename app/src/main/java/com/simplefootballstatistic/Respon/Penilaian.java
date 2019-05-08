package com.simplefootballstatistic.Respon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Penilaian {
    @SerializedName("id_nilai")
    @Expose
    public Integer idNilai;
    @SerializedName("pemain_id")
    @Expose
    public Integer pemainId;
    @SerializedName("team_id")
    @Expose
    public Integer teamId;
    @SerializedName("goal")
    @Expose
    public Integer goal;
    @SerializedName("foul")
    @Expose
    public Integer foul;
    @SerializedName("three_step")
    @Expose
    public Integer threeStep;
    @SerializedName("double")
    @Expose
    public Integer _double;
    @SerializedName("kartu_kuning")
    @Expose
    public Integer kartuKuning;
    @SerializedName("kartu_merah")
    @Expose
    public Integer kartuMerah;
    @SerializedName("suspand")
    @Expose
    public Integer suspand;
    @SerializedName("attack")
    @Expose
    public Integer attack;
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
    @SerializedName("id_pemain")
    @Expose
    public Integer idPemain;
    @SerializedName("nama_pemain")
    @Expose
    public String namaPemain;
    @SerializedName("no_punggung")
    @Expose
    public Integer noPunggung;

    public Integer getIdNilai() {
        return idNilai;
    }

    public void setIdNilai(Integer idNilai) {
        this.idNilai = idNilai;
    }

    public Integer getPemainId() {
        return pemainId;
    }

    public void setPemainId(Integer pemainId) {
        this.pemainId = pemainId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getGoal() {
        return goal;
    }

    public void setGoal(Integer goal) {
        this.goal = goal;
    }

    public Integer getFoul() {
        return foul;
    }

    public void setFoul(Integer foul) {
        this.foul = foul;
    }

    public Integer getThreeStep() {
        return threeStep;
    }

    public void setThreeStep(Integer threeStep) {
        this.threeStep = threeStep;
    }

    public Integer get_double() {
        return _double;
    }

    public void set_double(Integer _double) {
        this._double = _double;
    }

    public Integer getKartuKuning() {
        return kartuKuning;
    }

    public void setKartuKuning(Integer kartuKuning) {
        this.kartuKuning = kartuKuning;
    }

    public Integer getKartuMerah() {
        return kartuMerah;
    }

    public void setKartuMerah(Integer kartuMerah) {
        this.kartuMerah = kartuMerah;
    }

    public Integer getSuspand() {
        return suspand;
    }

    public void setSuspand(Integer suspand) {
        this.suspand = suspand;
    }

    public Integer getAttack() {
        return attack;
    }

    public void setAttack(Integer attack) {
        this.attack = attack;
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

    public Integer getIdPemain() {
        return idPemain;
    }

    public void setIdPemain(Integer idPemain) {
        this.idPemain = idPemain;
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
}
