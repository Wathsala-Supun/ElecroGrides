package model;

public class Payment {
	
	//connect database
	private static String url = "jdbc:mysql://localhost:3306/paf";
	private static String userName = "root";
	private static String password = " ";
	
	
	public Connection connect()
	{
	Connection con = null;
	
	try
	{
	  Class.forName("com.mysql.jdbc.Driver");
	  con= DriverManager.getConnection(url,userName,password);
	  //For testing
	  System.out.print("Successfully connected");
	}
	catch(Exception e)
	{
		System.out.println("Database connection is not success!!!");
	}
	
	return con;
	}
	
	
	//insert method
		public String insertPayment(String idpayment, String iddese, String paydate, String payprice)
		{
		   String output = "";
		  try
		   {
		      Connection con = connect();
		      if (con == null)
		   {
		    return "Error while connecting to the database";
		   }
		   // create a prepared statement
		   String query = "insert into paf.payment (idpayment,iddese,paydate,payprice) values (?,?,?,?)";
		   
		   
		   PreparedStatement preparedStmt = con.prepareStatement(query);
		   // binding values
		   preparedStmt.setInt(1, 0);
		  // preparedStmt.setString(2,idpayment );
		   preparedStmt.setString(2, iddese);
		   preparedStmt.setString(3,paydate);
		   preparedStmt.setString(4, payprice);
		
		
		  //execute the statement
		  preparedStmt.execute();
		  con.close();
		  output = "Inserted successfully";
		  }
		  catch (Exception e)
		  {
		  output = "Error while inserting";
		  System.err.println(e.getMessage());
		  }
		  return output;
		  }
		
	
	//read
	
	public String readPayment()
	  {
	     String output = "";
	     try
	    {
	      Connection con = connect();
	      if (con == null)
	    {
	        return "Error while connecting to the database for reading.";
	    }
	// Prepare the html table to be displayed
	   output = "<table border='1'><tr><th>Payment Description</th>"
	            +"<th>Payment Date</th><th>Price</th>"
	           
	            + "<th>Update</th><th>Remove</th></tr>";
	   
	    String query = "select * from payment";
	    Statement stmt = con.createStatement();
	    ResultSet rs = stmt.executeQuery(query);
	// iterate through the rows in the result set
	   while (rs.next())
	   {
	     String idpayment = Integer.toString(rs.getInt("idpayment"));
	     String iddese = rs.getString("iddese");
	     String paydate = rs.getString("paydate");
	     String payprice = rs.getString("payprice");
	     
	// Add a row into the html table
	    output += "<td>" + iddese + "</td>";
	    output += "<td>" + paydate + "</td>";
	
	    output += "<td>" + payprice + "</td>";
	// buttons
	    output += "<td><input name='btnUpdate' "
	    + " type='button' value='Update'></td>"
	    + "<td><form method='post' action='payment.jsp'>"
	    + "<input name='btnRemove' "
	    + " type='submit' value='Remove'>"
	    + "<input name='idpayment' type='hidden' "
	    + " value='" + idpayment + "'>" + "</form></td></tr>";
	    }
	    con.close();
	// Complete the html table
	    output += "</table>";
	    }
	   catch (Exception e)
	  {
	      output = "Error while reading the items.";
	      System.err.println(e.getMessage());
	  }
	    return output;
	 }
	
	 //delete
	
	public String deletePayment(String idpayment)
	 {
	   String output = "";
	 try
	 {
	    Connection con = connect();
	    if (con == null)
	  {
	       return "Error while connecting to the database for deleting.";
	   }
	// create a prepared statement
	      String query = "delete from payment where idpayment=?";
	      PreparedStatement preparedStmt = con.prepareStatement(query);
	// binding values
	      preparedStmt.setInt(1, Integer.parseInt(idpayment));
	// execute the statement
	      preparedStmt.execute();
	      con.close();
	      output = "Deleted successfully";
	 }
	 catch (Exception e)
	 {
	      output = "Error while deleting the payment.";
	      System.err.println(e.getMessage());
	 }
	 return output;
	 }
	
	//update
	public String updatePayment(String idpayment, String iddese, String paydate, String payprice)
	
	{
	    String output = "";
	try
	{
	   Connection con = connect();
	   if (con == null)
	    {  return "Error while connecting to the database for updating."; }
	// create a prepared statement
	  String query = "UPDATE payment SET idpayment=?,iddese=?,paydate=?,payprice=? WHERE idpayment=?";
	  PreparedStatement preparedStmt = con.prepareStatement(query);
	// binding values
	   preparedStmt.setString(1, idpayment);
	   preparedStmt.setString(2,iddese);
	   preparedStmt.setString(3, paydate);
	   preparedStmt.setString(4,payprice ); 
	// execute the statement
	   preparedStmt.execute();
	   con.close();
	   output = "Updated successfully";
	}
	catch (Exception e)
	{
	   output = "Error while updating the Payment.";
	    System.err.println(e.getMessage());
	}
	   return output;
 }



}
