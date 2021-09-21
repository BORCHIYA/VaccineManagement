package Vaccine;
import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {
	
	public void datecalculation(String aadharno) {
		AdminAll a=new AdminAll();
		boolean available=a.aadharcheck(aadharno);
	  	   if(available==true)
	  		{	
							try {
										Class.forName("com.mysql.cj.jdbc.Driver");
										Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/vaccinemanagement?verifyServerCertificate=false&useSSL=true", "root", "root");
										PreparedStatement ps1=con.prepareStatement("select * from vaccination where aadhar=?");
								ps1.setString(1,aadharno);
								ResultSet rs=ps1.executeQuery();
								while(rs.next()) 
								{
										if((rs.getString(5))==null)
											{
												System.out.println(rs.getString(2)+" Kindly vaccinate yourself in any nearby hospital");
												System.out.println("NOTE:Take your aadhar copy with yourself as a proof for verification");
											}
										else 
											{
												System.out.println(rs.getString(2)+" has vaccinated with  "+rs.getString(7)+"  and completed your first dose on "+rs.getDate(6));
												String x=rs.getString(6);
												LocalDate date1=LocalDate.parse(x);
												LocalDate lastdate1=date1.plusDays(84);
												LocalDate today= LocalDate.now();
												long diff = ChronoUnit.DAYS.between(today,lastdate1);
													   if((diff>=-7)&&(diff<=-1))
													   { long days = 7-(Math.abs(diff));
													         if(days==0)
													         {System.out.println(rs.getString(2)+" Kindly take your 2nd dose todays else our 1st dose gets expired and you have to repeat the procedure");}
													         else {System.out.println(rs.getString(2)+"Can take your second dosage within "+days+" days");}
															
														}
												else if(diff==0)
												          {System.out.println("can take your vaccine tomorrow");}
												else if(diff<-7) 
												          {System.out.println("vaccination period has been expired");}
												else      {System.out.println(diff+" days  more for your second dose");}
												
										       }//else
								}//while
								con.close();
							
							}//try
							catch(Exception ae)
							{System.out.println(ae);}
	  		}//if closed
	  	   else {System.out.println("your aadhar number is not available ,Kindly check with neraby hospital as soon as possible");}
	
}
	
	
	public void user() {
		User u=new User();
		
		Scanner sc = new Scanner(System.in);
	    System.out.println("Enter aadhar number(NOTE:Enter the aadhar number without spaces)");
	   String aadhar1 = sc.nextLine();
	Pattern ptrn = Pattern.compile("^[1-9]{1}[0-9]{11}");
	Matcher match = ptrn.matcher(aadhar1);  
	if(match.find() && match.group().equals(aadhar1))
	   {	
		
					try {
										Class.forName("com.mysql.cj.jdbc.Driver");
										Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/vaccinemanagement?verifyServerCertificate=false&useSSL=true", "root", "root");
										PreparedStatement ps=con.prepareStatement("select * from vaccinated where aadhar=?");
										ps.setString(1,aadhar1);
										boolean status=false;
										ResultSet rs=ps.executeQuery();
										status=rs.next();
										if(status==true) {
											System.out.println(rs.getString(3)+" has vaccinated and completed your first dose on "+rs.getString(5)+" and second dose on "+ rs.getString(6)+" \nKindly download the certificate");
										}
										else {
											u.datecalculation(aadhar1);
											
											}
										con.close();
										}
										catch(Exception ae)
										{System.out.println(ae);}
									}
		else
		{
			System.out.println("Invalid format for  Aadhar Number ,Enter the aahar number without spaces");}
		

sc.close();}
	}
