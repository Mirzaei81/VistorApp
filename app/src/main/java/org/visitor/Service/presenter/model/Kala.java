package org.visitor.Service.presenter.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;
@Entity(tableName = "kala_info")
public class Kala implements Serializable {

    @SerializedName("KGoroh")
    @Expose
    public Long KGoroh;
    @SerializedName("KName")
    @Expose
    public String KName;
    @PrimaryKey
    @SerializedName("KCode")
    @Expose
    public Long KCode;
    @SerializedName("kVahed")
    @Expose
    public String KVahed;
    @SerializedName("kVahedKoli")
    @Expose
    public String KVahedKoli;
    @SerializedName("kZarib")
    @Expose
    public Double KZarib;
    @SerializedName("kForoshM")
    @Expose
    public Double KForoshM;
    @SerializedName("kArz")
    @Expose
    public Long KArz;
    @SerializedName("kArzMab")
    @Expose
    public Double KArzMab;
    @SerializedName("kMinF")
    @Expose
    public Double KMinF;
    @SerializedName("KMaxF")
    @Expose
    public Double KMaxF;
    @SerializedName("KForoshH")
    @Expose
    public Double KForoshH;
    @SerializedName("kFani")
    @Expose
    public String KFani;
    @SerializedName("kSharh")
    @Expose
    public String KSharh;
    @SerializedName("kPic")
    @Expose
    public String KPic;
    @SerializedName("kLog")
    @Expose
    public Boolean KLog;
    @SerializedName("kLan")
    @Expose
    public Long KLan;
    @SerializedName("kNameL")
    @Expose
    public String KNameL;
    @SerializedName("kTolid")
    @Expose
    public String KTolid;
    @SerializedName("kHtolid")
    @Expose
    public String KHtolid;
    @SerializedName("kSarbar")
    @Expose
    public String KSarbar;
    @SerializedName("kHtmam")
    @Expose
    public String KHtmam;
    @SerializedName("kBarcode")
    @Expose
    public String KBarcode;
    @SerializedName("kOmde")
    @Expose
    public Double KOmde;
    @SerializedName("kVazn")
    @Expose
    public Double KVazn;
    @SerializedName("kDarsad")
    @Expose
    public Double KDarsad;
    @SerializedName("kKeshvar")
    @Expose
    public String KKeshvar;
    @SerializedName("kFmonth")
    @Expose
    public String KFmonth;
    @SerializedName("kFyear")
    @Expose
    public String KFyear;
    @SerializedName("kFmonth2")
    @Expose
    public Double KFmonth2;
    @SerializedName("kFmonth3")
    @Expose
    public Double KFmonth3;
    @SerializedName("kFmonth4")
    @Expose
    public Double KFmonth4;
    @SerializedName("kFmonth1")
    @Expose
    public Double KFmonth1;
    @SerializedName("kFmonth5")
    @Expose
    public Double KFmonth5;
    @SerializedName("kFmonth6")
    @Expose
    public Double KFmonth6;
    @SerializedName("kFmonth7")
    @Expose
    public Double KFmonth7;
    @SerializedName("kFmonth8")
    @Expose
    public Double KFmonth8;
    @SerializedName("kFmonth9")
    @Expose
    public Double KFmonth9;
    @SerializedName("kFmonth10")
    @Expose
    public Double KFmonth10;
    @SerializedName("kFmonth11")
    @Expose
    public Double KFmonth11;
    @SerializedName("kFmonth12")
    @Expose
    public Double KFmonth12;
    @SerializedName("kMaliatIs")
    @Expose
    public Boolean KMaliatIs;
    @SerializedName("kMaliat")
    @Expose
    public String KMaliat;
    @SerializedName("kAvarezIs")
    @Expose
    public Boolean KAvarezIs;
    @SerializedName("kAvarez")
    @Expose
    public String KAvarez;
    @SerializedName("kFani2")
    @Expose
    public String KFani2;
    @SerializedName("kPorsant")
    @Expose
    public Double KPorsant;
    @SerializedName("kIsBarcode")
    @Expose
    public Boolean KIsBarcode;
    @SerializedName("kForoshN")
    @Expose
    public Long KForoshN;
    @SerializedName("kForoshMd")
    @Expose
    public Long KForoshMd;
    @SerializedName("kForoshMsrf")
    @Expose
    public Long KForoshMsrf;
    @SerializedName("kGorohNavigation")
    @Expose
    public String KGorohNavigation;
/*    @SerializedName("factor2s")
    @Expose
    private List<String> factor2s;
    @SerializedName("kalaMs")
    @Expose
    private List<String> kalaMs;*/
/*    @SerializedName("kalaTTKalaItmNavigations")
    @Expose
    private List<String> kalaTTKalaItmNavigations;*/
 /*   @SerializedName("kalaTTKalaNavigations")
    @Expose
    private List<String> kalaTTKalaNavigations;*/
/*    @SerializedName("sefaresh2s")
    @Expose
    private List<String> sefaresh2s;
    @SerializedName("tCodes")
    @Expose
    private List<String> tCodes;*/

