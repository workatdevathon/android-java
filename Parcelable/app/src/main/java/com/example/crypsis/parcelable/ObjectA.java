package com.example.crypsis.parcelable;

import android.os.Parcel;
import android.os.Parcelable;


public class ObjectA implements Parcelable {

    private String strValue;
    private Integer intValue;


    public ObjectA() {  };


    public ObjectA(Parcel in) {
        readFromParcel(in);
    }


    public String getStrValue() {
        return strValue;
    }


    public void setStrValue(String strValue) {
        this.strValue = strValue;
    }


    public Integer getIntValue() {
        return intValue;
    }

    public void setIntValue(Integer intValue) {
        this.intValue = intValue;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(strValue);
        dest.writeInt(intValue);
    }


    private void readFromParcel(Parcel in) {

        strValue = in.readString();
        intValue = in.readInt();
    }


    public static final Parcelable.Creator CREATOR =
            new Parcelable.Creator() {
                public ObjectA createFromParcel(Parcel in) {
                    return new ObjectA(in);
                }

                public ObjectA[] newArray(int size) {
                    return new ObjectA[size];
                }
            };

}