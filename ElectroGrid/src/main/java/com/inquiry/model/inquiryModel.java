package com.inquiry.model;

public class inquiryModel {

    private int inqId;

    public String getDescs() {
        return descs;
    }

    public void setDescs(String descs) {
        this.descs = descs;
    }

    private String descs;
    private String cname;
    private int mobNo;

    public inquiryModel() {
    }

    public int getInqId() {
        return inqId;
    }

    public void setInqId(int inqId) {
        this.inqId = inqId;
    }



    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public int getMobNo() {
        return mobNo;
    }

    public void setMobNo(int mobNo) {
        this.mobNo = mobNo;
    }
}
