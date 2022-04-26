package com.inquiry.service;

import com.inquiry.model.inquiryModel;

import java.sql.*;
import java.util.ArrayList;

public class inquiryService {

    Connection con;

    public inquiryService() {
        try {
            String url ="jdbc:mysql://localhost:3306/electricitydatabase";
            String uname ="root";
            String pwd = "";



            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url,uname,pwd);
        } catch (Exception e) {
            System.out.println(e + "Data Insert Unsucessfull");
        }
    }

    public inquiryModel insertInquiry(inquiryModel inquiry){
        String insert="insert into inquiry(inqId,descs,cname,mobNo) value(?,?,?,?)";
        try {
            PreparedStatement ps =con.prepareStatement(insert);
            ps.setLong(1,inquiry.getInqId());
            ps.setString(2,inquiry.getDescs());
            ps.setString(3,inquiry.getCname());
            ps.setLong(4,inquiry.getMobNo());


            ps.execute();
        } catch (Exception e) {
            System.out.println(e + "Data Insert Unsucessfull");
        }
        return inquiry;
    }

    public ArrayList<inquiryModel> getInquiry() throws SQLException {

        ArrayList<inquiryModel> data = new ArrayList<>();
        String select ="select * from inquiry";
        PreparedStatement ps = con.prepareStatement(select);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            inquiryModel model = new inquiryModel();

            model.setInqId(rs.getInt("inqId"));
            model.setDescs(rs.getString("descs"));
            model.setCname(rs.getString("cname"));
            model.setMobNo(rs.getInt("mobNo"));



            data.add(model);
        }


        return data;
    }

    public ArrayList<inquiryModel> getInquiryById(int inqId) throws SQLException{
        ArrayList<inquiryModel> data = new ArrayList<inquiryModel>();
        String select="select * from inquiry where inqId=?";
        PreparedStatement ps = con.prepareStatement(select);
        ps.setInt(1,inqId);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            inquiryModel model = new inquiryModel();

            model.setInqId(rs.getInt("inqId"));
            model.setDescs(rs.getString("descs"));
            model.setCname(rs.getString("cname"));
            model.setMobNo(rs.getInt("mobNo"));


            data.add(model);
        }
        return data;
    }

    public inquiryModel updateInquiry(inquiryModel inquiry){

        String insert = "update inquiry set descs=?, cname=?, mobNo=?  where inqId=?";

        try {
            PreparedStatement ps =con.prepareStatement(insert);

            ps.setString(1,inquiry.getDescs());
            ps.setString(2,inquiry.getCname());
            ps.setLong(3,inquiry.getMobNo());
            ps.setLong(4,inquiry.getInqId());


            ps.execute();
        } catch (Exception e) {
            System.out.println(e + "Data Update Unsucessfull");
        }


        return inquiry;
    }

    public int deleteInquiry(int inqId){

        String insert="delete from inquiry where inqId=?";

        try{
            PreparedStatement ps = con.prepareStatement(insert);
            ps.setInt(1,inqId);
            ps.executeUpdate();

        }catch (Exception e){
            System.out.println(e +"data delete unsuccessfull");
        }
        return inqId;
    }
}
