package org.visitor.Service.presenter.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;


public class ContactModel  implements Parcelable {
    public String id;
    public String name;
    @NonNull
    public String mobileNumber;
    public ContactModel(){

    }
    protected ContactModel(Parcel in) {
        id = in.readString();
        name = in.readString();
        mobileNumber = in.readString();
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(mobileNumber);
    }
    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ContactModel> CREATOR = new Creator<ContactModel>() {
        @Override
        public ContactModel createFromParcel(Parcel in) {
            return new ContactModel(in);
        }

        @Override
        public ContactModel[] newArray(int size) {
            return new ContactModel[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}
