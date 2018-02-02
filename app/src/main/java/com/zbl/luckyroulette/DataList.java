package com.zbl.luckyroulette;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by benlongzhu on 2018/2/1.
 *
 * 轮上上的数据
 */

public class DataList implements Parcelable{

    public static final long serialVersionUID = 3453521L;

    public DataList(String tv, int img) {
        this.tv = tv;
        this.img = img;
    }

    public String getTv() {
        return tv;
    }

    public void setTv(String tv) {
        this.tv = tv;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    String tv;
    int img;

    public DataList(){
        super();
    }

    protected DataList(Parcel in) {
        tv = in.readString();
        img = in.readInt();
    }

    public static final Creator<DataList> CREATOR = new Creator<DataList>() {
        @Override
        public DataList createFromParcel(Parcel in) {
            return new DataList(in);
        }

        @Override
        public DataList[] newArray(int size) {
            return new DataList[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(tv);
        dest.writeInt(img);
    }
}