    public Long getkCode() {
        return KCode;
    }

    public void setkCode(Long kCode) {
        this.KCode = kCode;
    }

    public Long getkGoroh() {
        return KGoroh;
    }

    public void setkGoroh(Long kGoroh) {
        this.KGoroh = kGoroh;
    }

    public String getkName() {
        return KName;
    }

    public void setkName(String kName) {
        this.KName = kName;
    }

    public String getkVahed() {
        return KVahed;
    }

    public void setkVahed(String kVahed) {
        this.KVahed = kVahed;
    }

    public String getkVahedKoli() {
        return KVahedKoli;
    }

    public void setkVahedKoli(String kVahedKoli) {
        this.KVahedKoli = kVahedKoli;
    }

    public Double getkZarib() {
        return KZarib;
    }

    public void setkZarib(Double kZarib) {
        this.KZarib = kZarib;
    }

    public Double getkForoshM() {
        return KForoshM;
    }

    public void setkForoshM(Double kForoshM) {
        this.KForoshM = kForoshM;
    }

    public Long getkArz() {
        return KArz;
    }

    public void setkArz(Long kArz) {
        this.KArz = kArz;
    }

    public Double getkArzMab() {
        return KArzMab;
    }

    public void setkArzMab(Double kArzMab) {
        this.KArzMab = kArzMab;
    }

    public Double getkMinF() {
        return KMinF;
    }

    public void setkMinF(Double kMinF) {
        this.KMinF = kMinF;
    }

    public Double getkMaxF() {
        return KMaxF;
    }

    public void setkMaxF(Double kMaxF) {
        this.KMaxF = kMaxF;
    }

    public Double getkForoshH() {
        return KForoshH;
    }

    public void setkForoshH(Double kForoshH) {
        this.KForoshH = kForoshH;
    }

    public String getkFani() {
        return KFani;
    }

    public void setkFani(String kFani) {
        this.KFani = kFani;
    }

    public String getkSharh() {
        return KSharh;
    }

    public void setkSharh(String kSharh) {
        this.KSharh = kSharh;
    }

    public String getkPic() {
        return KPic;
    }

    public void setkPic(String kPic) {
        this.KPic = kPic;
    }

    public Boolean getkLog() {
        return KLog;
    }

    public void setkLog(Boolean kLog) {
        this.KLog = kLog;
    }

    public Long getkLan() {
        return KLan;
    }

    public void setkLan(Long kLan) {
        this.KLan = kLan;
    }

    public String getkNameL() {
        return KNameL;
    }

    public void setkNameL(String kNameL) {
        this.KNameL = kNameL;
    }

    public Object getkTolid() {
        return KTolid;
    }

    public void setkTolid(String kTolid) {
        this.KTolid = kTolid;
    }

    public String getkHtolid() {
        return KHtolid;
    }

    public void setkHtolid(String kHtolid) {
        this.KHtolid = kHtolid;
    }

    public String getkSarbar() {
        return KSarbar;
    }

    public void setkSarbar(String kSarbar) {
        this.KSarbar = kSarbar;
    }

    public String getkHtmam() {
        return KHtmam;
    }

    public void setkHtmam(String kHtmam) {
        this.KHtmam = kHtmam;
    }

    public String getkBarcode() {
        return KBarcode;
    }

    public void setkBarcode(String kBarcode) {
        this.KBarcode = kBarcode;
    }

    public Double getkOmde() {
        return KOmde;
    }

    public void setkOmde(Double kOmde) {
        this.KOmde = kOmde;
    }

    public Double getkVazn() {
        return KVazn;
    }

    public void setkVazn(Double kVazn) {
        this.KVazn = kVazn;
    }

    public Double getkDarsad() {
        return KDarsad;
    }

    public void setkDarsad(Double kDarsad) {
        this.KDarsad = kDarsad;
    }

    public String getkKeshvar() {
        return KKeshvar;
    }

