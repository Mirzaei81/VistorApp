package org.visitor.Service.presenter;


import org.visitor.Service.presenter.model.AccHsbPrsnsKoliResponse;
import org.visitor.Service.presenter.model.FactorDetail;
import org.visitor.Service.presenter.model.HsbPrsnsKoli;
import org.visitor.Service.presenter.model.MoshtariResponse;

import java.util.List;

/**
 * Created by renjer on 1/10/2017.
 */

public interface ResultFactorPresenter {

    void onErrorServer(String e);

    void onErrorInternetConnection();

    void onSuccessResultSearch(AccHsbPrsnsKoliResponse response);
}
