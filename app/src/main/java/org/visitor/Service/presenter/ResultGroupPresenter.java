package org.visitor.Service.presenter;


import org.visitor.Service.presenter.model.Groups;

import java.util.ArrayList;

/**
 * Created by renjer on 1/10/2017.
 */

public interface ResultGroupPresenter {

    void onErrorServer(String e);

    void onErrorInternetConnection();

    void onSuccessResultSearch(ArrayList<Groups> response);


}
