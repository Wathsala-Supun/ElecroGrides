package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Branch {

		String branch_id;
		String branch_name;
		String branch_address;
		String district;
		String cover_areas;
		
		
		// A common method to connect with the database.
	    public Connection connect() { 

		 Connection con = null;

		 try
		 		{
			 	Class.forName("com.mysql.jdbc.Driver");
		 
			 	//Provide the correct details: DBServer/DBName, username, password 
			 	con= DriverManager.getConnection("jdbc:mysql://localhost.3306/electrogrid","root", "DinushikaAlvis");
		 
			 	//For testing	
			 	System.out.print("DB Successfully connected");
		 		}
		 
		 	catch(Exception e)
		 	{
		 		e.printStackTrace();
		 	}

		 return con;
		 
		}


	public String insertBranch(String branch_id, String branch_name, String branch_address, String district, String cover_areas) {
			
			String output = ""; 
			
			try
			     {
			
					Connection con = connect();
					
					if (con == null)
						
					{
						return "Error while connecting to the database for inserting";
					}
					
			
					// create a prepared statement
			
					String query = " insert into branch(`branch_id`,`branch_name`,`branch_address`,`district`,`cover_areas`)" + " values (?, ?, ?, ?, ?)";
			
					PreparedStatement preparedStmt  = con.prepareStatement(query); 
			
					// binding values
			
					preparedStmt.setString(1, branch_id);
					preparedStmt.setString(2, branch_name);
					preparedStmt.setString(3, branch_address);
					preparedStmt.setString(4, district);
					preparedStmt.setString(5, cover_areas);
					
					 
				
			
					//execute the statement
			
					preparedStmt.execute();
					con.close();
					
					output = "Branch details inserted successfully";
			   }
			
			catch (Exception e)
			
			 {
					output = "Error while inserting the branches";
					
					System.err.println(e.getMessage());
			 }
			

			
			return output;	
			
		    } 


	public String readBranch() {
	 
		   String output = "";
				
		   try 	
		        {
		
					Connection con = connect();
					
					if (con == null)		
					
				{
						return "Error while connecting to the database for reading.";
				}
					
					
		 // Prepare the html table to be displayed
					
		 output = "<table border='1'><tr><th>Branch ID</th>"	 
				 +"<th>Project Code</th><th>Branch Name</th>"	 
				 + "<th>Branch Address</th>"
				 + "<th>District</th>"
				 + "<th>Power Areas</th>"
				 + "<th>Power Consumtion</th></tr>";
		 
		 	String query = "select * from branch";
		 	Statement stmt = con.createStatement();
		 	ResultSet rs = stmt.executeQuery(query); 
		 	
		 // iterate through the rows in the result set
		
		 	while (rs.next())
		 
		 	{
		 		
		 		String branch_id = rs.getString("branch_id");
		 		String branch_name = rs.getString("branch_name");
		 		String branch_address = rs.getString("branch_address");
		 		String district = rs.getString("district");
		 		String cover_areas = rs.getString("cover_areas");
		 		
		 		     
		 		
		 		
		 // Add a row into the html table
		 		
		 		output += "<tr><td>" + branch_id + "</td>";
		 		output += "<td>" + branch_name + "</td>";
		 		output += "<td>" + branch_address + "</td>";
		 		output += "<td>" + district + "</td>"; 
		 		output += "<td>" + cover_areas + "</td>";
		 		
		 		
		 		
		 // buttons 		
		   output
				  += "<td><input name='btnUpdate' "
				  + " type='button' value='Update' class='btn btn-secondary' </td>"
		 		  + "<td><form method='post' action='branch.jsp'>"
		 		  + "<input name='btnRemove' " + " type='submit' value='Remove' class='btn btn-danger'>"
		 		  + "<input name='branch_id' type='hidden' " + " value='" + branch_id + "'>" + "</form></td></tr>";
		 		 
		 
		 }	 	
			 	
		 con.close(); 
		
		 
		 // Complete the html table
		       output += "</table>";	       
		       }
		   
		catch (Exception e)
		 {
		     output = "Error while reading the projects.";
		 
		     System.err.println(e.getMessage());
		 }
				
		return output;

		}


	public String deleteBranch(String branch_id) {

		   String output = "";

				try
				   {
					
					Connection con = connect();
					
					if (con == null)
						
				    {
						
						return "Error while connecting to the database for deleting.";
				    }
					
		 // create a prepared statement
					
					String query = "delete from products where project_Id=?";
					
					PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
					
					preparedStmt.setInt(1, Integer.parseInt(branch_id));

		 // execute the statement
					
					preparedStmt.execute();
					con.close();
					
					output = "Branch details have been deleted Successfully";
				  }
				
				catch (Exception e)
				{
					output = "Error while deleting the branches.";
					System.err.println(e.getMessage());
				}
				
				return output;
		}
		



	public String updateBranch(String branch_id, String branch_name, String branch_address, String district, String cover_areas) {
		
		String output = "";
		  
		  try
			  {
			  
			    Connection con = connect();
			 
			 if (con == null)
			   {
				 return "Error while connecting to the database for updating.";
			   }
			 
			// create a prepared statement
			 String query = "UPDATE branch SET branch_name=?,branch_address=?,district=?,cover_areas=?,power_consumtion=? WHERE branch_id=?"; 
			 PreparedStatement preparedStmt = con.prepareStatement(query);
	 
			// binding values
			 
			    preparedStmt.setString(1, branch_id);
				preparedStmt.setString(2, branch_name);
				preparedStmt.setString(3, branch_address);
				preparedStmt.setString(4, district);
				preparedStmt.setString(5, cover_areas);
	

			 
			 //execute the statement
			 preparedStmt.execute();
			 con.close();
			 output = "Branch details have been updated successfully";
			 
			   }
		  
		catch (Exception e)
		 {
		     output = "Error while updating the branch.";
		     System.err.println(e.getMessage());
		 }
		
		return output;
	}
		
		
	}



