package org.visitor;


import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.visitor.Service.presenter.ResultAnbarPresenter;
import org.visitor.Service.presenter.ResultConfigPresenter;
import org.visitor.Service.presenter.ResultFactorDetailPresenter;
import org.visitor.Service.presenter.ResultFactorPresenter;
import org.visitor.Service.presenter.ResultLoginPresenter;
import org.visitor.Service.presenter.ResultMoshtariPresenter;
import org.visitor.Service.presenter.ResultUserNamePreasenter;
import org.visitor.Service.presenter.model.AccHsbPrsnsKoliResponse;
import org.visitor.Service.presenter.model.Anbar;
import org.visitor.Service.presenter.model.FactorDetail;
import org.visitor.Service.presenter.model.Groups;
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
        if(dataSaver.hasConfig()) {
            DbName = dataSaver.getConfig().databaseName;
        }
    }
    public void getDbName(final float year, final String Daftar, final String Company, final ResultConfigPresenter resaultConfigPresenter)
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
    public void getKalas(final int gId,final ResultKalaPresenter resultSearchBusPresenter) {
        final String url =dataSaver.getHost()+"groups/"+gId;
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
                            Type kalaList = new TypeToken<ArrayList<Kala >>(){}.getType();
                            ArrayList<Kala> response =new Gson().fromJson(result,kalaList);
                            if (response !=null &&  !response.isEmpty()  ) {
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
    public void getAnbars(final ResultAnbarPresenter resultAnbarPresenter){
        final String  url = dataSaver.getHost()+"anbar";
        new Thread(()->{
            new WebServiceNetwork(context).requestWebServiceByGet(url,DbName,null, new NetworkListener() {
                @Override
                public void onStart() {
                }
                @Override
                public void onErrorInternetConnection() {
                    resultAnbarPresenter.onErrorInternetConnection();
                }

                @Override
                public void onErrorServer(String e) {
                    resultAnbarPresenter.onErrorServer(e);
                }

                @Override
                public void onFinish(String result) {
                    try {
                        Type anbarType =new TypeToken<ArrayList<Anbar>>(){}.getType();
                        ArrayList<Anbar> response =  new Gson().fromJson(result, anbarType);
                        if (!response.isEmpty() ) {
                            resultAnbarPresenter.onFinish(response);
                        } else
                            resultAnbarPresenter.onErrorServer("404 No Moshtari Found");
                    } catch (Exception e) {
                        resultAnbarPresenter.onErrorServer(e.toString());
                    }
                }
            });
        }).start();
    }
    public void getMoshtaris(final ResultMoshtariPresenter resultSearchBusPresenter) {
        final String url =dataSaver.getHost()+"moshtaris";

        new Thread(() -> {
            HashMap<String, String> getHashMap = new HashMap<>();
            getHashMap.put(KeyConst.APP_KEY, KeyConst.appKey);
            getHashMap.put(KeyConst.APP_SECRET, KeyConst.appSecret);
            new WebServiceNetwork(context).requestWebServiceByGet(url,DbName,getHashMap, new NetworkListener() {
                @Override
                public void onStart() {
                }

                @Override
                public void onErrorInternetConnection() {
                    resultSearchBusPresenter.onErrorInternetConnection();
                }

                @Override
                public void onErrorServer(String e) {
                    resultSearchBusPresenter.onErrorServer(e);
                }

                @Override
                public void onFinish(String result) {
                    try {
                        Gson gson = new Gson();
                        MoshtariResponse response = gson.fromJson(result, MoshtariResponse.class);
                        if (response != null  ) {
                            resultSearchBusPresenter.onSuccessResultSearch(response);
                        } else
                            resultSearchBusPresenter.onErrorServer("404 No Moshtari Found");
                    } catch (Exception e) {
                        resultSearchBusPresenter.onErrorServer(e.toString());
                    }
                }
            });
        }).start();
    }
    public void getFactorDetail(int FK_NO, final ResultFactorDetailPresenter resultFactorDetailPresenter){
        final String url = dataSaver.getHost()+"Fac/"+FK_NO;
        cancelRequest();
        new Thread(()-> {
            new WebServiceNetwork(context).requestWebServiceByGet(url, DbName, null, new NetworkListener() {
                @Override
                public void onStart() {
                }

                @Override
                public void onErrorInternetConnection() {
                    resultFactorDetailPresenter.onErrorInternetConnection();
                }

                @Override
                public void onErrorServer(String e) {
                    resultFactorDetailPresenter.onErrorServer(e);
                }

                @Override
                public void onFinish(String result) {
                    Type FDetail = new TypeToken<ArrayList<FactorDetail>>(){}.getType();
                    ArrayList<FactorDetail> detail = new Gson().fromJson(result,FDetail);
                    resultFactorDetailPresenter.onSuccessResultSearch(detail);
                }
            });
        }).start();

    }
    public void getFactor(String endDate, String startDate,String codeMoshtari, final ResultFactorPresenter resultSearchBusPresenter) {
        final String url =dataSaver.getHost()+"acc_HsbPrsnsKoli?dateTo="+endDate+"&dateFrom="+startDate+"&codeM="+codeMoshtari+"&mrkaz=1&mandDate=1&kind=A";
        cancelRequest();
        thread = new Thread(() -> new WebServiceNetwork(context).requestWebServiceByGet(url,DbName, null, new NetworkListener() {
            @Override
            public void onStart() {
            }

            @Override
            public void onErrorInternetConnection() {
                resultSearchBusPresenter.onErrorInternetConnection();
            }

            @Override
            public void onErrorServer(String e) {
                resultSearchBusPresenter.onErrorServer(e);
            }

            @Override
            public void onFinish(String result) {
                try {

                    Gson gson = new Gson();
                    AccHsbPrsnsKoliResponse response = gson.fromJson(result, AccHsbPrsnsKoliResponse.class);
                    if (response != null  ) {
                        resultSearchBusPresenter.onSuccessResultSearch(response);
                    } else {
                        resultSearchBusPresenter.onErrorServer("404 No FactorFound");
                    }
                } catch (Exception e) {
                    resultSearchBusPresenter.onErrorServer(e.toString());
                }
            }
        }));
        thread.start();
    }
    public void getGroups(final ResultGroupPresenter groupPresenter){
    final String url =dataSaver.getHost()+"groups";
    cancelRequest();
        thread = new Thread(() -> new WebServiceNetwork(context).requestWebServiceByGet(url,DbName, null, new NetworkListener() {
            @Override
            public void onStart() {
            }

            @Override
            public void onErrorInternetConnection() {
                groupPresenter.onErrorInternetConnection();
            }

            @Override
            public void onErrorServer(String e) {
                groupPresenter.onErrorServer(e);
            }

            @Override
            public void onFinish(String result) {
                try {
                    Gson gson = new Gson();
                    Type groupToken = new TypeToken<ArrayList<Groups>>(){}.getType();
                    ArrayList<Groups> response = gson.fromJson(result, groupToken);
                    if (response != null) {
                        groupPresenter.onSuccessResultSearch(response);
                    } else
                         groupPresenter.onErrorServer("no Group found");
                } catch (Exception e) {
                    groupPresenter.onErrorServer(e.toString());
                }

            }
        }));
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
                    }

                    @Override
                    public void onErrorInternetConnection() {
                        resultSearchBusPresenter.onErrorInternetConnection();
                    }

                    @Override
                    public void onErrorServer(String e) {
                        resultSearchBusPresenter.onErrorServer(e);
                    }

                    @Override
                    public void onFinish(String result) {
                        try {
                            Gson gson = new Gson();
                            AccHsbPrsnsKoliResponse response = gson.fromJson(result, AccHsbPrsnsKoliResponse.class);
                            if (response != null  ) {
                                resultSearchBusPresenter.onSuccessResultSearch(response);
                            }
                        } catch (Exception e) {
                            resultSearchBusPresenter.onErrorServer(e.toString());
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