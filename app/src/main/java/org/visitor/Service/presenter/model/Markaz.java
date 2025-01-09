package org.visitor.Service.presenter.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Markaz  implements Serializable {
    @SerializedName("ZCode")
    public int zCode;
    @SerializedName("ZName")
    public String zName;
    @SerializedName("ZFlag")
    public boolean zFlag;
}
