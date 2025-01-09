package org.visitor.Tools.Databace;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

import org.visitor.BaseConfig;
import org.visitor.Service.presenter.model.ConfigResponse;
import org.visitor.Service.presenter.model.UserConfig;
import org.visitor.Service.presenter.model.UserResponse;
import org.visitor.userModel.ResponseUser;

import java.util.concurrent.ExecutionException;


public class DataSaver {
    private final Context context;
    private final SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String USER_JSON = "userJson";
    public static final String HOST_ADDRESS = "hostAddress";
    public static final String CONFIG = "initialConfig";
    public static  final  String SnackbarDuration = "SnackbarDuration";

    public DataSaver(Context context) {
        this.context = context;
        sharedpreferences = PreferenceManager.getDefaultSharedPreferences(this.context);
    }
    public int getDuraiton(){
        return sharedpreferences.getInt(SnackbarDuration,10);
    }
    public void setDuration(int Duration){
        SharedPreferences sharedpreferences = PreferenceManager.getDefaultSharedPreferences(this.context);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putInt(DataSaver.SnackbarDuration,Duration);
        editor.apply();
    }
    public boolean hasConfig(){
        SharedPreferences sharedpreferences = PreferenceManager.getDefaultSharedPreferences(this.context);
        return sharedpreferences.contains(CONFIG);
    }
    public String getHost(){
        SharedPreferences sharedpreferences = PreferenceManager.getDefaultSharedPreferences(this.context);
        String hostAddress = sharedpreferences.getString(HOST_ADDRESS, "");
        if(hostAddress.isEmpty()){
            setHost(BaseConfig.Base_Host);
            return BaseConfig.BASE_URL_MASTER;
        }
        return "http://"+hostAddress+"/api/";
    }
    public void setHost(String host){
        String ipPattern  = "^((25[0-5]|(2[0-4]|1\\d|[1-9]|)\\d)\\.?\\b){4}$";
        SharedPreferences sharedpreferences = PreferenceManager.getDefaultSharedPreferences(this.context);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(HOST_ADDRESS, host);
        editor.apply();
    }
    public ConfigResponse getConfig(){
        SharedPreferences sharedPreferences =  PreferenceManager.getDefaultSharedPreferences(this.context);
        String configS = sharedPreferences.getString(CONFIG,"");
        return new Gson().fromJson(configS, ConfigResponse.class);
    }
    public void setConfig(ConfigResponse config){
        SharedPreferences sharedPreferences =  PreferenceManager.getDefaultSharedPreferences(this.context);
        SharedPreferences.Editor  editor = sharedPreferences.edit();
        String configString = new Gson().toJson(config);
        editor.putString(CONFIG,configString);
        editor.apply();
    }
    public Boolean hasLogin() {
        SharedPreferences sharedpreferences =  PreferenceManager.getDefaultSharedPreferences(this.context);
        return sharedpreferences.contains(USER_JSON);
    }
    public void setLogin(UserResponse userResponse) {
        SharedPreferences sharedpreferences =  PreferenceManager.getDefaultSharedPreferences(this.context);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        String userJson  = new Gson().toJson(userResponse.UserObject);
        editor.putString(USER_JSON, userJson);
        editor.apply();
    }
}
