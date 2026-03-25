package com.pack1;

import java.sql.DriverManager;
import java.sql.Connection;
import java.util.Scanner;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



public class Jdbc_pro6 
{
	private String driver="oracle.jdbc.OracleDriver";
	private String db_url="jdbc:oracle:thin:@localhost:1521:orcl";
	private String db_uname="system";
	private String db_pwd="123456";
	
	Scanner sc=new Scanner(System.in);
	
	
	/*Database Connection*/
	
	Connection connect()
	{
		Connection con=null;
		try
		{
			Class.forName(driver);
			con=DriverManager.getConnection(db_url, db_uname, db_pwd);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return con;
	}
	
	/*Implementing library management system using prepared statement*/
	
	void lmsOperation()
	{
		Connection con=connect();
		
		
		
		
		try 
		{
			
			PreparedStatement pstmt1=con.prepareStatement("insert into library values(?,?,?,?,?)");
			PreparedStatement pstmt2=con.prepareStatement("select * from library");
			PreparedStatement pstmt3=con.prepareStatement("select * from library where bookid=?");
			PreparedStatement pstmt4=con.prepareStatement("update library set bookname=?,author=?,genere=?,bookcost=? where bookid=? ");
			PreparedStatement pstmt5=con.prepareStatement("Delete from library where bookid=?");
			
			
			
			
			
			
			while(true)
			{
				System.out.println("\n\nPʟᴇᴀsᴇ ᴄʜᴏᴏsᴇ ʏᴏᴜʀ Oᴘᴛɪᴏɴ");
				System.out.println("\n1.𝐀𝐝𝐝 𝐁𝐨𝐨𝐤\n2.𝐕𝐢𝐞𝐰 𝐀𝐥𝐥 𝐁𝐨𝐨𝐤𝐬 𝐃𝐚𝐭𝐚 \n3.𝐑𝐞𝐭𝐫𝐢𝐞𝐯𝐞 𝐁𝐨𝐨𝐤𝐃𝐚𝐭𝐚  \n4.𝐔𝐩𝐝𝐚𝐭𝐞 𝐁𝐨𝐨𝐤 𝐃𝐚𝐭𝐚\n5.𝐃𝐞𝐥𝐞𝐭𝐞 𝐁𝐨𝐨𝐤 𝐝𝐚𝐭𝐚\n6.𝐄𝐱𝐢𝐭");
				int choice=Integer.parseInt(sc.nextLine());
				switch(choice)
				{
					case 1:
							System.out.println("Add Book with Data");
							
							System.out.println("Enter Bookid:");
							String bookid=sc.nextLine();
							
							System.out.println("Enter BookName:");
							String bookname=sc.nextLine();
							
							System.out.println("Enter Author:");
							String author=sc.nextLine();
							
							System.out.println("Enter Genere:");
							String genere=sc.nextLine();
							
							System.out.println("Enter BookCost:");
							int bookcost=Integer.parseInt(sc.nextLine());
							
							pstmt1.setString(1,bookid);
							pstmt1.setString(2,bookname);
							pstmt1.setString(3,author);
							pstmt1.setString(4,genere);
							pstmt1.setInt(5,bookcost);
							
							int rowCount=pstmt1.executeUpdate();
							
								if(rowCount==1)
									System.out.println("Data Added/Inserted Successfully");
								else
									System.out.println("'Data Insertion failed'");
							
							break;
					case 2:
							System.out.println("\n\t𝘿𝙞𝙨𝙥𝙡𝙖𝙮𝙞𝙣𝙜 𝘼𝙡𝙡 𝘽𝙤𝙤𝙠𝙨 𝘿𝙖𝙩𝙖");
							System.out.println("\t---------------------------");
							
							ResultSet rs=pstmt2.executeQuery();
							
							while(rs.next())
								System.out.println(rs.getString(1)+ " "+rs.getString(2)+ " "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getInt(5));
							
							break;
					case 3:
							System.out.println("\n\t𝙍𝙚𝙩𝙧𝙞𝙚𝙫𝙞𝙣𝙜 𝘽𝙤𝙤𝙠 𝘿𝙖𝙩𝙖");
							System.out.println("\t-----------------------");
							
							System.out.println("Enter Bookid:");
							String bookid2=sc.nextLine();
							
							pstmt3.setString(1, bookid2);
							
							
							ResultSet rs2=pstmt3.executeQuery();
							
							while(rs2.next())
							{
								System.out.println();
								System.out.println("Data of Book with Id "+bookid2+" is: \n");
								System.out.println(rs2.getString(1)+ " "+rs2.getString(2)+ " "+rs2.getString(3)+" "+rs2.getString(4)+" "+rs2.getInt(5));
							}
	
							break;
					case 4:
							System.out.println("\n\t𝙐𝙥𝙙𝙖𝙩𝙞𝙣𝙜 𝘽𝙤𝙤𝙠 𝘿𝙖𝙩𝙖");
							System.out.println("\t---------------------");
							
							System.out.println("Enter Bookid:");
							String bookid3=sc.nextLine();
							
							System.out.println("Enter BookName:");
							String bookname2=sc.nextLine();
							
							System.out.println("Enter Author:");
							String author2=sc.nextLine();
							
							System.out.println("Enter Genere:");
							String genere2=sc.nextLine();
							
							System.out.println("Enter BookCost:");
							int bookcost2=Integer.parseInt(sc.nextLine());//input
							
							pstmt4.setString(1, bookname2);
							pstmt4.setString(2, author2);
							pstmt4.setString(3, genere2);//prepare
							pstmt4.setInt(4, bookcost2);
							pstmt4.setString(5,bookid3);
							
							int rowCount3=pstmt4.executeUpdate(); //query
							
							if(rowCount3>0)//condition
								System.out.println("𝑩𝒐𝒐𝒌 𝒅𝒂𝒕𝒂 𝑼𝒑𝒅𝒂𝒕𝒆 𝑺𝒖𝒄𝒄𝒆𝒔𝒔𝒇𝒖𝒍𝒍𝒚...");
							else
								System.out.println("No Book Found with ID"+bookid3);
							
							break;
					case 5:
							System.out.println("\n\t𝘿𝙚𝙡𝙚𝙩𝙞𝙣𝙜 𝘽𝙤𝙤𝙠𝙨 𝘿𝙖𝙩𝙖");
							System.out.println("\t----------------");
							
							System.out.println("Enter Bookid:");
							String bookid4=sc.nextLine();
							
							pstmt5.setString(1,bookid4);
							
							int rowCount4=pstmt5.executeUpdate();
							
							if(rowCount4>0)
								System.out.println("Book with ID "+bookid4+" Deleted Successfully!");
							else
								System.out.println("'Book not found with that ID'");
							
							break;
					case 6:
							System.out.println("\n\n\t\t\t 𝙂𝙧𝙚𝙖𝙩 𝙬𝙤𝙧𝙠 𝘽𝙪𝙙𝙙𝙮\n");
							System.out.println("\t\t\t✌️THANK✌️ ✌️YOU✌️");
							System.out.println("\r\n"
									+ "𝙔𝙤𝙪 𝙖𝙧𝙚 𝙀𝙭𝙞𝙩𝙞𝙣𝙜 𝙩𝙝𝙚 𝙇𝙞𝙗𝙧𝙖𝙧𝙮 𝙈𝙖𝙣𝙖𝙜𝙚𝙢𝙚𝙣𝙩 𝙨𝙮𝙨𝙩𝙚𝙢 𝙞𝙣");
							
							for(int i=3;i>=1;i--)
					        {
					            System.out.println(+i);
					            Thread.sleep(1000);
					        }
							System.exit(0);
							break;
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	
	
	
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		Jdbc_pro6 aobj=new Jdbc_pro6();
		System.out.println("\n\t----------------------------------------------------------------------");
		System.out.println("\t\t\t𝐖𝐄𝐋𝐂𝐎𝐌𝐄 𝐓𝐎 𝐋𝐈𝐁𝐑𝐀𝐑𝐘 𝐌𝐀𝐍𝐀𝐆𝐄𝐌𝐄𝐍𝐓 𝐒𝐘𝐒𝐓𝐄𝐌 ");
		System.out.println("\t----------------------------------------------------------------------");
		aobj.lmsOperation();

	}

}
