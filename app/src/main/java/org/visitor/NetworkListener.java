package org.visitor;

/**
 * Created by renjer on 1/10/2017.
 */

public interface NetworkListener {

    void onStart();

    void onErrorInternetConnection();

    void onErrorServer(String e);

    void onFinish(String result);
}
