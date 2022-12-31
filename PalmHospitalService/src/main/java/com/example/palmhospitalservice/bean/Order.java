package com.example.palmhospitalservice.bean;

public class Order {
    private String oid;
    private int sid;
    private int uid;
    public Order(){}
    public Order(String oid, int sid, int uid) {
        this.oid = oid;
        this.sid = sid;
        this.uid = uid;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "Order{" +
                "oid='" + oid + '\'' +
                ", sid=" + sid +
                ", uid=" + uid +
                '}';
    }
}
