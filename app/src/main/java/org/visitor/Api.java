package org.visitor;


import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import org.visitor.Service.presenter.ResultFactorPresenter;
import org.visitor.Service.presenter.ResultLoginPresenter;
import org.visitor.Service.presenter.model.AccHsbPrsnsKoliResponse;
import org.visitor.Service.presenter.model.KalaResponse;
import org.visitor.Service.presenter.ResultKalaPresenter;
import org.visitor.Service.presenter.model.MoshtariResponse;
import org.visitor.Service.presenter.ResultGroupPresenter;
import org.visitor.Service.presenter.model.UserResponse;
import org.visitor.Tools.Databace.DataSaver;

import java.util.HashMap;


public class Api {

    private Context context;
    private Thread thread;
    private DataSaver dataSaver;

    public Api(Context context,DataSaver ds) {
        this.context = context;
        dataSaver = ds;
        PackageInfo packageInfo = null;
    }


/*    public void searchBus( final ResultUserPresenter resultSearchBusPresenter) {

        final String url = "https://www.farsishop.com/opt/api.php";
        cancelRequest();

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                HashMap<String, String> getHashMap = new HashMap<>();
                getHashMap.put(KeyConst.APP_KEY, KeyConst.appKey);
                getHashMap.put(KeyConst.APP_SECRET, KeyConst.appSecret);
                new WebServiceNetwork(context).requestWebServiceByGet(url, null, new NetworkListener() {
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
                    public void onErrorServer() {
                        resultSearchBusPresenter.onErrorServer();
                        resultSearchBusPresenter.onFinish();
                    }

                    @Override
                    public void onFinish(String result) {
                        try {
                            Gson gson = new Gson();
                            ResponseUser response = gson.fromJson(result, ResponseUser.class);
//                            if (response != null  ) {
//                                resultSearchBusPresenter.onSuccessResultSearch(response);
//                            } else
//                                resultSearchBusPresenter.noBus();
                            resultSearchBusPresenter.noBus();
                        } catch (Exception e) {


                            resultSearchBusPresenter.onErrorServer();
                        } finally {
                            resultSearchBusPresenter.onFinish();
                        }

                    }
                });

            }
        });
        thread.start();
    }
    public void searchMobileLogin(final ResultUserPresenter resultSearchBusPresenter, SendLogin sendLogin) {

        final String url = "https://www.farsishop.com/api/payamak/login.php";
        cancelRequest();

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                HashMap<String, String> getHashMap = new HashMap<>();
                getHashMap.put(KeyConst.APP_KEY, KeyConst.appKey);
                getHashMap.put(KeyConst.APP_SECRET, KeyConst.appSecret);
                new WebServiceNetwork(context).requestWebServiceByPost(url, sendLogin.toString(), new NetworkListener() {
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
                    public void onErrorServer() {
                        resultSearchBusPresenter.onErrorServer();
                        resultSearchBusPresenter.onFinish();
                    }

                    @Override
                    public void onFinish(String result) {
                        try {
                            Gson gson = new Gson();
                            ResponseUser response = gson.fromJson(result, ResponseUser.class);
                            if (response != null  &&response.getCode()>0) {
                                resultSearchBusPresenter.onSuccessResultSearch(response);
                            } else
                                resultSearchBusPresenter.noBus();
                            resultSearchBusPresenter.noBus();
                        } catch (Exception e) {


                            resultSearchBusPresenter.onErrorServer();
                        } finally {
                            resultSearchBusPresenter.onFinish();
                        }

                    }
                });

            }
        });
        thread.start();
    }
    public void searchCodeLogin(final ResultUserPresenter resultSearchBusPresenter, SendLogin sendLogin) {

        final String url = "https://www.farsishop.com/api/payamak/login.php";
        cancelRequest();

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                HashMap<String, String> getHashMap = new HashMap<>();
                getHashMap.put(KeyConst.APP_KEY, KeyConst.appKey);
                getHashMap.put(KeyConst.APP_SECRET, KeyConst.appSecret);
                new WebServiceNetwork(context).requestWebServiceByPost(url, sendLogin.toString(), new NetworkListener() {
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
                    public void onErrorServer() {
                        resultSearchBusPresenter.onErrorServer();
                        resultSearchBusPresenter.onFinish();
                    }

                    @Override
                    public void onFinish(String result) {
                        try {
                            Gson gson = new Gson();
                            ResponseUser response = gson.fromJson(result, ResponseUser.class);
                            if (response != null &&response.getCode()>0&& response.getResult().getStatus()>0) {
                                resultSearchBusPresenter.onSuccessResultSearch(response);
                            } else
                                resultSearchBusPresenter.noBus();
                            resultSearchBusPresenter.noBus();
                        } catch (Exception e) {


                            resultSearchBusPresenter.onErrorServer();
                        } finally {
                            resultSearchBusPresenter.onFinish();
                        }

                    }
                });

            }
        });
        thread.start();
    }
    public void registerGroupSms(final ResultGroupPresenter resultSearchBusPresenter, SendLogin sendLogin) {

        final String url = "https://www.farsishop.com/api/payamak/group.php";
        cancelRequest();

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                HashMap<String, String> getHashMap = new HashMap<>();
                getHashMap.put(KeyConst.APP_KEY, KeyConst.appKey);
                getHashMap.put(KeyConst.APP_SECRET, KeyConst.appSecret);
                new WebServiceNetwork(context).requestWebServiceByPost(url, sendLogin.toString(), new NetworkListener() {
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
                    public void onErrorServer() {
                        resultSearchBusPresenter.onErrorServer();
                        resultSearchBusPresenter.onFinish();
                    }

                    @Override
                    public void onFinish(String result) {
                        try {
                            Gson gson = new Gson();
                            GroupResponse response = gson.fromJson(result, GroupResponse.class);
                            if (response != null&&response.getCode()>0 ) {
                                resultSearchBusPresenter.onSuccessResultSearch(response);
                            } else
                                resultSearchBusPresenter.noBus();
                            resultSearchBusPresenter.noBus();
                        } catch (Exception e) {


                            resultSearchBusPresenter.onErrorServer();
                        } finally {
                            resultSearchBusPresenter.onFinish();
                        }

                    }
                });

            }
        });
        thread.start();
    }
    public void getGroupMessage(final ResultGroupPresenter resultSearchBusPresenter, SendLogin sendLogin) {

        final String url = "https://www.farsishop.com/api/payamak/group.php";
        cancelRequest();

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                HashMap<String, String> getHashMap = new HashMap<>();
                getHashMap.put(KeyConst.APP_KEY, KeyConst.appKey);
                getHashMap.put(KeyConst.APP_SECRET, KeyConst.appSecret);
                new WebServiceNetwork(context).requestWebServiceByPost(url, sendLogin.toString(), new NetworkListener() {
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
                    public void onErrorServer() {
                        resultSearchBusPresenter.onErrorServer();
                        resultSearchBusPresenter.onFinish();
                    }

                    @Override
                    public void onFinish(String result) {
                        try {
                            Gson gson = new Gson();
                            GroupResponse response = gson.fromJson(result, GroupResponse.class);
                            if (response != null &&response.getCode()>0 ) {
                                resultSearchBusPresenter.onSuccessResultSearch(response);
                            } else
                                resultSearchBusPresenter.noBus();
                        } catch (Exception e) {


                            resultSearchBusPresenter.onErrorServer();
                        } finally {
                            resultSearchBusPresenter.onFinish();
                        }

                    }
                });

            }
        });
        thread.start();
    }


    public void reghisterGroup(final ResultGroupPresenter resultSearchBusPresenter, SendLogin sendLogin) {

        final String url = "https://www.farsishop.com/api/payamak/group.php";
        cancelRequest();

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                HashMap<String, String> getHashMap = new HashMap<>();
                getHashMap.put(KeyConst.APP_KEY, KeyConst.appKey);
                getHashMap.put(KeyConst.APP_SECRET, KeyConst.appSecret);
                new WebServiceNetwork(context).requestWebServiceByPost(url, sendLogin.toString(), new NetworkListener() {
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
                    public void onErrorServer() {
                        resultSearchBusPresenter.onErrorServer();
                        resultSearchBusPresenter.onFinish();
                    }

                    @Override
                    public void onFinish(String result) {
                        try {
                            Gson gson = new Gson();
                            GroupResponse response = gson.fromJson(result, GroupResponse.class);
                            if (response != null  ) {
                                resultSearchBusPresenter.onSuccessResultSearch(response);
                            } else
                                resultSearchBusPresenter.noBus();
                            resultSearchBusPresenter.noBus();
                        } catch (Exception e) {


                            resultSearchBusPresenter.onErrorServer();
                        } finally {
                            resultSearchBusPresenter.onFinish();
                        }

                    }
                });

            }
        });
        thread.start();
    }
    public void updateGroup(final ResultGroupPresenter resultSearchBusPresenter, SendLogin sendLogin) {

        final String url = "https://www.farsishop.com/api/payamak/group.php";
        cancelRequest();

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                HashMap<String, String> getHashMap = new HashMap<>();
                getHashMap.put(KeyConst.APP_KEY, KeyConst.appKey);
                getHashMap.put(KeyConst.APP_SECRET, KeyConst.appSecret);
                new WebServiceNetwork(context).requestWebServiceByPost(url, sendLogin.toString(), new NetworkListener() {
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
                    public void onErrorServer() {
                        resultSearchBusPresenter.onErrorServer();
                        resultSearchBusPresenter.onFinish();
                    }

                    @Override
                    public void onFinish(String result) {
                        try {
                            Gson gson = new Gson();
                            GroupResponse response = gson.fromJson(result, GroupResponse.class);
                            if (response != null  ) {
                                resultSearchBusPresenter.onSuccessResultSearch(response);
                            } else
                                resultSearchBusPresenter.noBus();
                            resultSearchBusPresenter.noBus();
                        } catch (Exception e) {


                            resultSearchBusPresenter.onErrorServer();
                        } finally {
                            resultSearchBusPresenter.onFinish();
                        }

                    }
                });

            }
        });
        thread.start();
    }*/
    public  void login(final String username, final String password,final ResultLoginPresenter resultLoginPresenter){
       final  String url = dataSaver.getHost()+"Auth/login";
       final  String body= "{" +
               " \"username\":" +username +
               " \"password\":" +password +
               "}";
       cancelRequest();
       thread = new Thread(new Runnable() {
           @Override
           public void run() {
              new WebServiceNetwork(context).requestWebServiceByPost(url, body, new NetworkListener() {
                  @Override
                  public void onStart() {
                      resultLoginPresenter.onStart();
                  }

                  @Override
                  public void onErrorInternetConnection() {
                      resultLoginPresenter.onErrorInternetConnection();
                  }

                  @Override
                  public void onErrorServer(String e) {
                      resultLoginPresenter.onError(e);
                  }

                  @Override
                  public void onFinish(String result) {
                      UserResponse userResponse = new Gson().fromJson(result,UserResponse.class);
                      resultLoginPresenter.onSuccessResultSearch(userResponse);
                  }
              });
           }
       });
       thread.start();
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
                new WebServiceNetwork(context).requestWebServiceByGet(url, null, new NetworkListener() {
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
                            KalaResponse response = gson.fromJson(result, KalaResponse.class);
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
    public void getMoshtaris(final ResultGroupPresenter resultSearchBusPresenter) {
        final String url =dataSaver.getHost()+"moshtaris";
        cancelRequest();

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                HashMap<String, String> getHashMap = new HashMap<>();
                getHashMap.put(KeyConst.APP_KEY, KeyConst.appKey);
                getHashMap.put(KeyConst.APP_SECRET, KeyConst.appSecret);
                new WebServiceNetwork(context).requestWebServiceByGet(url,getHashMap, new NetworkListener() {
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
                new WebServiceNetwork(context).requestWebServiceByGet(url, null, new NetworkListener() {
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
                new WebServiceNetwork(context).requestWebServiceByGet(url, null, new NetworkListener() {
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
                        resultSearchBusPresenter.onErrorServer(e.toString());
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
