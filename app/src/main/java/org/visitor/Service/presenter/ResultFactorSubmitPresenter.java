package org.visitor.Service.presenter;

import org.visitor.Service.presenter.model.FactorResponse;

public interface ResultFactorSubmitPresenter {
    void onErrorServer(String e);

    void onErrorInternetConnection();

    void onSuccessResultSearch(FactorResponse response);
}
