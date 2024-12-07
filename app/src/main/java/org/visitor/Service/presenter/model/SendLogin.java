package org.visitor.Service.presenter.model;

import android.os.Parcelable;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.visitor.KeyConst;

import java.util.List;

public class SendLogin implements Parcelable
{

    @SerializedName("key")
    @Expose
    private String key;
    @SerializedName("step")
    @Expose
    private String step;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("verificationCode")
    @Expose
    private String verificationCode;
    @SerializedName("token")
    @Expose
    private String token;

    @SerializedName("group")
    @Expose
    private String group;

    @SerializedName("groupId")
    @Expose
    private String groupId;

    @SerializedName("members")
    @Expose
    private List<String> members = null;

    @SerializedName("text")
    @Expose
    private String text;

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("scheduleStart")
    private String scheduleStart;

    @SerializedName("schedulePeriod")
    private String schedulePeriod;

    @SerializedName("scheduleDailyStart")
    private String scheduleDailyStart;

    @SerializedName("scheduleDailyEnd")
    private String scheduleDailyEnd;

    public final static Creator<SendLogin> CREATOR = new Creator<SendLogin>() {


        @SuppressWarnings({
                "unchecked"
        })
        public SendLogin createFromParcel(android.os.Parcel in) {
            return new SendLogin(in);
        }

        public SendLogin[] newArray(int size) {
            return (new SendLogin[size]);
        }

    }
            ;

    protected SendLogin(android.os.Parcel in) {
        this.key = ((String) in.readValue((String.class.getClassLoader())));
        this.step = ((String) in.readValue((String.class.getClassLoader())));
        this.mobile = ((String) in.readValue((String.class.getClassLoader())));
        this.verificationCode = ((String) in.readValue((String.class.getClassLoader())));
        this.token = ((String) in.readValue((String.class.getClassLoader())));
        this.group = ((String) in.readValue((String.class.getClassLoader())));
        this.groupId = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.members, (java.lang.String.class.getClassLoader()));
        this.text= ((String) in.readValue((String.class.getClassLoader())));
        this.date= ((String) in.readValue((String.class.getClassLoader())));
        this.scheduleStart= ((String) in.readValue((String.class.getClassLoader())));
        this.schedulePeriod= ((String) in.readValue((String.class.getClassLoader())));
        this.scheduleDailyStart= ((String) in.readValue((String.class.getClassLoader())));
        this.scheduleDailyEnd= ((String) in.readValue((String.class.getClassLoader())));
    }

    public SendLogin() {
        this.key = KeyConst.key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public String getToken() {
        return token;
    }

    public String getGroup() {
        return group;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setScheduleStart(String scheduleStart) {
        this.scheduleStart = scheduleStart;
    }

    public void setSchedulePeriod(String schedulePeriod) {
        this.schedulePeriod = schedulePeriod;
    }

    public void setScheduleDailyStart(String scheduleDailyStart) {
        this.scheduleDailyStart = scheduleDailyStart;
    }

    public void setScheduleDailyEnd(String scheduleDailyEnd) {
        this.scheduleDailyEnd = scheduleDailyEnd;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(key);
        dest.writeValue(step);
        dest.writeValue(mobile);
        dest.writeValue(verificationCode);
        dest.writeValue(token);
        dest.writeValue(group);
        dest.writeValue(groupId);
        dest.writeList(members);
        dest.writeValue(text);
        dest.writeValue(date);
        dest.writeValue(scheduleStart);
        dest.writeValue(schedulePeriod);
        dest.writeValue(scheduleDailyStart);
        dest.writeValue(scheduleDailyEnd);
    }

    public int describeContents() {
        return 0;
    }

    @Override
    public String toString() {
        try {
            Exclude ex = new Exclude();
            Gson gson = new GsonBuilder().addDeserializationExclusionStrategy(ex).addSerializationExclusionStrategy(ex).create();
            String jsonString = gson.toJson(this);
            return jsonString;
        } catch (Exception e) {
            e.getMessage();
        }
        return "";
    }

    //-----------------------------------------------
    class Exclude implements ExclusionStrategy {

        @Override
        public boolean shouldSkipClass(Class<?> arg0) {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public boolean shouldSkipField(FieldAttributes field) {
            SerializedName ns = field.getAnnotation(SerializedName.class);
            return ns == null;
        }
    }

}
