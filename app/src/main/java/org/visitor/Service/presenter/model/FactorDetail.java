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
        public int k_zarib;
        @SerializedName("K_Vahed")
        @Expose
        public String k_Vahed;
        @SerializedName("FK_Num")
        @Expose
        public int fK_Num;
        @SerializedName("FK_Mab")
        @Expose
        public long fK_Mab;
        @SerializedName("FK_Mab_koli")
        @Expose
        public int fK_Mab_koli;
}
