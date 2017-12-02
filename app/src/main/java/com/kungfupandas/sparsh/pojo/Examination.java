package com.kungfupandas.sparsh.pojo;

/**
 * Created by tusharchoudhary on 02/12/17.
 */

public class Examination {
    private String number;
    private String ecg;
    private String bp;
    private String weight;
    private String height;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEcg() {
        return ecg;
    }

    public void setEcg(String ecg) {
        this.ecg = ecg;
    }

    public String getBp() {
        return bp;
    }

    public void setBp(String bp) {
        this.bp = bp;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public Examination(String number, String ecg, String bp, String weight, String height) {
        this.number = number;
        this.ecg = ecg;
        this.bp = bp;
        this.weight = weight;
        this.height = height;
    }
}
