package org.visitor.Service.presenter.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SubmitFactorBody {
    @SerializedName("F_Markz")
    public int f_Markz;
    @SerializedName("LoginId")
    public int loginId;
    @SerializedName("Anbar")
    public int anbar;
    @SerializedName("MoshtaryId")
    public int moshtaryId;
    @SerializedName("factorDetails")
    ArrayList<FactorDetail> factorDetails;
}
