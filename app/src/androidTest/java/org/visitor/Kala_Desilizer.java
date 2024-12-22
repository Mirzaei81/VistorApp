package org.visitor;

import android.app.Instrumentation;
import android.content.Context;
import android.os.Debug;

import androidx.test.platform.app.InstrumentationRegistry;

import com.google.gson.Gson;

import org.junit.Assert;
import org.junit.Test;
import org.visitor.Service.presenter.model.KalaResponse;
import org.visitor.Tools.Databace.DataSaver;

import java.util.HashMap;

public class Kala_Desilizer {

    final String url ;
    final String DbName ;
    final Context context;
    public Kala_Desilizer(){
        context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        DataSaver dataSaver = new DataSaver(context);
        url = dataSaver.getHost()+"kalas";
        DbName = dataSaver.getConfig().databaseName;
    }
    @Test
    public void getUser(){
        new Thread(new Runnable() {
            @Override
            public void run () {
                HashMap<String, String> getHashMap = new HashMap<>();
                getHashMap.put(KeyConst.APP_KEY, KeyConst.appKey);
                getHashMap.put(KeyConst.APP_SECRET, KeyConst.appSecret);
                new WebServiceNetwork(context).requestWebServiceByGet(url, DbName, null, new NetworkListener() {
                    @Override
                    public void onStart() {
                    }

                    @Override
                    public void onErrorInternetConnection() {
                    }

                    @Override
                    public void onErrorServer(String e) {
                    }

                    @Override
                    public void onFinish(String result) {
                        try {
                            Gson gson = new Gson();
                            KalaResponse response = gson.fromJson(result, KalaResponse.class);
                            Assert.assertNotNull(response.Kalas);
                            if (response != null && response.Kalas!= null) {
                                System.out.println(response.Kalas);
                                Assert.assertEquals(response.Kalas.size(),3);
                            }
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }

                    }
                });

            }
        }).start();
    }
}