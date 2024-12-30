package org.visitor.Service.presenter;

import org.visitor.Service.presenter.model.MoshtariResponse;

public interface ResultMoshtariPresenter {
    void onErrorServer(String e);
    void onErrorInternetConnection();
    void onSuccessResultSearch(MoshtariResponse response);
}
