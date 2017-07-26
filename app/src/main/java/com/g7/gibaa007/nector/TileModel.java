package com.g7.gibaa007.nector;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by newagesmb on 11/7/17.
 */
public class TileModel implements Parcelable {


    String color = "#fff";
    String title = "";
    ArrayList<String> content = new ArrayList<>();
    String image = "";
    int dLeft = 0;
    int tag = 0;


    public TileModel(String color, String title, ArrayList<String> content, int tag, int dLeft,String image ) {
        this.color = color;
        this.title = title;
        this.content = content;
        this.image = image;
        this.dLeft = dLeft;
        this.tag = tag;
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

    public ArrayList<String> getContent() {
        return content;
    }

    public void setContent(ArrayList<String> content) {
        this.content = content;
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

    protected TileModel(Parcel in) {
        color = in.readString();
        title = in.readString();
        if (in.readByte() == 0x01) {
            content = new ArrayList<String>();
            in.readList(content, String.class.getClassLoader());
        } else {
            content = null;
        }
        image = in.readString();
        dLeft = in.readInt();
        tag = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(color);
        dest.writeString(title);
        if (content == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(content);
        }
        dest.writeString(image);
        dest.writeInt(dLeft);
        dest.writeInt(tag);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<TileModel> CREATOR = new Parcelable.Creator<TileModel>() {
        @Override
        public TileModel createFromParcel(Parcel in) {
            return new TileModel(in);
        }

        @Override
        public TileModel[] newArray(int size) {
            return new TileModel[size];
        }
    };
}