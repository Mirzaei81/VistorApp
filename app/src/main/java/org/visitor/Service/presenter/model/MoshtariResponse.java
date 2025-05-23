package org.visitor.Service.presenter.model;


import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class MoshtariResponse implements Serializable
{

    @SerializedName("Moshtaris")
    @Expose
    private List<Moshtari> moshtaris;

    @SerializedName("gorohMs")
    @Expose
    private List<GorohM> gorohMs;
    private final static long serialVersionUID = 1058134121181443936L;

    public List<Moshtari> getMoshtaris() {
        return moshtaris;
    }

    public List<GorohM> getGorohMs() {
        return gorohMs;
    }
    public void setMoshtaris(List<Moshtari> moshtaris) {
        this.moshtaris = moshtaris;
    }

}

