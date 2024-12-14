package org.visitor;


import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.visitor.Service.presenter.ResaultConfigPresenter;
import org.visitor.Service.presenter.ResultFactorPresenter;
import org.visitor.Service.presenter.ResultLoginPresenter;
import org.visitor.Service.presenter.ResultUserNamePreasenter;
import org.visitor.Service.presenter.model.AccHsbPrsnsKoliResponse;
import org.visitor.Service.presenter.model.Kala;
import org.visitor.Service.presenter.model.KalaResponse;
import org.visitor.Service.presenter.ResultKalaPresenter;
import org.visitor.Service.presenter.model.MoshtariResponse;
import org.visitor.Service.presenter.ResultGroupPresenter;
import org.visitor.Service.presenter.model.UserResponse;
import org.visitor.Tools.Databace.DataSaver;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;


public class Api {

    private Context context;
    private Thread thread;
    private DataSaver dataSaver;
    private String DbName ;

    public Api(Context context,DataSaver ds) {
        this.context = context;
        dataSaver = ds;
        DbName = dataSaver.getConfig();
    }
    public void getDbName(final float year, final String Daftar, final String Company, final ResaultConfigPresenter resaultConfigPresenter)
    {
        final String url  = dataSaver.getHost()+"v1/Auth/db";
        final  String body = String.format(Locale.forLanguageTag("en-US"),"{ \"year\":\"%f\",\"daftar\":\"%s\",\"company\":\"%s\"}",year,Daftar,Company);
        new Thread(new Runnable() {
            @Override
            public void run() {
                new WebServiceNetwork(context).requestWebServiceByPost(url, body, new NetworkListener() {
                    @Override
                    public void onStart(){}
                    @Override
                    public void onErrorInternetConnection(){resaultConfigPresenter.onErrorInternetConnection();}

                    @Override
                    public void onErrorServer(String e) {resaultConfigPresenter.onErrorServer(e);}

                    @Override
                    public void onFinish(String result) {resaultConfigPresenter.onFinish(result);}
                });
            }
        }).start();
    }
    public  void login(final String username, final String password,final ResultLoginPresenter resultLoginPresenter){
       final  String url = dataSaver.getHost()+"v1/Auth";
       final  String body= "{" +
               "\"username\":\""+username +
               "\",\"password\":\"" +password +
               "\"}";
       cancelRequest();
       thread = new Thread(new Runnable() {
           @Override
           public void run() {
              new WebServiceNetwork(context).requestWebServiceByPost(url, body, new NetworkListener() {
                  @Override
                  public void onStart() {
                  }

                  @Override
                  public void onErrorInternetConnection() {
                      resultLoginPresenter.onErrorInternetConnection();
                  }

                  @Override
                  public void onErrorServer(String e) {
                      resultLoginPresenter.onErrorServer(e);
                  }

                  @Override
                  public void onFinish(String result) {
                      UserResponse userResponse = new Gson().fromJson(result,UserResponse.class);
                      if(userResponse!=null){
                          resultLoginPresenter.onFinish(userResponse);
                      }else{
                          resultLoginPresenter.onErrorServer("500 Couldn't parse the result");
                      }
                  }
              });
           }
       });
       thread.start();
    }
    public void getUsers(final ResultUserNamePreasenter resultUserNamePreasenter){
        final  String url = dataSaver.getHost()+"v1/Auth/users";
        cancelRequest();
        new Thread(new Runnable() {
            @Override
            public void run() {
                new WebServiceNetwork(context).requestWebServiceByGet(url,DbName, null, new NetworkListener() {
                    @Override
                    public void onStart() {}

                    @Override
                    public void onErrorInternetConnection() {
                        resultUserNamePreasenter.onErrorInternetConnection();
                    }

                    @Override
                    public void onErrorServer(String e) {
                        resultUserNamePreasenter.onErrorServer(e);
                    }

                    @Override
                    public void onFinish(String result) {
                        try {
                            String[] response = new Gson().fromJson(result,String[].class);
                            if (response != null  ) {
                                resultUserNamePreasenter.onFinish(response);
                            }else{
                                resultUserNamePreasenter.onErrorServer(result);
                            }
                        } catch (Exception e) {
                            resultUserNamePreasenter.onErrorServer(e.getMessage());
                        }

                    }
                });
            };
        }).start();
    }
    public void getKalas(final ResultKalaPresenter resultSearchBusPresenter) {
        final String url =dataSaver.getHost()+"kalas";
        cancelRequest();
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                HashMap<String, String> getHashMap = new HashMap<>();
                getHashMap.put(KeyConst.APP_KEY, KeyConst.appKey);
                getHashMap.put(KeyConst.APP_SECRET, KeyConst.appSecret);
                new WebServiceNetwork(context).requestWebServiceByGet(url,DbName, null, new NetworkListener() {
                    @Override
                    public void onStart() {
                        resultSearchBusPresenter.onStart();
                    }

                    @Override
                    public void onErrorInternetConnection() {
                        resultSearchBusPresenter.onErrorInternetConnection();
                        resultSearchBusPresenter.onFinish();
                    }

                    @Override
                    public void onErrorServer(String e) {
                        resultSearchBusPresenter.onErrorServer(e);
                        resultSearchBusPresenter.onFinish();
                    }

                    @Override
                    public void onFinish(String result) {
                        try {
                            Type tokenType = new TypeToken<KalaResponse>() {}.getType();
                            Gson gson = new GsonBuilder().create();
                            KalaResponse response = gson.fromJson(result, KalaResponse.class);
                            if (response !=null &&  !response.Kalas.isEmpty()  ) {
                                resultSearchBusPresenter.onSuccessResultSearch(response);
                            } else
                                resultSearchBusPresenter.noBus();
                        } catch (Exception e) {
                            resultSearchBusPresenter.onErrorServer(e.toString());
                        } finally {
                            resultSearchBusPresenter.onFinish();
                        }

                    }
                });

            }
        });
        thread.start();
    }
    public void getMoshtaris(final ResultGroupPresenter resultSearchBusPresenter) {
        final String url =dataSaver.getHost()+"moshtaris";
        cancelRequest();

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                HashMap<String, String> getHashMap = new HashMap<>();
                getHashMap.put(KeyConst.APP_KEY, KeyConst.appKey);
                getHashMap.put(KeyConst.APP_SECRET, KeyConst.appSecret);
                new WebServiceNetwork(context).requestWebServiceByGet(url,DbName,getHashMap, new NetworkListener() {
                    @Override
                    public void onStart() {
                        resultSearchBusPresenter.onStart();
                    }

                    @Override
                    public void onErrorInternetConnection() {
                        resultSearchBusPresenter.onErrorInternetConnection();
                        resultSearchBusPresenter.onFinish();
                    }

                    @Override
                    public void onErrorServer(String e) {
                        resultSearchBusPresenter.onErrorServer(e);
                        resultSearchBusPresenter.onFinish();
                    }

                    @Override
                    public void onFinish(String result) {
                        try {
                            Gson gson = new Gson();
                            MoshtariResponse response = gson.fromJson(result, MoshtariResponse.class);
                            if (response != null  ) {
                                resultSearchBusPresenter.onSuccessResultSearch(response);
                            } else
                                resultSearchBusPresenter.noBus();
                        } catch (Exception e) {
                            resultSearchBusPresenter.onErrorServer(e.toString());
                        } finally {
                            resultSearchBusPresenter.onFinish();
                        }

                    }
                });

            }
        });
        thread.start();
    }

    public void getFactor(String endDate, String startDate, String codeMoshtari, final ResultFactorPresenter resultSearchBusPresenter) {

        final String url =BaseConfig.BASE_URL_MASTER+"acc_HsbPrsnsKoli?dateTo="+endDate+"&dateFrom="+startDate+"&codeM="+codeMoshtari+"&mrkaz=1&mandDate=0&kind=A";
        cancelRequest();

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                HashMap<String, String> getHashMap = new HashMap<>();
                getHashMap.put(KeyConst.APP_KEY, KeyConst.appKey);
                getHashMap.put(KeyConst.APP_SECRET, KeyConst.appSecret);
                new WebServiceNetwork(context).requestWebServiceByGet(url,DbName, null, new NetworkListener() {
                    @Override
                    public void onStart() {
                        resultSearchBusPresenter.onStart();
                    }

                    @Override
                    public void onErrorInternetConnection() {
                        resultSearchBusPresenter.onErrorInternetConnection();
                        resultSearchBusPresenter.onFinish();
                    }

                    @Override
                    public void onErrorServer(String e) {
                        resultSearchBusPresenter.onErrorServer(e);
                        resultSearchBusPresenter.onFinish();
                    }

                    @Override
                    public void onFinish(String result) {
                        try {
                            Gson gson = new Gson();
                            AccHsbPrsnsKoliResponse response = gson.fromJson(result, AccHsbPrsnsKoliResponse.class);
                            if (response != null  ) {
                                resultSearchBusPresenter.onSuccessResultSearch(response);
                            } else
                                resultSearchBusPresenter.noBus();
                        } catch (Exception e) {
                            resultSearchBusPresenter.onErrorServer(e.toString());
                        } finally {
                            resultSearchBusPresenter.onFinish();
                        }

                    }
                });

            }
        });
        thread.start();
    }

    public void sendFactor(String endDate, String startDate, String codeMoshtari, final ResultFactorPresenter resultSearchBusPresenter) {

        final String url =BaseConfig.BASE_URL_MASTER+"acc_HsbPrsnsKoli?dateTo="+endDate+"&dateFrom="+startDate+"&codeM="+codeMoshtari+"&mrkaz=1&mandDate=0&kind=A";
        cancelRequest();

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                HashMap<String, String> getHashMap = new HashMap<>();
                getHashMap.put(KeyConst.APP_KEY, KeyConst.appKey);
                getHashMap.put(KeyConst.APP_SECRET, KeyConst.appSecret);
                new WebServiceNetwork(context).requestWebServiceByGet(url,DbName, null, new NetworkListener() {
                    @Override
                    public void onStart() {
                        resultSearchBusPresenter.onStart();
                    }

                    @Override
                    public void onErrorInternetConnection() {
                        resultSearchBusPresenter.onErrorInternetConnection();
                        resultSearchBusPresenter.onFinish();
                    }

                    @Override
                    public void onErrorServer(String e) {
                        resultSearchBusPresenter.onErrorServer(e);
                        resultSearchBusPresenter.onFinish();
                    }

                    @Override
                    public void onFinish(String result) {
                        try {
                            Gson gson = new Gson();
                            AccHsbPrsnsKoliResponse response = gson.fromJson(result, AccHsbPrsnsKoliResponse.class);
                            if (response != null  ) {
                                resultSearchBusPresenter.onSuccessResultSearch(response);
                            } else
                                resultSearchBusPresenter.noBus();
                        } catch (Exception e) {
                            resultSearchBusPresenter.onErrorServer(e.toString());
                        } finally {
                            resultSearchBusPresenter.onFinish();
                        }

                    }
                });

            }
        });
        thread.start();
    }
    public synchronized void cancelRequest() {
        if (thread != null) {
            thread.interrupt();
            thread = null;
        }
    }
}