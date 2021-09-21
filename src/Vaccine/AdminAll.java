package Vaccine;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdminAll {
	Scanner sc=new Scanner(System.in);
	
public static Connection getConnection()
	{  
	    Connection con=null;  
	    try
	    {  
			Class.forName("com.mysql.cj.jdbc.Driver");

	        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/vaccinemanagement?verifyServerCertificate=false&useSSL=true","root","root");  
	    }
	    catch(Exception e){System.out.println(e);}  
	    return con;  
	}  
	
public boolean aadharcheck(String aadhar1) {
	boolean status=false;
	   Pattern ptrn = Pattern.compile("^[1-9]{1}[0-9]{11}");
	    Matcher match = ptrn.matcher(aadhar1);  
	    if(match.find() && match.group().equals(aadhar1)){
	try {
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/vaccinemanagement?verifyServerCertificate=false&useSSL=true", "root", "root");
	PreparedStatement ps=con.prepareStatement("select * from vaccination where aadhar=?");
	ps.setString(1,aadhar1);
	ResultSet rs=ps.executeQuery();
	status=rs.next();
	con.close();
	}catch(Exception e)
	{
		System.out.println(e);
	}
	}
	   return status; }

public void updatemobile()
{
	int data=0;
	Connection con=AdminAll.getConnection();  
	System.out.println("which id u want to update");
	String id=sc.next();
	boolean available=aadharcheck(id);
	if(available==true)
	{	
			System.out.println("enter mobile number");
			String mobile=sc.next();
			try
			{
				PreparedStatement ps=con.prepareStatement("update vaccination set mobile=? where aadhar=?");
				ps.setString(1, mobile);
				ps.setString(2,id);
				data=ps.executeUpdate();
				if(data>0)
					{System.out.println("Mobile Number Updated Successfully");}
				else
					{System.out.println("Mobile number in not updated");}
			con.close();
			}
			catch(Exception e)
			{System.out.println(e);}
	}
	else {System.out.println("aadhar number is not available,Kindly enter the details before vaccination");
	insertperson();}
}

public void insertperson() 
{
		
		System.out.println("Enter the aadhar number(without spaces)");
		String adhar=sc.next();
		  Pattern ptrn = Pattern.compile("^[1-9]{1}[0-9]{11}");
		    Matcher match = ptrn.matcher(adhar);  
		    if(match.find() && match.group().equals(adhar))
		    {
		
							System.out.println("Enter your name as per aadhar card");
							sc.nextLine();
							String name=sc.nextLine();
							System.out.println("Enter your age");
							int age=sc.nextInt();
						 int status=0;  
					    try
					    {  
					        Connection con=AdminAll.getConnection();  
					        PreparedStatement ps=con.prepareStatement("insert into vaccination(aadhar,name,age)values (?,?,?)");  
					        ps.setString(1,adhar);  
					        ps.setString(2,name); 
					        ps.setInt(3,age); 
					        status=ps.executeUpdate();  
					        con.close();  
					    }
					    catch(Exception ex)
					    {System.out.println(ex);}  
					    
					    if(status==1) System.out.println("VALUE INSERTED SUCCESFULLY");
					    
					    else{System.out.println("VALUE NOT INSERTED");}  
					 
}else {System.out.println("Aadhar number is not in proper format");}
}



        
public void delete(String aadhar)
{
		
	boolean available=aadharcheck(aadhar);
	if(available==true)
			{
				try{  
				        Connection con=AdminAll.getConnection();  
				        PreparedStatement ps=con.prepareStatement("delete from  vaccination where aadhar=?");  
				        ps.setString(1,aadhar);  
				        ps.executeUpdate();  
				        con.close();  
				        System.out.println("RECORD DELETED");
				    }
				catch(Exception e)
				{System.out.println(e);}  
			}
	else {System.out.println("Value not available");}
}
public void removevaccinated(String aadhar)
{
	try{  
				        Connection con=AdminAll.getConnection();  
				        PreparedStatement ps=con.prepareStatement("delete from  vaccinated where aadhar=?");  
				        ps.setString(1,aadhar);  
				        ps.executeUpdate();  
				        con.close();  
				        System.out.println("REMOVED THE RECORD ");
				    }
				catch(Exception e)
				{System.out.println(e);}  
			}
	



public void update1()
{
	    String TODAY;
		System.out.println("which id u want to update");
		String id=sc.next();
		boolean available=aadharcheck(id);
		if(available==true)
		{
						System.out.println("Enter yes if the person takes the vaccine today else enter the date in the format(YYYY-MM-DD)");
						String datechoice=sc.next();
						if(datechoice.equalsIgnoreCase("yes"))
						{
							SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
						    Date date = new Date();  
						    TODAY=formatter.format(date);
						}
						else
						{
							TODAY=datechoice;
						}
						System.out.println("Enter the name of the vaccine");
						String vaccinetype=sc.next();
						String dosage="1st";
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
							}	
							catch (SQLException e)
								{
									System.out.println(e);
								}
}
		else
		{	System.out.println("aadhar number is not available,Kindly enter the details before vaccination");
			insertperson();
		}
}
		
	
public void SecondDose()
{
		String aadharid=null;
		String name=null;
		String vaccine=null;
		String date=null;
		int age=0;
		System.out.println("enter the aadhar card number");
		String aadhar=sc.next();
		boolean available=aadharcheck(aadhar);
		if(available==true)
		{	
				try {
							Class.forName("com.mysql.cj.jdbc.Driver");
							Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/vaccinemanagement?verifyServerCertificate=false&useSSL=true", "root", "root");
							PreparedStatement ps1=con.prepareStatement("select * from vaccination where aadhar=?");
							ps1.setString(1,aadhar);
							ResultSet rs=ps1.executeQuery();
							while(rs.next())
							{
								aadharid=rs.getString(1);
								name=rs.getString(2);
								age=rs.getInt(3);
								vaccine=rs.getString(7);
								date=rs.getString(6);
							}	
						}catch(Exception e)
				{System.out.println(e);}
						    
				if(date==null)
				{System.out.println("Take your first dose");}
				else {
						LocalDate date1=LocalDate.parse(date);
						LocalDate lastdate1=date1.plusDays(84);
						LocalDate today= LocalDate.now();
						long diff = ChronoUnit.DAYS.between(today,lastdate1);
							   if((diff>=-7)&&(diff<=-1))
							   {
									System.out.println("can take your second dosage");
									dose2details(aadharid,name,age,date,vaccine);
								}
						else if(diff==0)
						          {System.out.println("can take your vaccine tomorrow");}
						else if(diff<-7) 
						          {System.out.println("vaccination period has been expired");}
						else      {System.out.println(diff+" days  more");}}
		}
		else {
			System.out.println("aadhar number is not available,Kindly enter the details before vaccination");
		    insertperson();
		}
			}

	
	
	public void dose2details(String aadhar,String name,int age,String dose1date,String Vaccine_type) {
		String TODAY=null;
		System.out.println("Enter yes if the person takes the vaccine today else enter the date in the format(YYYY-MM-DD)");
		String datechoice=sc.next();
		if(datechoice.equalsIgnoreCase("yes"))
		{
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
		    Date date = new Date();  
		    TODAY=formatter.format(date);
		}
		else
		{
			TODAY=datechoice;
		} 
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
	
	
	public void count() {
		int count=0;
	int countvaccination=0,totalpopulation=0;
	
	try
    {  
        Connection con=AdminAll.getConnection();  
        PreparedStatement ps=con.prepareStatement("select id from vaccinated order by id desc limit 1;");  
        ResultSet rs=ps.executeQuery();
        while(rs.next()) 
        {count=rs.getInt(1);}
		con.close();  
    System.out.println(count+"  PEOPLE HAVE BEEN VACCINATED WITH 2(two) DOSES ");}
			    catch(Exception ex)
			    {ex.printStackTrace();} 
    
	
		    try
		    {  
		        Connection con=AdminAll.getConnection();  
		        PreparedStatement ps=con.prepareStatement("select count(*)from vaccinated");  
		        ResultSet rs=ps.executeQuery();
		        while(rs.next()) 
		        {count=rs.getInt(1);}
				con.close();  
		    System.out.println(count+"  PEOPLE HAVE BEEN VACCINATED WITH 2(two)DOSES AND ARE HEALTHY");}
					    catch(Exception ex)
					    {ex.printStackTrace();} 
		    
		    
		    try
		    {  
		        Connection con=AdminAll.getConnection();  
		        PreparedStatement ps=con.prepareStatement("select count(*)from vaccination;");  
		        ResultSet rs=ps.executeQuery();
		        while(rs.next()) 
		        {countvaccination=rs.getInt(1);}
				con.close();  
				totalpopulation=count+countvaccination;
		    System.out.println(totalpopulation+" is the total population ");}
					    catch(Exception ex)
					    {ex.printStackTrace();} 
		    
	}

		   public void displaylist() {
				System.out.println("\n ------Enter the number to perform your need---------");
				System.out.println("1.ADD A PERSON ");
				System.out.println("2.MOBILE NUMBER UPDATION");
				System.out.println("3.REMOVE A PERSON WHO HASN'T TAKEN THEIR SECOND DOSE");
				System.out.println("4.REMOVE A PERSON WHO HAS COMPLETED BOTH DOSES");
				System.out.println("5.FIRST TIME FOR VACCINATION");  
				System.out.println("6.SECOND DOSE UPDATION");
				System.out.println("7.Find the number of people records");
				System.out.println("8.END");
				System.out.println("9.CHECK A PERSON'S VACCINATION DETAIL");
				int ch=sc.nextInt();
				switch(ch) {
				case 1:insertperson();
				displaylist();
				break;
				case 2:updatemobile();
				displaylist();
				break;
				case 3:System.out.println("Enter the aadhar number to remove(type aadhar number without spaces) ");
				String aadhar=sc.next();
				delete(aadhar);
				displaylist();
				break;
				case 4:System.out.println("Enter the aadhar number to remove(type aadhar number without spaces) ");
				String aadhar1=sc.next();
				removevaccinated(aadhar1);
				displaylist();
				break;
				case 5:update1();
				displaylist();
				break;
				case 6:SecondDose();
				displaylist();
				break;
				case 9:
				User u=new User();
				u.user();
				break;
				case 7:count();displaylist();break;
				case 8:break;
				default:System.out.println("choose from the choice displayed");
				displaylist();
				}
		   } 
		
	public static void main(String[] args) { 
		AdminAll aa=new AdminAll();				
				aa.displaylist();
				System.out.println("THANK YOU");
		}  


	

}

