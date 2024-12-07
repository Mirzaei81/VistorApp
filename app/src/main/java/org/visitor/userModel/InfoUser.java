package org.visitor.userModel;


import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class InfoUser implements Parcelable
{

    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("id")
    @Expose
    private String id;
    public final static Creator<InfoUser> CREATOR = new Creator<InfoUser>() {


        @SuppressWarnings({
                "unchecked"
        })
        public InfoUser createFromParcel(android.os.Parcel in) {
            return new InfoUser(in);
        }

        public InfoUser[] newArray(int size) {
            return (new InfoUser[size]);
        }

    }
            ;

    protected InfoUser(android.os.Parcel in) {
        this.mobile = ((String) in.readValue((String.class.getClassLoader())));
        this.token = ((String) in.readValue((String.class.getClassLoader())));
        this.id = ((String) in.readValue((String.class.getClassLoader())));
    }

    public InfoUser() {
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(mobile);
        dest.writeValue(token);
        dest.writeValue(id);
    }

    public int describeContents() {
        return 0;
    }

}
