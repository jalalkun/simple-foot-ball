package com.simplefootballstatistic.Respon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RestPenilaian {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private List<Penilaian> penilaians = null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<Penilaian> getPenilaians() {
        return penilaians;
    }

    public void setPenilaians(List<Penilaian> penilaians) {
        this.penilaians = penilaians;
    }
}
