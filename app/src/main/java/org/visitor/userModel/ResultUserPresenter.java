package org.visitor.userModel;


import org.visitor.ResponseUser;
import org.visitor.Service.presenter.model.UserResponse;

/**
 * Created by renjer on 1/10/2017.
 */

public interface ResultUserPresenter {

    void onStart();

    void onErrorServer();

    void onErrorInternetConnection();

    void onSuccessResultSearch(UserResponse response);

    void noBus();

    void onError(String msg);

    void onFinish();

}
