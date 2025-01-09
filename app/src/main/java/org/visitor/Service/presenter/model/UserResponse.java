package org.visitor.Service.presenter.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class UserResponse implements Serializable
{

    @SerializedName("user")
    public User UserObject;
    public ArrayList<UserConfig> serverDetail;
    @SerializedName("markazes")
    public ArrayList<Markaz> markazs;
}
