package org.visitor.Service.presenter.model;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.visitor.Service.presenter.model.Moshtari;

public class KalaResponse implements Serializable
{

    @SerializedName("kalas")
    @Expose
    private List<Kala> kalas;

    public List<Kala> getKalas() {
        return kalas;
    }

    public void setKalas(List<Kala> kalas) {
        this.kalas = kalas;
    }


}
