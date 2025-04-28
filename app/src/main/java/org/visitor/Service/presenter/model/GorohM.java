package org.visitor.Service.presenter.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "gorohm_info")
public class GorohM implements Serializable {
    @PrimaryKey
    @SerializedName("GmCode")
    @Expose
    private Integer gmCode;
    @SerializedName("GmName")
    @Expose
    private String gmName;
    @SerializedName("GmInKharid")
    @Expose
    private Boolean gmInKharid;
    @SerializedName("GmInForosh")
    @Expose
    private Boolean gmInForosh;
    @SerializedName("GmInPosnt")
    @Expose
    private Boolean gmInPosnt;

    /**
     * No args constructor for use in serialization
     *
     */
    public GorohM() {
    }

    @Ignore
    public GorohM(Integer gmCode, String gmName, Boolean gmInKharid, Boolean gmInForosh, Boolean gmInPosnt) {
        super();
        this.gmCode = gmCode;
        this.gmName = gmName;
        this.gmInKharid = gmInKharid;
        this.gmInForosh = gmInForosh;
        this.gmInPosnt = gmInPosnt;
    }

    public Integer getGmCode() {
        return gmCode;
    }

    public void setGmCode(Integer gmCode) {
        this.gmCode = gmCode;
    }

    public GorohM withGmCode(Integer gmCode) {
        this.gmCode = gmCode;
        return this;
    }

    public String getGmName() {
        return gmName;
    }

    public void setGmName(String gmName) {
        this.gmName = gmName;
    }

    public GorohM withGmName(String gmName) {
        this.gmName = gmName;
        return this;
    }

    public Boolean getGmInKharid() {
        return gmInKharid;
    }

    public void setGmInKharid(Boolean gmInKharid) {
        this.gmInKharid = gmInKharid;
    }

    public GorohM withGmInKharid(Boolean gmInKharid) {
        this.gmInKharid = gmInKharid;
        return this;
    }

    public Boolean getGmInForosh() {
        return gmInForosh;
    }

    public void setGmInForosh(Boolean gmInForosh) {
        this.gmInForosh = gmInForosh;
    }

    public GorohM withGmInForosh(Boolean gmInForosh) {
        this.gmInForosh = gmInForosh;
        return this;
    }

    public Boolean getGmInPosnt() {
        return gmInPosnt;
    }

    public void setGmInPosnt(Boolean gmInPosnt) {
        this.gmInPosnt = gmInPosnt;
    }

    public GorohM withGmInPosnt(Boolean gmInPosnt) {
        this.gmInPosnt = gmInPosnt;
        return this;
    }



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(GorohM.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("gmCode");
        sb.append('=');
        sb.append(((this.gmCode == null) ? "<null>" : this.gmCode));
        sb.append(',');
        sb.append("gmName");
        sb.append('=');
        sb.append(((this.gmName == null) ? "<null>" : this.gmName));
        sb.append(',');
        sb.append("gmInKharid");
        sb.append('=');
        sb.append(((this.gmInKharid == null) ? "<null>" : this.gmInKharid));
        sb.append(',');
        sb.append("gmInForosh");
        sb.append('=');
        sb.append(((this.gmInForosh == null) ? "<null>" : this.gmInForosh));
        sb.append(',');
        sb.append("gmInPosnt");
        sb.append('=');
        sb.append(((this.gmInPosnt == null) ? "<null>" : this.gmInPosnt));
        sb.append(',');
        sb.append("moshtaris");
        sb.append('=');
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }
}
