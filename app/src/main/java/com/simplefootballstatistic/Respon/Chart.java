package com.simplefootballstatistic.Respon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Chart {

    @SerializedName("foul")
    @Expose
    private Integer foul;
    @SerializedName("goal")
    @Expose
    private Integer goal;
    @SerializedName("double")
    @Expose
    private Integer dobel;
    @SerializedName("three_step")
    @Expose
    private Integer threeStep;
    @SerializedName("kartu_kuning")
    @Expose
    private Integer kartuKuning;
    @SerializedName("kartu_merah")
    @Expose
    private Integer kartuMerah;
    @SerializedName("suspand")
    @Expose
    private Integer suspand;
    @SerializedName("attack")
    @Expose
    private Integer attack;
    @SerializedName("success")
    @Expose
    private Boolean success;

    public Integer getFoul() {
        return foul;
    }

    public void setFoul(Integer foul) {
        this.foul = foul;
    }

    public Integer getGoal() {
        return goal;
    }

    public void setGoal(Integer goal) {
        this.goal = goal;
    }

    public Integer getDobel() {
        return dobel;
    }

    public void setDobel(Integer dobel) {
        this.dobel = dobel;
    }

    public Integer getThreeStep() {
        return threeStep;
    }

    public void setThreeStep(Integer threeStep) {
        this.threeStep = threeStep;
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

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
