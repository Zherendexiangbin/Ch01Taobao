package com.example.ch01taobao.entity;

import java.io.Serializable;
import java.util.Objects;

public class Commodity implements Serializable {
    private String sHeader;
    private String sName;
    private String sAbout;
    private String sNo;

    public Commodity(){

    }

    public Commodity(String sHeader, String sName, String sAbout, String sNo) {
        this.sHeader = sHeader;
        this.sName = sName;
        this.sAbout = sAbout;
        this.sNo = sNo;
    }

    //    public Commodity(int sHeader, String sName, String sAbout, String sNo) {
//        this.sHeader = sHeader;
//        this.sName = sName;
//        this.sAbout = sAbout;
//        this.sNo = sNo;
//    }


//    public int getsHeader() {
//        return sHeader;
//    }
//    public void setsHeader(int sHeader) {
//        this.sHeader = sHeader;
//    }

    public String getsHeader() {
        return sHeader;
    }

    public void setsHeader(String sHeader) {
        this.sHeader = sHeader;
    }

    public String getsName() {
        return sName;
    }
    public void setsName(String sName) {
        this.sName = sName;
    }
    public String getsAbout() {
        return sAbout;
    }
    public void setsAbout(String sAbout) {
        this.sAbout = sAbout;
    }
    public String getsNo() {
        return sNo;
    }
    public void setsNo(String sNo) {
        this.sNo = sNo;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Commodity product = (Commodity) o;
        double value1 = Double.parseDouble(product.sNo.replaceAll("[^\\d.]+", ""));
        double value2 = Double.parseDouble(sNo.replaceAll("[^\\d.]+", ""));
        return sHeader == product.sHeader && Double.compare(value1, value2) == 0 && Objects.equals(sName, product.sName) && Objects.equals(sAbout, product.sAbout);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sHeader,sName,sNo,sAbout);
    }
    @Override
    public String toString() {
        return "Commodity{" +
                "sHeader=" + sHeader +
                ", sName='" + sName + '\'' +
                ", sAbout='" + sAbout + '\'' +
                ", sNo='" + sNo + '\'' +
                '}';
    }
}
