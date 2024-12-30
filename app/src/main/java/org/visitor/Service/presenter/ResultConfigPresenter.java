package org.visitor.Service.presenter;

public interface ResultConfigPresenter {
    void onErrorServer(String e);
    void onErrorInternetConnection();
    void onFinish(String dbName);
}
