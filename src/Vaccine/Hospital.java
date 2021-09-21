package Vaccine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Hospital extends AdminAll{    //inheritance
	public void update1()
	{
		    String TODAY;
			System.out.println("which id u want to update");
			String id=sc.next();
			boolean available=aadharcheck(id);
			if(available==true)
			{
					System.out.println("Enter the name of the vaccine");
					String vaccinetype=sc.next();
					String dosage="1st";
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
					Date date = new Date();  
					TODAY=formatter.format(date);
					
						try 
						{
							Connection con=AdminAll.getConnection();
							int data=0;
							PreparedStatement ps= con.prepareStatement("update vaccination set dosage=?,vaccine_date =?,type_of_vaccine=? where aadhar=?");
							ps.setString(1,dosage);
							ps.setString(2,TODAY);
							ps.setString(3,vaccinetype);
							ps.setString(4,id);
							
							data=ps.executeUpdate();
							if(data>0)
							{System.out.println("CONGRATULATION YOU HAVE COMPLETED YOUR FIRST DOSE OF VACCINE");}
							else
							{System.out.println("NOT UPDATED");}
						con.close();}	
						catch (SQLException e)
							{
								System.out.println(e);
							}
			}else {	System.out.println("aadhar number is not available,Kindly enter the details before vaccination");
		    insertperson();}
	}
		
		
		public void dose2details(String aadhar,String name,int age,String dose1date,String Vaccine_type) {
			String TODAY=null;
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
			    Date date = new Date();  
			    TODAY=formatter.format(date);
			    try
			    {  
			        Connection con=AdminAll.getConnection();  
			        PreparedStatement ps=con.prepareStatement("insert into vaccinated (aadhar,name,age,dose1_date,dose2_date,type_of_vaccine) values (?,?,?,?,?,?)");  
			        ps.setString(1,aadhar);  
			        ps.setString(2,name); 
			        ps.setInt(3,age); 
			        ps.setString(4,dose1date);
			        ps.setString(5,TODAY);
			        ps.setString(6,Vaccine_type);
			        ps.executeUpdate();  
			        con.close();  
			    }
			    catch(Exception ex)
			    {ex.printStackTrace();}  
			    delete(aadhar);
			    
		}


	public void displaylist() {
			System.out.println("Enter the number to perform your need");
			System.out.println("1.ADD A PERSON ");
			System.out.println("2.MOBILE NUMBER UPDATION");
			System.out.println("3.FIRST TIME FOR VACCINATION"); 
			System.out.println("4.SECOND DOSE UPDATION");
			System.out.println("5.PRINT CERTIFICATE");
			System.out.println("6.EXIT");
			int ch=sc.nextInt();
			switch(ch) {
			case 1:insertperson();
			displaylist();
			break;
			case 2:updatemobile();
			displaylist();
			break;
			case 3:update1();
			displaylist();
			break;
			case 4:SecondDose();displaylist(); break;
			case 5:User u=new User();u.user();
			break;
			case 6:break;
			default:System.out.println("choose from the choice displayed");
			displaylist();
			}
			}

	public static void main(String[] args) {
		Hospital h=new Hospital();
		h.displaylist();

	}

}