    public void setkKeshvar(String kKeshvar) {
        this.KKeshvar = kKeshvar;
    }

    public String getkFmonth() {
        return KFmonth;
    }

    public void setkFmonth(String kFmonth) {
        this.KFmonth = kFmonth;
    }

    public String getkFyear() {
        return KFyear;
    }

    public void setkFyear(String kFyear) {
        this.KFyear = kFyear;
    }

    public Double getkFmonth2() {
        return KFmonth2;
    }

    public void setkFmonth2(Double kFmonth2) {
        this.KFmonth2 = kFmonth2;
    }

    public Double getkFmonth3() {
        return KFmonth3;
    }

    public void setkFmonth3(Double kFmonth3) {
        this.KFmonth3 = kFmonth3;
    }

    public Double getkFmonth4() {
        return KFmonth4;
    }

    public void setkFmonth4(Double kFmonth4) {
        this.KFmonth4 = kFmonth4;
    }

    public Double getkFmonth1() {
        return KFmonth1;
    }

    public void setkFmonth1(Double kFmonth1) {
        this.KFmonth1 = kFmonth1;
    }

    public Double getkFmonth5() {
        return KFmonth5;
    }

    public void setkFmonth5(Double kFmonth5) {
        this.KFmonth5 = kFmonth5;
    }

    public Double getkFmonth6() {
        return KFmonth6;
    }

    public void setkFmonth6(Double kFmonth6) {
        this.KFmonth6 = kFmonth6;
    }

    public Double getkFmonth7() {
        return KFmonth7;
    }

    public void setkFmonth7(Double kFmonth7) {
        this.KFmonth7 = kFmonth7;
    }

    public Double getkFmonth8() {
        return KFmonth8;
    }

    public void setkFmonth8(Double kFmonth8) {
        this.KFmonth8 = kFmonth8;
    }

    public Double getkFmonth9() {
        return KFmonth9;
    }

    public void setkFmonth9(Double kFmonth9) {
        this.KFmonth9 = kFmonth9;
    }

    public Double getkFmonth10() {
        return KFmonth10;
    }

    public void setkFmonth10(Double kFmonth10) {
        this.KFmonth10 = kFmonth10;
    }

    public Double getkFmonth11() {
        return KFmonth11;
    }

    public void setkFmonth11(Double kFmonth11) {
        this.KFmonth11 = kFmonth11;
    }

    public Double getkFmonth12() {
        return KFmonth12;
    }

    public void setkFmonth12(Double kFmonth12) {
        this.KFmonth12 = kFmonth12;
    }

    public Boolean getkMaliatIs() {
        return KMaliatIs;
    }

    public void setkMaliatIs(Boolean kMaliatIs) {
        this.KMaliatIs = kMaliatIs;
    }

    public String getkMaliat() {
        return KMaliat;
    }

    public void setkMaliat(String kMaliat) {
        this.KMaliat = kMaliat;
    }

    public Boolean getkAvarezIs() {
        return KAvarezIs;
    }

    public void setkAvarezIs(Boolean kAvarezIs) {
        this.KAvarezIs = kAvarezIs;
    }

    public String getkAvarez() {
        return KAvarez;
    }

    public void setkAvarez(String kAvarez) {
        this.KAvarez = kAvarez;
    }

    public String getkFani2() {
        return KFani2;
    }

    public void setkFani2(String kFani2) {
        this.KFani2 = kFani2;
    }

    public Double getkPorsant() {
        return KPorsant;
    }

    public void setkPorsant(Double kPorsant) {
        this.KPorsant = kPorsant;
    }

    public Boolean getkIsBarcode() {
        return KIsBarcode;
    }

    public void setkIsBarcode(Boolean kIsBarcode) {
        this.KIsBarcode = kIsBarcode;
    }

    public Long getkForoshN() {
        return KForoshN;
    }

    public void setkForoshN(Long kForoshN) {
        this.KForoshN = kForoshN;
    }

    public Long getkForoshMd() {
        return KForoshMd;
    }

    public void setkForoshMd(Long kForoshMd) {
        this.KForoshMd = kForoshMd;
    }

    public Long getkForoshMsrf() {
        return KForoshMsrf;
    }

    public void setkForoshMsrf(Long kForoshMsrf) {
        this.KForoshMsrf = kForoshMsrf;
    }

    public String getkGorohNavigation() {
        return KGorohNavigation;
    }

    public void setkGorohNavigation(String kGorohNavigation) {
        this.KGorohNavigation = kGorohNavigation;
    }
}
