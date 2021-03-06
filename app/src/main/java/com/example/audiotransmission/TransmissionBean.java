package com.example.audiotransmission;

import android.os.Parcel;
import android.os.Parcelable;

public class TransmissionBean implements Parcelable {
    public int contentType;
    public String content;

    protected TransmissionBean(Parcel in) {
        contentType = in.readInt();
        content = in.readString();
    }

    public static final Creator<TransmissionBean> CREATOR = new Creator<TransmissionBean>() {
        @Override
        public TransmissionBean createFromParcel(Parcel in) {
            return new TransmissionBean(in);
        }

        @Override
        public TransmissionBean[] newArray(int size) {
            return new TransmissionBean[size];
        }
    };

    public TransmissionBean() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(contentType);
        dest.writeString(content);
    }
}
