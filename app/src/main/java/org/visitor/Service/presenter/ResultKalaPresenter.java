package org.visitor.Service.presenter;


import org.visitor.Service.presenter.model.KalaResponse;

/**
 * Created by renjer on 1/10/2017.
 */

public interface ResultKalaPresenter {

    void onStart();

    void onErrorServer(String e);

    void onErrorInternetConnection();

    void onSuccessResultSearch(KalaResponse response);

    void noBus();

    void onError(String msg);

    void onFinish();

}
