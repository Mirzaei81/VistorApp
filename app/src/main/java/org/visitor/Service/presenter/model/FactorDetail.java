package org.visitor.Service.presenter.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FactorDetail {
        @SerializedName("K_Code")
        @Expose
        public int k_Code;
        @SerializedName("K_Name")
        @Expose
        public String k_Name;
        @SerializedName("FK_koli")
        @Expose
        public int fK_koli;
        @SerializedName("K_zarib")
        @Expose
        public Double k_zarib;
        @SerializedName("K_Vahed")
        @Expose
        public String k_Vahed;
        @SerializedName("FK_Num")
        @Expose
        public int fK_Num;
        @SerializedName("FK_Mab")
        @Expose
        public double fK_Mab;
        @SerializedName("FK_Mab_koli")
        @Expose
        public double fK_Mab_koli;

        public FactorDetail(Long k_Code, String k_Name, String fK_koli, Double k_zarib, String k_Vahed, int fK_Num, double fK_Mab, double fK_Mab_koli) {
                this.k_Code = Math.toIntExact(k_Code);
                this.k_Name = k_Name;
                this.fK_koli = Integer.parseInt(fK_koli==null?"0":fK_koli);
                this.k_zarib = k_zarib;
                this.k_Vahed = k_Vahed;
                this.fK_Num = fK_Num;
                this.fK_Mab = fK_Mab;
                this.fK_Mab_koli = fK_Mab_koli;
        }

}
