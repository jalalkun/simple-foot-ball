package com.simplefootballstatistic.Respon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RestListPemain {
    @SerializedName("data")
    @Expose
    private List<Pemain> pemains = null;
    @SerializedName("success")
    @Expose
    private Boolean success;

    public List<Pemain> getPemains() {
        return pemains;
    }

    public void setPemains(List<Pemain> pemains) {
        this.pemains = pemains;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
