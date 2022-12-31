package com.example.palmhospitalservice.bean;

public class Depart {
    private int departid;
    private String departname;

    public Depart(){}
    public Depart(int departid, String departname) {
        this.departid = departid;
        this.departname = departname;
    }

    public int getDepartid() {
        return departid;
    }

    public void setDepartid(int departid) {
        this.departid = departid;
    }

    public String getDepartname() {
        return departname;
    }

    public void setDepartname(String departname) {
        this.departname = departname;
    }

    @Override
    public String toString() {
        return "Depart{" +
                "departid=" + departid +
                ", departname='" + departname + '\'' +
                '}';
    }
}
