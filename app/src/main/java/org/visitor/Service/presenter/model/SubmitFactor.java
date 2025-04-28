package org.visitor.Service.presenter.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class SubmitFactor {
    @SerializedName("F_Markz")
    @Expose
    private Integer fMarkz;
    @SerializedName("LoginId")
    @Expose
    private Integer loginId;
    @SerializedName("Anbar")
    @Expose
    private Integer anbar;
    @SerializedName("MoshtaryId")
    @Expose
    private Integer moshtaryId;

    @SerializedName("Sharh")
    @Expose
    private String Sharh;
    @SerializedName("factorDetails")
    @Expose
    private List<FactorDetail> factorDetails = new ArrayList<FactorDetail>();
    public SubmitFactor() {
    }

    public SubmitFactor(Integer fMarkz, Integer loginId, Integer anbar, Integer moshtaryId,String Sharh, List<FactorDetail> factorDetails) {
        super();
        this.Sharh = Sharh;
        this.fMarkz = fMarkz;
        this.loginId = loginId;
        this.anbar = anbar;
        this.moshtaryId = moshtaryId;
        this.factorDetails = factorDetails;
    }


}
