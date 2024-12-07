package org.visitor.userModel;

import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ResponseUser implements Parcelable {
    @SerializedName("code")
    private Integer code;
    @SerializedName("result")
    private ResultUser result;
    public final static Parcelable.Creator<ResponseUser> CREATOR = new Parcelable.Creator<ResponseUser>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ResponseUser createFromParcel(android.os.Parcel in) {
            return new ResponseUser(in);
        }

        public ResponseUser[] newArray(int size) {
            return (new ResponseUser[size]);
        }

    }
            ;

    protected ResponseUser(android.os.Parcel in) {
        this.code = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.result = ((ResultUser) in.readValue((ResultUser.class.getClassLoader())));
    }

    public ResponseUser() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public ResultUser getResult() {
        return result;
    }

    public void setResult(ResultUser result) {
        this.result = result;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(code);
        dest.writeValue(result);
    }

    public int describeContents() {
        return 0;
    }
}
