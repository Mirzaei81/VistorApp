package org.visitor.userModel;


import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ResultUser implements Parcelable
{

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("info")
    @Expose
    private InfoUser info;
    public final static Creator<ResultUser> CREATOR = new Creator<ResultUser>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ResultUser createFromParcel(android.os.Parcel in) {
            return new ResultUser(in);
        }

        public ResultUser[] newArray(int size) {
            return (new ResultUser[size]);
        }

    }
            ;

    protected ResultUser(android.os.Parcel in) {
        this.status = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.msg = ((String) in.readValue((String.class.getClassLoader())));
        this.info = ((InfoUser) in.readValue((InfoUser.class.getClassLoader())));
    }

    public ResultUser() {
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public InfoUser getInfo() {
        return info;
    }

    public void setInfo(InfoUser info) {
        this.info = info;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeValue(msg);
        dest.writeValue(info);
    }

    public int describeContents() {
        return 0;
    }

}
