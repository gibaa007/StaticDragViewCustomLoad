package com.g7.gibaa007.nector;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by newagesmb on 11/7/17.
 */

public class TileModel_copy implements Parcelable {


    public static final Creator<TileModel_copy> CREATOR = new Creator<TileModel_copy>() {
        @Override
        public TileModel_copy createFromParcel(Parcel in) {
            return new TileModel_copy(in);
        }

        @Override
        public TileModel_copy[] newArray(int size) {
            return new TileModel_copy[size];
        }
    };
    String color = "#fff";
    String title = "";
    String content = "";
    String image = "";
    int dLeft = 0;
    int tag = 0;

    public TileModel_copy(String color, String title, String content, int tag, int dLeft, String image) {
        this.color = color;
        this.title = title;
        this.content = content;
        this.image = image;
        this.dLeft = dLeft;
        this.tag = tag;
    }

    protected TileModel_copy(Parcel in) {
        color = in.readString();
        title = in.readString();
        content = in.readString();
        image = in.readString();
        dLeft = in.readInt();
        tag = in.readInt();
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getdLeft() {
        return dLeft;
    }

    public void setdLeft(int dLeft) {
        this.dLeft = dLeft;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(color);
        out.writeString(title);
        out.writeString(content);
        out.writeString(image);
        out.writeInt(dLeft);
        out.writeInt(tag);
        out.writeInt(tag);
    }

}
