package org.visitor.Service.presenter.model;


import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "message_group", primaryKeys = {"groupSmsId"})
public class MessageGroup implements Parcelable
{
    @NonNull
    @SerializedName("groupSmsId")
    @Expose
    private String groupSmsId;
    @SerializedName("groupId")
    @Expose
    private String groupId;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("scheduleStart")
    @Expose
    private String scheduleStart;
    @SerializedName("schedulePeriod")
    @Expose
    private String schedulePeriod;
    @SerializedName("scheduleDailyStart")
    @Expose
    private String scheduleDailyStart;
    @SerializedName("scheduleDailyEnd")
    @Expose
    private String scheduleDailyEnd;
    private String sendServer="1";

    @Ignore
    public MessageGroup(@NonNull String groupSmsId, String groupId, String text, String date, String scheduleStart, String schedulePeriod, String scheduleDailyStart, String scheduleDailyEnd) {
        this.groupSmsId = groupSmsId;
        this.groupId = groupId;
        this.text = text;
        this.date = date;
        this.scheduleStart = scheduleStart;
        this.schedulePeriod = schedulePeriod;
        this.scheduleDailyStart = scheduleDailyStart;
        this.scheduleDailyEnd = scheduleDailyEnd;
    }

    public MessageGroup(@NonNull String groupSmsId, String groupId) {
        this.groupSmsId = groupSmsId;
        this.groupId = groupId;
    }

    public final static Creator<MessageGroup> CREATOR = new Creator<MessageGroup>() {


        @SuppressWarnings({
                "unchecked"
        })
        public MessageGroup createFromParcel(android.os.Parcel in) {
            return new MessageGroup(in);
        }

        public MessageGroup[] newArray(int size) {
            return (new MessageGroup[size]);
        }

    }
            ;

    protected MessageGroup(android.os.Parcel in) {
        this.groupSmsId = ((String) in.readValue((String.class.getClassLoader())));
        this.groupId = ((String) in.readValue((String.class.getClassLoader())));
        this.text = ((String) in.readValue((String.class.getClassLoader())));
        this.date = ((String) in.readValue((String.class.getClassLoader())));
        this.scheduleStart = ((String) in.readValue((String.class.getClassLoader())));
        this.schedulePeriod = ((String) in.readValue((String.class.getClassLoader())));
        this.scheduleDailyStart = ((String) in.readValue((String.class.getClassLoader())));
        this.scheduleDailyEnd = ((String) in.readValue((String.class.getClassLoader())));
        this.sendServer = ((String) in.readValue((String.class.getClassLoader())));
    }



    public String getGroupSmsId() {
        return groupSmsId;
    }

    public void setGroupSmsId(String groupSmsId) {
        this.groupSmsId = groupSmsId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getScheduleStart() {
        return scheduleStart;
    }

    public void setScheduleStart(String scheduleStart) {
        this.scheduleStart = scheduleStart;
    }

    public String getSchedulePeriod() {
        return schedulePeriod;
    }

    public void setSchedulePeriod(String schedulePeriod) {
        this.schedulePeriod = schedulePeriod;
    }

    public String getScheduleDailyStart() {
        return scheduleDailyStart;
    }

    public void setScheduleDailyStart(String scheduleDailyStart) {
        this.scheduleDailyStart = scheduleDailyStart;
    }

    public String getScheduleDailyEnd() {
        return scheduleDailyEnd;
    }

    public void setScheduleDailyEnd(String scheduleDailyEnd) {
        this.scheduleDailyEnd = scheduleDailyEnd;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(groupSmsId);
        dest.writeValue(groupId);
        dest.writeValue(text);
        dest.writeValue(date);
        dest.writeValue(scheduleStart);
        dest.writeValue(schedulePeriod);
        dest.writeValue(scheduleDailyStart);
        dest.writeValue(scheduleDailyEnd);
        dest.writeValue(sendServer);
    }

    public int describeContents() {
        return 0;
    }

    public String getSendServer() {
        return sendServer;
    }

    public void setSendServer(String sendServer) {
        this.sendServer = sendServer;
    }
}
