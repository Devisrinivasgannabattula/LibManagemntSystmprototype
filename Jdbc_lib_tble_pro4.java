package com.pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.Scanner;

public class Jdbc_lib_tble_pro4 {
	
	private String driver="oracle.jdbc.OracleDriver";
	private String dburl="jdbc:oracle:thin:@localhost:1521:orcl";
	private String dbuname="system";
	private String dbpwd="123456";
	private String display ="select * from library";
	
	Connection connect()
	   {
	       Connection con=null;
	       try
	       {
	           Class.forName(driver);
	           con=DriverManager.getConnection(dburl, dbuname, dbpwd);
	       }
	       catch(Exception e)
	       {
	           e.printStackTrace();
	       }        
	       return con;
	   }
	public void displayingbookdata() 
	{
		try
		{
			Connection con=connect();
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery(display);
			while(rs.next())
			{
				System.out.println(rs.getString(1)+ " "+rs.getString(2)+ " "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getInt(5));
			}
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	public void addingbookdata()
	{
		Scanner sc =new Scanner(System.in);
		System.out.println("Enter the data to be inserted");
		
		
		System.out.println("Enter Bookid:");
		String bookId=sc.nextLine();
		
		System.out.println("Enter BookName:");
		String bookName=sc.nextLine();
		
		System.out.println("Enter AuthorName:");
		String author=sc.nextLine();
		
		System.out.println("Enter Genere:");
		String Genere=sc.nextLine();
		
		System.out.println("Enter BookCost:");
		int bookCost=Integer.parseInt(sc.nextLine());
		
		
		try 
		{
			
			Connection con=connect();
			Statement stmt=con.createStatement();
			int rowCount = stmt.executeUpdate(
					"insert into library values('"+bookId+"','"+bookName+"','"+author+"','"+Genere+"',"+bookCost+")");
			if(rowCount==1)
			{
				System.out.println("Data updated");
				System.out.println("Do you want to view the data: Y/N");
				char choice=sc.nextLine().charAt(0);
				switch(choice) 
				{
				case 'Y','y':
					displayingbookdata();
				    break;
				case 'N','n':
					System.out.println("Thanks for updating the data");
					break;
					default:System.out.println("Invalid data");
				}
			}
			else 
			{
				System.out.println("Data not updated");
			}
			con.close();
		}
		
		catch(SQLIntegrityConstraintViolationException sicve)
		{
			System.out.println("Duplicate Book Id's are not allowed");
			
		}
	
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	

	public void deletingbookdatabyid()
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the BookID to delete that book:");
		String bookId = sc.nextLine();
		try 
		{
			Connection con=connect();
			Statement stmt=con.createStatement();
			int rowCount = stmt.executeUpdate(
					"DELETE FROM library WHERE bookId='"+bookId +"'");
			if(rowCount==1)
			{
				System.out.println("BOOK Deleted successfully");
			}
			else 
			{
				System.out.println("Book Not found");
			}
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	public void lmssystem()
	{
		Scanner sc=new Scanner(System.in);
		System.out.println(" \n\n\n Press '0' to Exit LMS portal \n Press '1' to display the books data! \n Press '2' to add the booksdata! \n Press '3' to delete the booksdata!  " );
		int val=Integer.parseInt(sc.nextLine());
		if(val==1)
		{
			displayingbookdata();
			lmssystem();
		}
		else if(val==2)
		{
			addingbookdata();
			lmssystem();
		}
		else if(val==3)
		{
			deletingbookdatabyid();
			lmssystem();
		}
		else if(val==0)
		{
			
			System.out.println("SHUTDOWN......");
			try {
				System.out.println("System shutting down in seconds...");
				for(int i=5;i>=1;i--)
		        {
		            System.out.println(+i);
		            Thread.sleep(1000);
		        }
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.exit(0);
		}
		else
		{
			System.out.println("'Please Enter Valid input!'");
			lmssystem();
		}
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Jdbc_lib_tble_pro4 aobj = new Jdbc_lib_tble_pro4();
		//aobj.displayingbookdata();
		//aobj.addingbookdata();
		//aobj.deletingbookdatabyid();
		System.out.println("\n\t----------------------------------------------------------------------");
		System.out.println("\t\t\tWELCOME TO LIBRARY MANAGEMENT SYSTEM ");
		System.out.println("\t----------------------------------------------------------------------");
		aobj.lmssystem();
		

	}

}
