package org.visitor.Service.presenter;

import org.visitor.Service.presenter.model.UserResponse;

public interface ResaultConfigPresenter {
    void onErrorServer(String e);
    void onErrorInternetConnection();
    void onFinish(String dbName);
}