package org.visitor;


import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


import org.visitor.userModel.ResultUser;

import java.util.ArrayList;
import java.util.List;

public class ResponseUser implements Parcelable
{

    @SerializedName("code")
    private Integer code;

    @SerializedName("data")
    private List<Datum> data =  new ArrayList<>();

    @SerializedName("result")
    private ResultUser result;
    public final static Creator<ResponseUser> CREATOR = new Creator<ResponseUser>() {


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
        in.readList(this.data, (Datum.class.getClassLoader()));
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

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }
    public ResultUser getResult() {
        return result;
    }

    public void setResult(ResultUser result) {
        this.result = result;
    }
    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(code);
        dest.writeList(data);
        dest.writeValue(result);
    }

    public int describeContents() {
        return 0;
    }
}
