package org.visitor.Service.presenter;

import org.visitor.Service.presenter.model.AccHsbPrsnsKoliResponse;
import org.visitor.Service.presenter.model.UserResponse;

public interface ResultLoginPresenter {
    void onErrorServer(String e);
    void onErrorInternetConnection();
    void onFinish(UserResponse response);
}
