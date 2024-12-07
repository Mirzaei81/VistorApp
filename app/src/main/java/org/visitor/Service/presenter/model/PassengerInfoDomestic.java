package org.visitor.Service.presenter.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;



@Entity(tableName = "user_info", primaryKeys = {"codeMeli", "passportNumber"})
public class PassengerInfoDomestic implements Parcelable {
    @NonNull
    @SerializedName("code")
    private String codeMeli;
    @NonNull
    @SerializedName("passport_number")
    private String passportNumber;
    // 1 == iranian   and   2 == foriegn
    @SerializedName("meliat")
    private int meliat;
    @SerializedName("gender")
    private int gender;
    @SerializedName("englishFirstName")
    private String englishFirstName;
    @SerializedName("englishLastName")
    private String englishLastName;
    // adult infant child
    @SerializedName("passengerType")
    private int passengerType;
    //  IRN
    @SerializedName("nationalitycode")
    private String nationalityCode;



    @SerializedName("passportCountry")
    private String passportCountry;

    //  "1999-11-24"

    @SerializedName("birthday")
    private String birthday;
    @Ignore
    @SerializedName("persianFirstName")
    private String persianFirstName;
    @Ignore
    @SerializedName("persianLastName")
    private String persianLastName;
    @SerializedName("expdate")
    private String expDate;




    @Ignore
    private boolean isChoosed = false;

    public final static int GENDER_MALE = 1;
    public final static int GENDER_FEMALE = 2;
    public final static int EXPORTING_COUNTRY_IRAN = 1;
    public final static int EXPORTING_COUNTRY_FOREIGN = 2;

    public PassengerInfoDomestic() {

        meliat = EXPORTING_COUNTRY_IRAN;
        nationalityCode = "IRN";

        passportNumber = "";
        expDate = "";
    //    gender = Integer.parseInt(PassengerInfo.MALE);

    }

