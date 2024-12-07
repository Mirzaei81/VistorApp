package org.visitor.Service.presenter.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;
@Entity(tableName = "kala_info")
public class Kala implements Serializable {
    @PrimaryKey
    @SerializedName("kCode")
    @Expose
    public Long kCode;
    @SerializedName("number")
    @Expose
    public Long number= Long.valueOf(0);
    @SerializedName("kGoroh")
    @Expose
    public Long kGoroh;
    @SerializedName("kName")
    @Expose
    public String kName;
    @SerializedName("kVahed")
    @Expose
    public String kVahed;
    @SerializedName("kVahedKoli")
    @Expose
    public String kVahedKoli;
    @SerializedName("kZarib")
    @Expose
    public Double kZarib;
    @SerializedName("kForoshM")
    @Expose
    public Double kForoshM;
    @SerializedName("kArz")
    @Expose
    public Long kArz;
    @SerializedName("kArzMab")
    @Expose
    public Double kArzMab;
    @SerializedName("kMinF")
    @Expose
    public Double kMinF;
    @SerializedName("kMaxF")
    @Expose
    public Double kMaxF;
    @SerializedName("kForoshH")
    @Expose
    public Double kForoshH;
    @SerializedName("kFani")
    @Expose
    public String kFani;
    @SerializedName("kSharh")
    @Expose
    public String kSharh;
    @SerializedName("kPic")
    @Expose
    public String kPic;
    @SerializedName("kLog")
    @Expose
    public Boolean kLog;
    @SerializedName("kLan")
    @Expose
    public Long kLan;
    @SerializedName("kNameL")
    @Expose
    public String kNameL;
    @SerializedName("kTolid")
    @Expose
    public String kTolid;
    @SerializedName("kHtolid")
    @Expose
    public String kHtolid;
    @SerializedName("kSarbar")
    @Expose
    public String kSarbar;
    @SerializedName("kHtmam")
    @Expose
    public String kHtmam;
    @SerializedName("kBarcode")
    @Expose
    public String kBarcode;
    @SerializedName("kOmde")
    @Expose
    public Double kOmde;
    @SerializedName("kVazn")
    @Expose
    public Double kVazn;
    @SerializedName("kDarsad")
    @Expose
    public Double kDarsad;
    @SerializedName("kKeshvar")
    @Expose
    public String kKeshvar;
    @SerializedName("kFmonth")
    @Expose
    public String kFmonth;
    @SerializedName("kFyear")
    @Expose
    public String kFyear;
    @SerializedName("kFmonth2")
    @Expose
    public Double kFmonth2;
    @SerializedName("kFmonth3")
    @Expose
    public Double kFmonth3;
    @SerializedName("kFmonth4")
    @Expose
    public Double kFmonth4;
    @SerializedName("kFmonth1")
    @Expose
    public Double kFmonth1;
    @SerializedName("kFmonth5")
    @Expose
    public Double kFmonth5;
    @SerializedName("kFmonth6")
    @Expose
    public Double kFmonth6;
    @SerializedName("kFmonth7")
    @Expose
    public Double kFmonth7;
    @SerializedName("kFmonth8")
    @Expose
    public Double kFmonth8;
    @SerializedName("kFmonth9")
    @Expose
    public Double kFmonth9;
    @SerializedName("kFmonth10")
    @Expose
    public Double kFmonth10;
    @SerializedName("kFmonth11")
    @Expose
    public Double kFmonth11;
    @SerializedName("kFmonth12")
    @Expose
    public Double kFmonth12;
    @SerializedName("kMaliatIs")
    @Expose
    public Boolean kMaliatIs;
    @SerializedName("kMaliat")
    @Expose
    public String kMaliat;
    @SerializedName("kAvarezIs")
    @Expose
    public Boolean kAvarezIs;
    @SerializedName("kAvarez")
    @Expose
    public String kAvarez;
    @SerializedName("kFani2")
    @Expose
    public String kFani2;
    @SerializedName("kPorsant")
    @Expose
    public Double kPorsant;
    @SerializedName("kIsBarcode")
    @Expose
    public Boolean kIsBarcode;
    @SerializedName("kForoshN")
    @Expose
    public Long kForoshN;
    @SerializedName("kForoshMd")
    @Expose
    public Long kForoshMd;
    @SerializedName("kForoshMsrf")
    @Expose
    public Long kForoshMsrf;
    @SerializedName("kGorohNavigation")
    @Expose
    public String kGorohNavigation;
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
        return kCode;
    }

    public void setkCode(Long kCode) {
        this.kCode = kCode;
    }

    public Long getkGoroh() {
        return kGoroh;
    }

    public void setkGoroh(Long kGoroh) {
        this.kGoroh = kGoroh;
    }

    public String getkName() {
        return kName;
    }

    public void setkName(String kName) {
        this.kName = kName;
    }

    public String getkVahed() {
        return kVahed;
    }

    public void setkVahed(String kVahed) {
        this.kVahed = kVahed;
    }

    public String getkVahedKoli() {
        return kVahedKoli;
    }

    public void setkVahedKoli(String kVahedKoli) {
        this.kVahedKoli = kVahedKoli;
    }

    public Double getkZarib() {
        return kZarib;
    }

    public void setkZarib(Double kZarib) {
        this.kZarib = kZarib;
    }

    public Double getkForoshM() {
        return kForoshM;
    }

    public void setkForoshM(Double kForoshM) {
        this.kForoshM = kForoshM;
    }

    public Long getkArz() {
        return kArz;
    }

    public void setkArz(Long kArz) {
        this.kArz = kArz;
    }

    public Double getkArzMab() {
        return kArzMab;
    }

    public void setkArzMab(Double kArzMab) {
        this.kArzMab = kArzMab;
    }

    public Double getkMinF() {
        return kMinF;
    }

    public void setkMinF(Double kMinF) {
        this.kMinF = kMinF;
    }

    public Double getkMaxF() {
        return kMaxF;
    }

    public void setkMaxF(Double kMaxF) {
        this.kMaxF = kMaxF;
    }

    public Double getkForoshH() {
        return kForoshH;
    }

    public void setkForoshH(Double kForoshH) {
        this.kForoshH = kForoshH;
    }

    public String getkFani() {
        return kFani;
    }

    public void setkFani(String kFani) {
        this.kFani = kFani;
    }

    public String getkSharh() {
        return kSharh;
    }

    public void setkSharh(String kSharh) {
        this.kSharh = kSharh;
    }

    public String getkPic() {
        return kPic;
    }

    public void setkPic(String kPic) {
        this.kPic = kPic;
    }

    public Boolean getkLog() {
        return kLog;
    }

    public void setkLog(Boolean kLog) {
        this.kLog = kLog;
    }

    public Long getkLan() {
        return kLan;
    }

    public void setkLan(Long kLan) {
        this.kLan = kLan;
    }

    public String getkNameL() {
        return kNameL;
    }

    public void setkNameL(String kNameL) {
        this.kNameL = kNameL;
    }

    public Object getkTolid() {
        return kTolid;
    }

    public void setkTolid(String kTolid) {
        this.kTolid = kTolid;
    }

    public String getkHtolid() {
        return kHtolid;
    }

    public void setkHtolid(String kHtolid) {
        this.kHtolid = kHtolid;
    }

    public String getkSarbar() {
        return kSarbar;
    }

    public void setkSarbar(String kSarbar) {
        this.kSarbar = kSarbar;
    }

    public String getkHtmam() {
        return kHtmam;
    }

    public void setkHtmam(String kHtmam) {
        this.kHtmam = kHtmam;
    }

    public String getkBarcode() {
        return kBarcode;
    }

    public void setkBarcode(String kBarcode) {
        this.kBarcode = kBarcode;
    }

    public Double getkOmde() {
        return kOmde;
    }

    public void setkOmde(Double kOmde) {
        this.kOmde = kOmde;
    }

    public Double getkVazn() {
        return kVazn;
    }

    public void setkVazn(Double kVazn) {
        this.kVazn = kVazn;
    }

    public Double getkDarsad() {
        return kDarsad;
    }

    public void setkDarsad(Double kDarsad) {
        this.kDarsad = kDarsad;
    }

    public String getkKeshvar() {
        return kKeshvar;
    }

    public void setkKeshvar(String kKeshvar) {
        this.kKeshvar = kKeshvar;
    }

    public String getkFmonth() {
        return kFmonth;
    }

    public void setkFmonth(String kFmonth) {
        this.kFmonth = kFmonth;
    }

    public String getkFyear() {
        return kFyear;
    }

    public void setkFyear(String kFyear) {
        this.kFyear = kFyear;
    }

    public Double getkFmonth2() {
        return kFmonth2;
    }

    public void setkFmonth2(Double kFmonth2) {
        this.kFmonth2 = kFmonth2;
    }

    public Double getkFmonth3() {
        return kFmonth3;
    }

    public void setkFmonth3(Double kFmonth3) {
        this.kFmonth3 = kFmonth3;
    }

    public Double getkFmonth4() {
        return kFmonth4;
    }

    public void setkFmonth4(Double kFmonth4) {
        this.kFmonth4 = kFmonth4;
    }

    public Double getkFmonth1() {
        return kFmonth1;
    }

    public void setkFmonth1(Double kFmonth1) {
        this.kFmonth1 = kFmonth1;
    }

    public Double getkFmonth5() {
        return kFmonth5;
    }

    public void setkFmonth5(Double kFmonth5) {
        this.kFmonth5 = kFmonth5;
    }

    public Double getkFmonth6() {
        return kFmonth6;
    }

    public void setkFmonth6(Double kFmonth6) {
        this.kFmonth6 = kFmonth6;
    }

    public Double getkFmonth7() {
        return kFmonth7;
    }

    public void setkFmonth7(Double kFmonth7) {
        this.kFmonth7 = kFmonth7;
    }

    public Double getkFmonth8() {
        return kFmonth8;
    }

    public void setkFmonth8(Double kFmonth8) {
        this.kFmonth8 = kFmonth8;
    }

    public Double getkFmonth9() {
        return kFmonth9;
    }

    public void setkFmonth9(Double kFmonth9) {
        this.kFmonth9 = kFmonth9;
    }

    public Double getkFmonth10() {
        return kFmonth10;
    }

    public void setkFmonth10(Double kFmonth10) {
        this.kFmonth10 = kFmonth10;
    }

    public Double getkFmonth11() {
        return kFmonth11;
    }

    public void setkFmonth11(Double kFmonth11) {
        this.kFmonth11 = kFmonth11;
    }

    public Double getkFmonth12() {
        return kFmonth12;
    }

    public void setkFmonth12(Double kFmonth12) {
        this.kFmonth12 = kFmonth12;
    }

    public Boolean getkMaliatIs() {
        return kMaliatIs;
    }

    public void setkMaliatIs(Boolean kMaliatIs) {
        this.kMaliatIs = kMaliatIs;
    }

    public String getkMaliat() {
        return kMaliat;
    }

    public void setkMaliat(String kMaliat) {
        this.kMaliat = kMaliat;
    }

    public Boolean getkAvarezIs() {
        return kAvarezIs;
    }

    public void setkAvarezIs(Boolean kAvarezIs) {
        this.kAvarezIs = kAvarezIs;
    }

    public String getkAvarez() {
        return kAvarez;
    }

    public void setkAvarez(String kAvarez) {
        this.kAvarez = kAvarez;
    }

    public String getkFani2() {
        return kFani2;
    }

    public void setkFani2(String kFani2) {
        this.kFani2 = kFani2;
    }

    public Double getkPorsant() {
        return kPorsant;
    }

    public void setkPorsant(Double kPorsant) {
        this.kPorsant = kPorsant;
    }

    public Boolean getkIsBarcode() {
        return kIsBarcode;
    }

    public void setkIsBarcode(Boolean kIsBarcode) {
        this.kIsBarcode = kIsBarcode;
    }

    public Long getkForoshN() {
        return kForoshN;
    }

    public void setkForoshN(Long kForoshN) {
        this.kForoshN = kForoshN;
    }

    public Long getkForoshMd() {
        return kForoshMd;
    }

    public void setkForoshMd(Long kForoshMd) {
        this.kForoshMd = kForoshMd;
    }

    public Long getkForoshMsrf() {
        return kForoshMsrf;
    }

    public void setkForoshMsrf(Long kForoshMsrf) {
        this.kForoshMsrf = kForoshMsrf;
    }

    public String getkGorohNavigation() {
        return kGorohNavigation;
    }

    public void setkGorohNavigation(String kGorohNavigation) {
        this.kGorohNavigation = kGorohNavigation;
    }



    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }
}
