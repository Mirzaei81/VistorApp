package org.visitor.Tools.Databace;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

import org.visitor.BaseConfig;
import org.visitor.Service.presenter.model.UserConfig;
import org.visitor.Service.presenter.model.UserResponse;
import org.visitor.userModel.ResponseUser;

import java.util.concurrent.ExecutionException;


public class DataSaver {
    private Context context;
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String USER_JSON = "userJson";
    public static final String HOST_ADDRESS = "hostAddress";
    public static final String CONFIG = "initialConfig";

    public DataSaver(Context context) {
        this.context = context;
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
    public String getConfig(){
        SharedPreferences sharedPreferences =  PreferenceManager.getDefaultSharedPreferences(this.context);
        return sharedPreferences.getString(CONFIG,"");
    }
    public void setConfig(String DbName){
        SharedPreferences sharedPreferences =  PreferenceManager.getDefaultSharedPreferences(this.context);
        SharedPreferences.Editor  editor = sharedPreferences.edit();
        editor.putString(CONFIG,DbName);
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
