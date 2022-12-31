package com.example.palmhospital.bean;

public class Order {
    String time;
    String departname;
    String dname;
    public Order(){}
    public Order(String time, String departname, String dname) {
        this.time = time;
        this.departname = departname;
        this.dname = dname;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDepartname() {
        return departname;
    }

    public void setDepartname(String departname) {
        this.departname = departname;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    @Override
    public String toString() {
        return "Order{" +
                "time='" + time + '\'' +
                ", departname='" + departname + '\'' +
                ", dname='" + dname + '\'' +
                '}';
    }
}
