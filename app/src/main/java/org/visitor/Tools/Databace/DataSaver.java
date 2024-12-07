package org.visitor.Tools.Databace;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import org.visitor.BaseConfig;
import org.visitor.Service.presenter.model.UserResponse;
import org.visitor.userModel.ResponseUser;


public class DataSaver {
    private Context context;
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String USER_JSON = "userJson";
    public static final String HOST_ADDRESS = "hostAddress";
    public static final String CONFIG = "initialConfig";

    public DataSaver(Context context) {
        this.context = context;
    }
    public String getHost(){
        SharedPreferences sharedpreferences = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String hostAddress = sharedpreferences.getString(HOST_ADDRESS, "");
        if(hostAddress.isEmpty()){
            setHost(BaseConfig.Base_Host);
        }
        return "http:/"+hostAddress+"+/api/";
    }
    public void setHost(String host){
        String ipPattern  = "^((25[0-5]|(2[0-4]|1\\d|[1-9]|)\\d)\\.?\\b){4}$";
        if(host.matches(ipPattern)){
            SharedPreferences sharedpreferences = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString(HOST_ADDRESS, "");
            editor.apply();
        }else{
            throw new java.lang.RuntimeException("IP Address is invalid");
        }
    }
    public void setConfig(Config){
        SharedPreferences sharedPreferences = context.getSharedPreferences(CONFIG,Context.MODE_PRIVATE);
        SharedPreferences.Editor  editor = sharedPreferences.edit();
        editor.putString()
    }
    public Boolean hasLogin() {
        try {
            SharedPreferences sharedpreferences = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
            String userJson = sharedpreferences.getString(USER_JSON, "");
            return  userJson.isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    public void setLogin(UserResponse userResponse) {
        SharedPreferences sharedpreferences = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        String userJson  = new Gson().toJson(userResponse);
        editor.putString(USER_JSON, userJson);
        editor.apply();
    }
}
