package org.visitor.Service.presenter;


import org.visitor.Service.presenter.model.Kala;
import org.visitor.Service.presenter.model.KalaResponse;

import java.util.ArrayList;

/**
 * Created by renjer on 1/10/2017.
 */

public interface ResultKalaPresenter {

    void onStart();

    void onErrorServer(String e);

    void onErrorInternetConnection();

    void onSuccessResultSearch(ArrayList<Kala> result);

    void noBus();

    void onError(String msg);

    void onFinish();

}
