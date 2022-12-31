package com.example.palmhospital.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class Doctor implements Parcelable {
    private int did;
    private String dname;
    private String dlevel;
    private String dinfo;
    private String ddepartid;
    private int sex;
    private String ddetail;

    public Doctor(){}

    public Doctor(int did, String dname, String dlevel, String dinfo, String ddepartid, int sex, String ddetail) {
        this.did = did;
        this.dname = dname;
        this.dlevel = dlevel;
        this.dinfo = dinfo;
        this.ddepartid = ddepartid;
        this.sex = sex;
        this.ddetail = ddetail;
    }

    protected Doctor(Parcel in) {
        did = in.readInt();
        dname = in.readString();
        dlevel = in.readString();
        dinfo = in.readString();
        ddepartid = in.readString();
        sex = in.readInt();
        ddetail = in.readString();
    }

    public static final Creator<Doctor> CREATOR = new Creator<Doctor>() {
        @Override
        public Doctor createFromParcel(Parcel in) {
            return new Doctor(in);
        }

        @Override
        public Doctor[] newArray(int size) {
            return new Doctor[size];
        }
    };

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getDlevel() {
        return dlevel;
    }

    public void setDlevel(String dlevel) {
        this.dlevel = dlevel;
    }

    public String getDinfo() {
        return dinfo;
    }

    public void setDinfo(String dinfo) {
        this.dinfo = dinfo;
    }

    public String getDdepartid() {
        return ddepartid;
    }

    public void setDdepartid(String ddepartid) {
        this.ddepartid = ddepartid;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getDdetail() {
        return ddetail;
    }

    public void setDdetail(String ddetail) {
        this.ddetail = ddetail;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "did=" + did +
                ", dname='" + dname + '\'' +
                ", dlevel='" + dlevel + '\'' +
                ", dinfo='" + dinfo + '\'' +
                ", ddepartid='" + ddepartid + '\'' +
                ", sex=" + sex +
                ", ddetail='" + ddetail + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(did);
        parcel.writeString(dname);
        parcel.writeString(dlevel);
        parcel.writeString(dinfo);
        parcel.writeString(ddepartid);
        parcel.writeInt(sex);
        parcel.writeString(ddetail);
    }
}
