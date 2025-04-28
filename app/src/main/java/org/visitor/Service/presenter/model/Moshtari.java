package org.visitor.Service.presenter.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


@Entity(tableName = "Moshtari")
public class Moshtari implements Serializable
{

    @PrimaryKey
    @SerializedName("MCode")
    @Expose
    public Integer mCode;
    @SerializedName("MGoroh")
    @Expose
    public Integer mGoroh;
    @SerializedName("MName")
    @Expose
    public String mName;
    @SerializedName("MAtbar")
    @Expose
    public Integer mAtbar;
    @SerializedName("MTel1")
    @Expose
    public String mTel1;
    @SerializedName("MTel2")
    @Expose
    public String mTel2;
    @SerializedName("MMobil")
    @Expose
    public String mMobil;
    @SerializedName("MAddress")
    @Expose
    public String mAddress;
    @SerializedName("MMeli")
    @Expose
    public String mMeli;
    @SerializedName("MLog")
    @Expose
    public Boolean mLog;
    @SerializedName("MLogHsab")
    @Expose
    public Boolean mLogHsab;
    @SerializedName("MKol")
    @Expose
    public String mKol;
    @SerializedName("MMoein")
    @Expose
    public String mMoein;
    @SerializedName("MTfzili")
    @Expose
    public String mTfzili;
    @SerializedName("MAcc")
    @Expose
    public String mAcc;
    @SerializedName("MPic")
    @Expose
    public String mPic;
    @SerializedName("MTxt")
    @Expose
    public String mTxt;
    @SerializedName("MHmkar")
    @Expose
    public Integer mHmkar;
    @SerializedName("MLan")
    @Expose
    public Integer mLan;
    @SerializedName("MNameL")
    @Expose
    public String mNameL;
    @SerializedName("MOnvan")
    @Expose
    public Integer mOnvan;
    @SerializedName("MDateList")
    @Expose
    public Integer mDateList;
    @SerializedName("MEconomy")
    @Expose
    public String mEconomy;
    @SerializedName("MPost")
    @Expose
    public String mPost;
    @SerializedName("MCity")
    @Expose
    public String mCity;
    @SerializedName("MOstan")
    @Expose
    public String mOstan;
    @SerializedName("MPorsant")
    @Expose
    public Integer mPorsant;
    @SerializedName("MEmail")
    @Expose
    public String mEmail;
    @SerializedName("MCodeMantagh")
    @Expose
    public Integer mCodeMantagh;
    @SerializedName("MTel3")
    @Expose
    public String mTel3;
    @SerializedName("MTel4")
    @Expose
    public String mTel4;
    @SerializedName("MMobile2")
    @Expose
    public String mMobile2;
    @SerializedName("MTelSms")
    @Expose
    public Boolean mTelSms;
    @SerializedName("MSabt")
    @Expose
    public String mSabt;
    @SerializedName("MModir")
    @Expose
    public Boolean mModir;
    @SerializedName("MAccNavigation")
    @Expose
    public Integer mAccNavigation;
    @SerializedName("MGorohNavigation")
    @Expose
    public Integer mGorohNavigation;
    @SerializedName("MoshtariTkmili")
    @Expose
    public Integer moshtariTkmili;
    private final static long serialVersionUID = 8492332403414069907L;

    public Integer getmCode() {
        return mCode;
    }

    public void setmCode(Integer mCode) {
        this.mCode = mCode;
    }

    public Integer getmGoroh() {
        return mGoroh;
    }