    protected PassengerInfoDomestic(Parcel in) {
        codeMeli = in.readString();
        passportNumber = in.readString();
        meliat = in.readInt();
        gender = in.readInt();
        englishFirstName = in.readString();
        englishLastName = in.readString();
        passengerType = in.readInt();
        nationalityCode = in.readString();
        passportCountry=in.readString();
        birthday = in.readString();
        persianFirstName = in.readString();
        persianLastName = in.readString();
        expDate = in.readString();

        isChoosed = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(codeMeli);
        dest.writeString(passportNumber);
        dest.writeInt(meliat);
        dest.writeInt(gender);
        dest.writeString(englishFirstName);
        dest.writeString(englishLastName);
        dest.writeInt(passengerType);
        dest.writeString(nationalityCode);
        dest.writeString(passportCountry);
        dest.writeString(birthday);
        dest.writeString(persianFirstName);
        dest.writeString(persianLastName);
        dest.writeString(expDate);

        dest.writeByte((byte) (isChoosed ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PassengerInfoDomestic> CREATOR = new Creator<PassengerInfoDomestic>() {
        @Override
        public PassengerInfoDomestic createFromParcel(Parcel in) {
            return new PassengerInfoDomestic(in);
        }

        @Override
        public PassengerInfoDomestic[] newArray(int size) {
            return new PassengerInfoDomestic[size];
        }
    };

    public void  setPassengerInfoDomestic(@NonNull String codeMeli, @NonNull String passportNumber, int meliat, int gender,
                                          String englishFirstName, String englishLastName, int passengerType, String nationalityCode,String passportCountry, String birthday,String birthdayPersian,
                                          String persianFirstName, String persianLastName, String expDate, boolean isChoosed) {
        this.codeMeli = codeMeli;
        this.passportNumber = passportNumber;
        this.meliat = meliat;
        this.gender = gender;
        this.englishFirstName = englishFirstName;
        this.englishLastName = englishLastName;
        this.passengerType = passengerType;
        this.nationalityCode = nationalityCode;
        this.passportCountry=passportCountry;
        this.birthday = birthday;
        this.persianFirstName = persianFirstName;
        this.persianLastName = persianLastName;
        this.expDate = expDate;

        this.isChoosed = isChoosed;
    }
    public String getPassportCountry() {
        return passportCountry;
    }

    public void setPassportCountry(String passportCountry) {
        this.passportCountry = passportCountry;
    }



    @NonNull
    public String getCodeMeli() {
        return codeMeli;
    }

    public void setCodeMeli(@NonNull String codeMeli) {
        this.codeMeli = codeMeli;
    }

    @NonNull
    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(@NonNull String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public int getMeliat() {
        return meliat;
    }

    public void setMeliat(int meliat) {
        this.meliat = meliat;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getEnglishFirstName() {
        return englishFirstName;
    }

    public void setEnglishFirstName(String englishFirstName) {
        this.englishFirstName = englishFirstName;
    }

    public String getEnglishLastName() {
        return englishLastName;
    }

    public void setEnglishLastName(String englishLastName) {
        this.englishLastName = englishLastName;
    }

    public int getPassengerType() {
        return passengerType;
    }

    public void setPassengerType(int passengerType) {
        this.passengerType = passengerType;
    }

    public String getNationalityCode() {
        return nationalityCode;
    }

    public void setNationalityCode(String nationalityCode) {
        this.nationalityCode = nationalityCode;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPersianFirstName() {
        return persianFirstName;
    }

    public void setPersianFirstName(String persianFirstName) {
        this.persianFirstName = persianFirstName;
    }

    public String getPersianLastName() {
        return persianLastName;
    }

    public void setPersianLastName(String persianLastName) {
        this.persianLastName = persianLastName;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }



    public boolean isChoosed() {
        return isChoosed;
    }

    public void setChoosed(boolean choosed) {
        isChoosed = choosed;
    }

    public static Creator<PassengerInfoDomestic> getCREATOR() {
        return CREATOR;
    }
    /*

    protected PassengerInfoDomestic(Parcel in) {
        codeMeli = in.readString();
        birthday = in.readString();
        meliat = in.readInt();
        gender = in.readInt();
        englishFirstName = in.readString();
        englishLastName = in.readString();
        persianFirstName = in.readString();
        persianLastName = in.readString();
        expDate = in.readString();
        passportNumber = in.readString();
        nationalityCode = in.readString();
        passengerType = in.readInt();
        isChoosed = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(codeMeli);
        dest.writeString(birthday);
        dest.writeInt(meliat);
        dest.writeInt(gender);
        dest.writeString(englishFirstName);
        dest.writeString(englishLastName);
        dest.writeString(persianFirstName);
        dest.writeString(persianLastName);
        dest.writeString(expDate);
        dest.writeString(passportNumber);
        dest.writeString(nationalityCode);
        dest.writeInt(passengerType);
        dest.writeByte((byte) (isChoosed ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PassengerInfoDomestic> CREATOR = new Creator<PassengerInfoDomestic>() {
        @Override
        public PassengerInfoDomestic createFromParcel(Parcel in) {
            return new PassengerInfoDomestic(in);
        }

        @Override
        public PassengerInfoDomestic[] newArray(int size) {
            return new PassengerInfoDomestic[size];
        }
    };

    public boolean isChoosed() {
        return isChoosed;
    }

    public void setChoosed(boolean choosed) {
        isChoosed = choosed;
    }

    public void setCodeMeli(String code) {
        this.codeMeli = code;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setMeliat(int meliat) {
        this.meliat = meliat;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public void setEnglishFirstName(String englishFirstName) {
        this.englishFirstName = englishFirstName;
    }

    public void setEnglishLastName(String englishLastName) {
        this.englishLastName = englishLastName;
    }

    public void setPersianFirstName(String persianFirstName) {
        this.persianFirstName = persianFirstName;
    }

    public void setPersianLastName(String persianLastName) {
        this.persianLastName = persianLastName;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public void setNationalityCode(String nationalityCode) {
        this.nationalityCode = nationalityCode;
    }

    public void setPassengerType(int passengerType) {
        this.passengerType = passengerType;
    }

    public String getCodeMeli() {
        return codeMeli;
    }

    public String getBirthday() {
        return birthday;
    }

    public int getMeliat() {
        return meliat;
    }

    public int getGender() {
        return gender;
    }

    public String getEnglishFirstName() {
        return englishFirstName;
    }

    public String getEnglishLastName() {
        return englishLastName;
    }

    public String getPersianFirstName() {
        return persianFirstName;
    }

    public String getPersianLastName() {
        return persianLastName;
    }

    public String getExpDate() {
        return expDate;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public String getNationalityCode() {
        return nationalityCode;
    }

    public int getPassengerType() {
        return passengerType;
    }

     */

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
