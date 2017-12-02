package com.kungfupandas.sparsh.pojo;

import java.util.ArrayList;

/**
 * Created by tusharchoudhary on 02/12/17.
 */

public class Patient {
    private String name, age, pic, address, mobile;
    private Long lastUpdated, pregnantDate, dueDate, nextMeetingDate;
    private boolean isHighRisk;
    private ArrayList<Test> tests;

    public Long getNextMeetingDate() {
        return nextMeetingDate;
    }

    public void setNextMeetingDate(Long nextMeetingDate) {
        this.nextMeetingDate = nextMeetingDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Long getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Long lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Long getPregnantDate() {
        return pregnantDate;
    }

    public void setPregnantDate(Long pregnantDate) {
        this.pregnantDate = pregnantDate;
    }

    public Long getDueDate() {
        return dueDate;
    }

    public void setDueDate(Long dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isHighRisk() {
        return isHighRisk;
    }

    public void setHighRisk(boolean highRisk) {
        isHighRisk = highRisk;
    }

    public ArrayList<Test> getTests() {
        return tests;
    }

    public void setTests(ArrayList<Test> tests) {
        this.tests = tests;
    }

    public Patient(String name, String age, String pic, String address, String mobile, Long lastUpdated, Long pregnantDate, Long dueDate, Long nextMeetingDate, boolean isHighRisk, ArrayList<Test> tests) {
        this.name = name;
        this.age = age;
        this.pic = pic;
        this.address = address;
        this.mobile = mobile;
        this.lastUpdated = lastUpdated;
        this.pregnantDate = pregnantDate;
        this.dueDate = dueDate;
        this.nextMeetingDate = nextMeetingDate;
        this.isHighRisk = isHighRisk;
        this.tests = tests;
    }
}
