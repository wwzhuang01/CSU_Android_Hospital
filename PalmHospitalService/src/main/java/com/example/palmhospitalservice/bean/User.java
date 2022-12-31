package com.example.palmhospitalservice.bean;

public class User {
    private String uid;
    private String uname;
    private String upsw;
    public User(){}
    public User(String uid, String uname, String upsw) {
        this.uid = uid;
        this.uname = uname;
        this.upsw = upsw;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", uname='" + uname + '\'' +
                ", upsw='" + upsw + '\'' +
                '}';
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpsw() {
        return upsw;
    }

    public void setUpsw(String upsw) {
        this.upsw = upsw;
    }
}