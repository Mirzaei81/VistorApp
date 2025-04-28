package org.visitor.Service.presenter;

import org.visitor.Service.presenter.model.AccHsbPrsnsKoliResponse;
import org.visitor.Service.presenter.model.Moshtari;

public interface ResultMoshtariCreatePresenter {

    void onErrorServer(String e);

    void onErrorInternetConnection();

    void onSuccessResultSearch(Moshtari response);
}
