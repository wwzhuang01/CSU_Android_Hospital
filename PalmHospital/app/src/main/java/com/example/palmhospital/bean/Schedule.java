package com.example.palmhospital.bean;

public class Schedule {
    private int sid;
    private int did;
    private String time;
    float price;

    public Schedule(){}
    public Schedule(int sid, int did, String time, float price) {
        this.sid = sid;
        this.did = did;
        this.time = time;
        this.price = price;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "sid=" + sid +
                ", did=" + did +
                ", time='" + time + '\'' +
                ", price=" + price +
                '}';
    }
}
