package org.visitor.Service.presenter;

import org.visitor.Service.presenter.model.FactorDetail;

import java.util.ArrayList;

public interface ResultFactorDetailPresenter {
    void onErrorServer(String e);

    void onErrorInternetConnection();

    void onSuccessResultSearch(ArrayList<FactorDetail> response);



}
