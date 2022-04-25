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
	

}
