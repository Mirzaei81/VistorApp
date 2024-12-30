package org.visitor.Service.presenter.model;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class AccHsbPrsnsKoliResponse implements Serializable
{

    @SerializedName("HsbPrsnsKoli")
    @Expose
    private List<HsbPrsnsKoli> hsbPrsnsKoli;

    public List<HsbPrsnsKoli> getHsbPrsnsKoli() {
        return hsbPrsnsKoli;
    }

    public void setHsbPrsnsKoli(List<HsbPrsnsKoli> hsbPrsnsKoli) {
        this.hsbPrsnsKoli = hsbPrsnsKoli;
    }

}

