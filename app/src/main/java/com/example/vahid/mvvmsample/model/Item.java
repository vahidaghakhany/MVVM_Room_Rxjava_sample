package com.example.vahid.mvvmsample.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by vahid on 1/27/18.
 */

public class Item implements Parcelable {
   int x;
   String str;
   boolean bool;

    public Item(int x, String str, boolean bool) {
        this.x = x;
        this.str = str;
        this.bool = bool;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public boolean isBool() {
        return bool;
    }

    public void setBool(boolean bool) {
        this.bool = bool;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.x);
        dest.writeString(this.str);
        dest.writeByte(this.bool ? (byte) 1 : (byte) 0);
    }

    protected Item(Parcel in) {
        this.x = in.readInt();
        this.str = in.readString();
        this.bool = in.readByte() != 0;
    }

    public static final Parcelable.Creator<Item> CREATOR = new Parcelable.Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel source) {
            return new Item(source);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };
}
