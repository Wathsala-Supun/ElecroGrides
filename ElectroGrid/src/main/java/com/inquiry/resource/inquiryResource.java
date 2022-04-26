package com.inquiry.resource;


import com.inquiry.model.inquiryModel;
import com.inquiry.service.inquiryService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("/inquiry")
public class inquiryResource {

    inquiryService service = new  inquiryService();

    @Path("/insertion")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public inquiryModel addInquiry(inquiryModel inquiry){

        return service.insertInquiry(inquiry);
    }

    @Path("/retrieve")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<inquiryModel> getInquiry() throws SQLException {

        return service.getInquiry();
    }

    @Path("/retrieveById/{inqId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<inquiryModel> getInquiry(@PathParam("inqId") int inqId) throws SQLException{

        return service.getInquiryById(inqId);
    }

    @Path("/updateinquiry")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public inquiryModel updateInquiry(inquiryModel inquiry){

        return service.updateInquiry(inquiry);
    }

    @Path("/deleteInquiryById/{inqId}")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public int  deleteInquiry(@PathParam("inqId")int inqId){

        return service.deleteInquiry(inqId);
    }
}
