package org.visitor.Service.presenter;

import org.visitor.Service.presenter.model.AccHsbPrsnsKoliResponse;
import org.visitor.Service.presenter.model.UserResponse;

public interface ResultLoginPresenter {
    void onStart();

    void onErrorServer(String e);

    void onErrorInternetConnection();

    void onSuccessResultSearch(UserResponse response);

    void noBus();

    void onError(String msg);

    void onFinish();

}
