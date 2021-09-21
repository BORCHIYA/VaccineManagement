package Vaccine;

import java.util.Scanner;
import java.sql.*;


public class VaccineManagement {
	
	Scanner sc=new Scanner(System.in);
	public void display() {
		System.out.println("Select an option");
		System.out.println("1. USER");
		System.out.println("2. HOSPITAL ADMIN");
		System.out.println("3. CENTRAL ADMIN");
		int roll=sc.nextInt();
		switch(roll) {
		case 1:System.out.println("WELCOME USER");
		User u=new User();
		u.user();
		break;
		case 2:System.out.println("YOU HAVE CHOOSEN HOSPITAL ADMIN SECTION,KINDLY LOGIN TO PROCEED FURTHER");
		Login();
		break;
		case 3:System.out.println("YOU HAVE CHOOSEN CENTRAL ADMIN SECTION,KINDLY LOGIN TO PROCEED FURTHER");
		Login();
		break;
		default:System.out.println("Invalid Choice Choose From the Option Listed");
		display();
		
		}
	}
	//list feature end
public void Login()
{
	try
			{
				
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/vaccinemanagement", "root", "root");
			    String databaseUsername = "";
			    String databasePassword = "";
			    
			    // GET Username and Password
			    System.out.print("Enter Username: ");
			    String name = sc.next();
			    System.out.print("Enter Password: ");
			    String password = sc.next();
			    Statement stmt = con.createStatement();
			    String SQL = "SELECT * FROM login WHERE username='" + name + "' && password='" + password+ "'";
			    ResultSet rs = stmt.executeQuery(SQL);
			    
			    // Check Username and Password
			    while (rs.next()) {
			        databaseUsername = rs.getString("username");
			        databasePassword = rs.getString("password");
			    }
			
			    if (name.equals(databaseUsername) && password.equals(databasePassword))
				    {
				        System.out.println("Successful Login!");
						        if(databaseUsername.equalsIgnoreCase("HOSPITAL")) 
								        {
								        	System.out.println("-----Welcome to hospital admin section-----");
								        	Hospital.main(null);
								        }
						        else if(databaseUsername.equalsIgnoreCase("ADMIN"))
						        		{System.out.println("=======welcome to medical counsil admin section====");
						        		AdminAll.main(null);}
				    } 
			    else
					{
					 System.out.println("Incorrect Password!!!!");
					}
						
				}
	catch(Exception ae)
	{System.out.println(ae);}
   

}
	

	public static void main(String[] args) {
		
		VaccineManagement vc=new VaccineManagement();
		vc.display();
		
		}

	}