    public void setmGoroh(Integer mGoroh) {
        this.mGoroh = mGoroh;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public Integer getmAtbar() {
        return mAtbar;
    }

    public void setmAtbar(Integer mAtbar) {
        this.mAtbar = mAtbar;
    }

    public String getmTel1() {
        return mTel1;
    }

    public void setmTel1(String mTel1) {
        this.mTel1 = mTel1;
    }

    public String getmTel2() {
        return mTel2;
    }

    public void setmTel2(String mTel2) {
        this.mTel2 = mTel2;
    }

    public String getmMobil() {
        return mMobil;
    }

    public void setmMobil(String mMobil) {
        this.mMobil = mMobil;
    }

    public String getmAddress() {
        return mAddress;
    }

    public void setmAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    public String getmMeli() {
        return mMeli;
    }

    public void setmMeli(String mMeli) {
        this.mMeli = mMeli;
    }

    public Boolean getmLog() {
        return mLog;
    }

    public void setmLog(Boolean mLog) {
        this.mLog = mLog;
    }

    public Boolean getmLogHsab() {
        return mLogHsab;
    }

    public void setmLogHsab(Boolean mLogHsab) {
        this.mLogHsab = mLogHsab;
    }

    public String getmKol() {
        return mKol;
    }

    public void setmKol(String mKol) {
        this.mKol = mKol;
    }

    public String getmMoein() {
        return mMoein;
    }

    public void setmMoein(String mMoein) {
        this.mMoein = mMoein;
    }

    public String getmTfzili() {
        return mTfzili;
    }

    public void setmTfzili(String mTfzili) {
        this.mTfzili = mTfzili;
    }

    public String getmAcc() {
        return mAcc;
    }

    public void setmAcc(String mAcc) {
        this.mAcc = mAcc;
    }

    public String getmPic() {
        return mPic;
    }

    public void setmPic(String mPic) {
        this.mPic = mPic;
    }

    public String getmTxt() {
        return mTxt;
    }

    public void setmTxt(String mTxt) {
        this.mTxt = mTxt;
    }

    public Integer getmHmkar() {
        return mHmkar;
    }

    public void setmHmkar(Integer mHmkar) {
        this.mHmkar = mHmkar;
    }

    public Integer getmLan() {
        return mLan;
    }

    public void setmLan(Integer mLan) {
        this.mLan = mLan;
    }

    public String getmNameL() {
        return mNameL;
    }

    public void setmNameL(String mNameL) {
        this.mNameL = mNameL;
    }

    public Integer getmOnvan() {
        return mOnvan;
    }

    public void setmOnvan(Integer mOnvan) {
        this.mOnvan = mOnvan;
    }

    public Integer getmDateList() {
        return mDateList;
    }

    public void setmDateList(Integer mDateList) {
        this.mDateList = mDateList;
    }

    public String getmEconomy() {
        return mEconomy;
    }

    public void setmEconomy(String mEconomy) {
        this.mEconomy = mEconomy;
    }

    public String getmPost() {
        return mPost;
    }

    public void setmPost(String mPost) {
        this.mPost = mPost;
    }

    public String getmCity() {
        return mCity;
    }

    public void setmCity(String mCity) {
        this.mCity = mCity;
    }

    public String getmOstan() {
        return mOstan;
    }

    public void setmOstan(String mOstan) {
        this.mOstan = mOstan;
    }

    public Integer getmPorsant() {
        return mPorsant;
    }

    public void setmPorsant(Integer mPorsant) {
        this.mPorsant = mPorsant;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public Integer getmCodeMantagh() {
        return mCodeMantagh;
    }

    public void setmCodeMantagh(Integer mCodeMantagh) {
        this.mCodeMantagh = mCodeMantagh;
    }

    public String getmTel3() {
        return mTel3;
    }

    public void setmTel3(String mTel3) {
        this.mTel3 = mTel3;
    }

    public String getmTel4() {
        return mTel4;
    }

    public void setmTel4(String mTel4) {
        this.mTel4 = mTel4;
    }

    public String getmMobile2() {
        return mMobile2;
    }

    public void setmMobile2(String mMobile2) {
        this.mMobile2 = mMobile2;
    }

    public Boolean getmTelSms() {
        return mTelSms;
    }

    public void setmTelSms(Boolean mTelSms) {
        this.mTelSms = mTelSms;
    }

    public String getmSabt() {
        return mSabt;
    }

    public void setmSabt(String mSabt) {
        this.mSabt = mSabt;
    }

    public Boolean getmModir() {
        return mModir;
    }

    public void setmModir(Boolean mModir) {
        this.mModir = mModir;
    }

    public Integer getmAccNavigation() {
        return mAccNavigation;
    }

    public void setmAccNavigation(Integer mAccNavigation) {
        this.mAccNavigation = mAccNavigation;
    }

    public Integer getmGorohNavigation() {
        return mGorohNavigation;
    }

    public void setmGorohNavigation(Integer mGorohNavigation) {
        this.mGorohNavigation = mGorohNavigation;
    }

    public Integer getMoshtariTkmili() {
        return moshtariTkmili;
    }

    public void setMoshtariTkmili(Integer moshtariTkmili) {
        this.moshtariTkmili = moshtariTkmili;
    }

}