package org.visitor.Service.presenter.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class HsbPrsnsKoli implements Serializable {
    @SerializedName("Brws")
    @Expose
    private Integer brws;
    @SerializedName("KindFactor")
    @Expose
    private String kindFactor;
    @SerializedName("Date")
    @Expose
    private String date;
    @SerializedName("No")
    @Expose
    private Integer no;
    @SerializedName("kind")
    @Expose
    private String kind;
    @SerializedName("Sharh")
    @Expose
    private String sharh;
    @SerializedName("Bes")
    @Expose
    private Double bes;
    @SerializedName("Bed")
    @Expose
    private Double bed;
    @SerializedName("F_Factor")
    @Expose
    private Integer fFactor;
    @SerializedName("F")
    @Expose
    private Object f;
    @SerializedName("Mande")
    @Expose
    private Integer mande;
    private final static long serialVersionUID = 3178013527405488621L;

    public Integer getBrws() {
        return brws;
    }

    public void setBrws(Integer brws) {
        this.brws = brws;
    }

    public String getKindFactor() {
        return kindFactor;
    }

    public void setKindFactor(String kindFactor) {
        this.kindFactor = kindFactor;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getSharh() {
        return sharh;
    }

    public void setSharh(String sharh) {
        this.sharh = sharh;
    }

    public Double getBes() {
        return bes;
    }

    public void setBes(Double bes) {
        this.bes = bes;
    }

    public Double getBed() {
        return bed;
    }

    public void setBed(Double bed) {
        this.bed = bed;
    }

    public Integer getfFactor() {
        return fFactor;
    }

    public Integer getMande() {
        return mande;
    }

    public void setMande(Integer mande) {
        this.mande = mande;
    }
}
