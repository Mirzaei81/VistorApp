package org.visitor.Service.presenter;


import org.visitor.Service.presenter.model.MoshtariResponse;

/**
 * Created by renjer on 1/10/2017.
 */

public interface ResultGroupPresenter {

    void onStart();

    void onErrorServer(String e);

    void onErrorInternetConnection();

    void onSuccessResultSearch(MoshtariResponse response);

    void noBus();

    void onError(String msg);

    void onFinish();

}
