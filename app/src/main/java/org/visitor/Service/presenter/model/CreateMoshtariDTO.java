package org.visitor.Service.presenter.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateMoshtariDTO {
    @SerializedName("MGoroh")
    @Expose
    public Integer mGoroh;
    @SerializedName("MName")
    @Expose
    public String mName;
    @SerializedName("MAtbar")
    @Expose
    public Integer mAtbar;
    @SerializedName("MMobil")
    @Expose
    public String mMobil;
    @SerializedName("MAddress")
    @Expose
    public String mAddress;
    @SerializedName("MMeli")
    @Expose
    public String mMeli;
    @SerializedName("MHmkar")
    @Expose
    public Integer mHmkar;
    @SerializedName("MPost")
    @Expose
    public String mPost;
    @SerializedName("MCity")
    @Expose
    public String mCity;
    @SerializedName("MOstan")
    @Expose
    public String mOstan;

    /**
     * No args constructor for use in serialization
     *
     */
    public CreateMoshtariDTO() {
    }

    public CreateMoshtariDTO(Integer mGoroh, String mName, Integer mAtbar, String mMobil, String mAddress, String mMeli, Integer mHmkar, String mPost, String mCity, String mOstan) {
        super();
        this.mGoroh = mGoroh;
        this.mName = mName;
        this.mAtbar = mAtbar;
        this.mMobil = mMobil;
        this.mAddress = mAddress;
        this.mMeli = mMeli;
        this.mHmkar = mHmkar;
        this.mPost = mPost;
        this.mCity = mCity;
        this.mOstan = mOstan;
    }

    public Integer getMGoroh() {
        return mGoroh;
    }

    public void setMGoroh(Integer mGoroh) {
        this.mGoroh = mGoroh;
    }

    public CreateMoshtariDTO withMGoroh(Integer mGoroh) {
        this.mGoroh = mGoroh;
        return this;
    }

    public String getMName() {
        return mName;
    }

    public void setMName(String mName) {
        this.mName = mName;
    }

    public CreateMoshtariDTO withMName(String mName) {
        this.mName = mName;
        return this;
    }

    public Integer getMAtbar() {
        return mAtbar;
    }

    public void setMAtbar(Integer mAtbar) {
        this.mAtbar = mAtbar;
    }

    public CreateMoshtariDTO withMAtbar(Integer mAtbar) {
        this.mAtbar = mAtbar;
        return this;
    }

    public String getMMobil() {
        return mMobil;
    }

    public void setMMobil(String mMobil) {
        this.mMobil = mMobil;
    }

    public CreateMoshtariDTO withMMobil(String mMobil) {
        this.mMobil = mMobil;
        return this;
    }

    public String getMAddress() {
        return mAddress;
    }

    public void setMAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    public CreateMoshtariDTO withMAddress(String mAddress) {
        this.mAddress = mAddress;
        return this;
    }

    public String getMMeli() {
        return mMeli;
    }

    public void setMMeli(String mMeli) {
        this.mMeli = mMeli;
    }

    public CreateMoshtariDTO withMMeli(String mMeli) {
        this.mMeli = mMeli;
        return this;
    }

    public Integer getMHmkar() {
        return mHmkar;
    }

    public void setMHmkar(Integer mHmkar) {
        this.mHmkar = mHmkar;
    }

    public CreateMoshtariDTO withMHmkar(Integer mHmkar) {
        this.mHmkar = mHmkar;
        return this;
    }

    public String getMPost() {
        return mPost;
    }

    public void setMPost(String mPost) {
        this.mPost = mPost;
    }

    public CreateMoshtariDTO withMPost(String mPost) {
        this.mPost = mPost;
        return this;
    }

    public String getMCity() {
        return mCity;
    }

    public void setMCity(String mCity) {
        this.mCity = mCity;
    }

    public CreateMoshtariDTO withMCity(String mCity) {
        this.mCity = mCity;
        return this;
    }

    public String getMOstan() {
        return mOstan;
    }

    public void setMOstan(String mOstan) {
        this.mOstan = mOstan;
    }

    public CreateMoshtariDTO withMOstan(String mOstan) {
        this.mOstan = mOstan;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("یک مشتری تازه ثبت شده است");
        sb.append("گروه");
        sb.append('=');
        sb.append(((this.mGoroh == null)?"<null>":this.mGoroh));
        sb.append(',');
        sb.append("نام و نام خانوادگی");
        sb.append('=');
        sb.append(((this.mName == null)?"<null>":this.mName));
        sb.append(',');
        sb.append("موبایل");
        sb.append('=');
        sb.append(((this.mMobil == null)?"<null>":this.mMobil));
        sb.append(',');
        sb.append("ادرس");
        sb.append('=');
        sb.append(((this.mAddress == null)?"<null>":this.mAddress));
        sb.append(',');
        sb.append(" کد ملی");
        sb.append('=');
        sb.append(((this.mMeli == null)?"<null>":this.mMeli));
        sb.append(',');
        sb.append("کد همکار");
        sb.append('=');
        sb.append(((this.mHmkar == null)?"<null>":this.mHmkar));
        sb.append(',');
        sb.append("کد پستی");
        sb.append('=');
        sb.append(((this.mPost == null)?"<null>":this.mPost));
        sb.append(',');
        sb.append("شهر");
        sb.append('=');
        sb.append(((this.mCity == null)?"<null>":this.mCity));
        sb.append(',');
        sb.append("استان");
        sb.append('=');
        sb.append(((this.mOstan == null)?"<null>":this.mOstan));
        return sb.toString();
    }

}
