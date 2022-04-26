package com;
import model.Payment;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Payment")
public class PaymentService {
	
	Payment paymentObj = new Payment();
	/*@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readPayment1()
	{
	return "Hello";
	}
	*/
	//read
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readPayment()
	{
	return paymentObj.readPayment();
	}
	
	//insert
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPayment(@FormParam("idpayment") String idpayment,
	@FormParam("iddese") String iddese,
	@FormParam("paydate") String paydate,
	@FormParam("payprice") String payprice)
	{
	String output = paymentObj.insertPayment(idpayment,iddese , paydate, payprice);
	return output;
	

	}
	//delete
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePayment(String itemData)
	{
	//Convert the input string to an XML document
	Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());

	String idpayment = doc.select("idpayment").text();
	String output = paymentObj.deletePayment(idpayment);
	return output;
	}
	
	//update
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePayment(String itemData)
	{
	//Convert the input string to a JSON object
	JsonObject PaymentObject = new JsonParser().parse(itemData).getAsJsonObject();
	//Read the values from the JSON object
	String idpayment = PaymentObject.get("idpayment").getAsString();
	String iddese = PaymentObject.get("iddese").getAsString();
	String paydate = PaymentObject.get("paydate").getAsString();
	String payprice = PaymentObject.get("payprice").getAsString();

	String output = paymentObj.updatePayment(idpayment, iddese, paydate, payprice);
	return output;
	}
	
	

}
