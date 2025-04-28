package org.visitor.Service.presenter.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FactorResponse
{
    @SerializedName("Uri")
    @Expose
    public String Uri;
    @SerializedName("id")
    @Expose
    public int id;
}
