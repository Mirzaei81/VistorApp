package org.visitor.Service.presenter;

import org.visitor.Service.presenter.model.Anbar;

import java.util.ArrayList;

public interface ResultAnbarPresenter {
    void onErrorServer(String e);
    void onErrorInternetConnection();
    void onFinish(ArrayList<Anbar> anbars);
}
