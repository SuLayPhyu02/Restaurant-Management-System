package restaurant_project;

import java.sql.*;
import java.util.ArrayList;

import restaurant_project_ui.LoginUI_re;


public class DBHandler {
	private static Connection con;
	private static String userName="root";
	private static String password="Sulay6619#";
	private static int port=3306;
	private static String dbName="restaurant";
	private static String host="localhost";
	
	public static boolean openConnection()
	{
		try {
			//connection
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url="jdbc:mysql://"+host+":"+port+"/"+dbName;
			con=DriverManager.getConnection(url,userName,password);
			return true;
			
		}catch(Exception e)
		{
			return false;
		}
		
	}
	public static boolean closeConnection()
	{
		try {
			
			con.close();
			return true;
			
			
		}catch(Exception e)
		{
			return false;
		}
	}
////////////////////////////////for staff //////////////////////////////////////////////////////////////////
	public static boolean addStaff(String n, String po, String email,String p,String enrolldate)
	{
		p=Checker_re.digestMsg(p);
		try {
			openConnection();
			
			String sql="insert into staffs(sname,sposition,semail,spassword,enrolldate) values(?,?,?,?,?)";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, n);
			ps.setString(2, po);
			ps.setString(3, email); 
			ps.setString(4, p);
			ps.setDate(5, java.sql.Date.valueOf(enrolldate));
			int line=ps.executeUpdate();
			closeConnection();
			return line>0;
			
		}catch(Exception e)
		{
			return false;
		}
	}
	public static boolean delete(int sid)
	{
		try {
			
			openConnection();
			String sql="delete from staffs where sid=?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1,sid);
			int line=ps.executeUpdate();
			closeConnection();
			return line>0;
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}	
	}
	public static int staff_login(String email,String p,String pos)
	{
		int id=-1;
		try {
			openConnection();
			p=Checker_re.digestMsg(p);
			String sql="select sid from staffs where semail=? and spassword=? and sposition=?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, p);
			ps.setString(3, pos);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				id=rs.getInt(1);
			}
		
		}catch(Exception e)
		{
		}
		closeConnection();
		return id;
	}
	public static boolean updateStaff(int sid,String sname,String sposition,String semail,String enrolldate)
	{
		try {
			openConnection();
			String sql="update staffs set sname=?,sposition=?,semail=?,enrolldate=? where sid=?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, sname);
			ps.setString(2, sposition);
			ps.setString(3, semail);
			ps.setString(4,enrolldate);
			ps.setInt(5,sid);
			int line=ps.executeUpdate();
			closeConnection();
			return line>0;
		}catch(Exception e)
		{
			return false;
		}
	}
/////////////////////////////////foreign key ////////////////////////////////////////////////////////
	public static int getTotal(int orno)
	{
		int tot=-1;
		try {
			openConnection();
			String sql="select sum(saledishes.total) from sales,saledishes where sales.said=saledishes.saleid and saledishes.orderno=?;";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, orno);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				tot=rs.getInt(1);
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		closeConnection();
		return tot;
	}
	public static int getCateid(String name)
	{
		int id=-1;
		try {
			openConnection();
			String sql="select cid from categories where cname=?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				id=rs.getInt(1);
			}
			
		}catch(Exception e)
		{
			e.getStackTrace();
			e.printStackTrace();
		}
		closeConnection();
		return id;
	}
	public static String getTodayDate()
	{
		String date="";
		try {
			openConnection();
			String sql="select curdate();";
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				date=rs.getString(1);
			}
			closeConnection();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return date;
	}
	public static int getSubCateid(String name)
	{
		int id=-1;
		try {
			openConnection();
			String sql="select scid from subcategories where scname=?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				id=rs.getInt(1);
			}
			
		}catch(Exception e)
		{
			e.getStackTrace();
			e.printStackTrace();
		}
		closeConnection();
		return id;
	}
	public static int gettbNo(int id)
	{
		int tb=-1;
		try {
			openConnection();
			String sql="select sales.tableNO from sales where said=?;";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				tb=rs.getInt("tableNO");
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		closeConnection();
		return tb;
	}
	public static int getMCount()
	{
		int id=-1;
		try {
			openConnection();
			String sql="SELECT COUNT(staffs.sid) FROM staffs WHERE staffs.sposition='Manager';";
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				id=rs.getInt(1);
			}
			
		}catch(Exception e)
		{
			e.getStackTrace();
			e.printStackTrace();
		}
		closeConnection();
		return id;
	}
	public static int getRCount()
	{
		int id=-1;
		try {
			openConnection();
			String sql="SELECT COUNT(staffs.sid) FROM staffs WHERE staffs.sposition='Receptionist';";
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				id=rs.getInt(1);
			}
			
		}catch(Exception e)
		{
			e.getStackTrace();
			e.printStackTrace();
		}
		closeConnection();
		return id;
	}
	public static String getFpopular()
	{
		String dname="";
		try {
			openConnection();
			String sql="SELECT dishes.dname FROM dishes,saledishes WHERE dishes.did=saledishes.dishid GROUP BY saledishes.dishid ORDER BY COUNT(saledishes.qty) DESC LIMIT 1;";
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				dname=rs.getString(1);
			}
			closeConnection();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return dname;
	}
	public static String getFpoor()
	{
		String dname="";
		try {
			openConnection();
			String sql="SELECT dishes.dname FROM dishes,saledishes WHERE dishes.did=saledishes.dishid GROUP BY saledishes.dishid ORDER BY COUNT(saledishes.qty) LIMIT 1;";
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				dname=rs.getString(1);
			}
			closeConnection();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return dname;
	}
	public static ArrayList<ShoppingItems> FProfit()
	{
		ArrayList<ShoppingItems> shopitem=new ArrayList<ShoppingItems>();
		try {
			openConnection();
			String sql="SELECT shoppingitems.buydate,SUM(sales.alltotal)AS income,SUM(shoppingitems.total)AS expense,(SUM(sales.alltotal)-SUM(shoppingitems.total))AS profit FROM shoppingitems,sales WHERE shoppingitems.buydate=sales.saleDate GROUP BY shoppingitems.buydate HAVING (SUM(sales.alltotal)-SUM(shoppingitems.total))>0;";
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				ShoppingItems si=new ShoppingItems(rs.getString("buydate"),rs.getInt("income"),rs.getInt("expense"),rs.getInt("profit"));
				shopitem.add(si);
			}		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		closeConnection();
		return shopitem;
	}
/////////////////////////////////////////////////
	public static boolean getSIDate(int id)
	{
		try {
			openConnection();
			String sql="UPDATE shoppingitems SET shoppingitems.buydate=CURDATE() WHERE siid=?;";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, id);
			int line=ps.executeUpdate();
			closeConnection();
			return line>0;
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	public static boolean getSDate(int id)
	{
		try {
			openConnection();
			String sql="UPDATE sales SET sales.saleDate=CURDATE() WHERE said=?;";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, id);
			int line=ps.executeUpdate();
			closeConnection();
			return line>0;
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	public static boolean getPayDate(int id)
	{
		try {
			openConnection();
			String sql="UPDATE payment SET payment.paydate=CURDATE() WHERE pid=?;";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, id);
			int line=ps.executeUpdate();
			closeConnection();
			return line>0;
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	public static int getShoppingid(String name)
	{
		int id=-1;
		try {
			openConnection();
			String sql="select siid from shoppingitems where itemname=?;";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				id=rs.getInt(1);
			}
			
		}catch(Exception e)
		{
			e.getStackTrace();
			e.printStackTrace();
		}
		closeConnection();
		return id;
	}
	public static int getDishid(String name)
	{
		int id=-1;
		try {
			openConnection();
			String sql="select did from dishes where dname=?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				id=rs.getInt(1);
			}
			
		}catch(Exception e)
		{
			e.getStackTrace();
			e.printStackTrace();
		}
		closeConnection();
		return id;
	}
	public static String getDname(int id)
	{
		String name="";
		try {
			openConnection();
			String sql="select dishes.dname from dishes where dishes.did=?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				name=rs.getString("dname");
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		closeConnection();
		return name;
	}
	public static int getDprice(int id)
	{
		int price=-1;
		try {
			openConnection();
			String sql="select dishes.dprice from dishes where dishes.did=?;";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				price=rs.getInt(1);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		closeConnection();
		return price;
	}
	public static String getStaffName(int id)
	{
		String name="";
		try {
			openConnection();
			String sql="select staffs.sname from staffs where sid=?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				name=rs.getString(1);
			}
		}catch(Exception e)
		{
			e.getStackTrace();
		}
		closeConnection();
		return name;
	}
	public static int getSaleMax()
	{
		int id=-1;
		try {
			openConnection();
			String sql="SELECT MAX(sales.said) FROM sales;";
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				id=rs.getInt(1);
			}
			
		}catch(Exception e)
		{
			e.getStackTrace();
			e.printStackTrace();
		}
		closeConnection();
		return id;
	}
	public static int getSaleDishMax()
	{
		int id=-1;
		try {
			openConnection();
			String sql="SELECT MAX(saledishes.sdid) FROM saledishes;";
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				id=rs.getInt(1);
			}
			
		}catch(Exception e)
		{
			e.getStackTrace();
			e.printStackTrace();
		}
		closeConnection();
		return id;
	}

	public static int getSIMax()
	{
		int id=-1;
		try {
			openConnection();
			String sql="SELECT MAX(shoppingitems.siid) FROM shoppingitems;";
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				id=rs.getInt(1);
			}
			
		}catch(Exception e)
		{
			e.getStackTrace();
			e.printStackTrace();
		}
		closeConnection();
		return id;
	}
	public static int getSCIMax()
	{
		int id=-1;
		try {
			openConnection();
			String sql="SELECT MAX(sitemscategories.sicid) FROM sitemscategories;";
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				id=rs.getInt(1);
			}
			
		}catch(Exception e)
		{
			e.getStackTrace();
			e.printStackTrace();
		}
		closeConnection();
		return id;
	}
	public static int getStaffId(String name)
	{
		int id=-1;
		try {
			openConnection();
			String sql="select sid from staffs where sname=?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				id=rs.getInt(1);
			}
			
		}catch(Exception e)
		{
			e.getStackTrace();
			e.printStackTrace();
		}
		closeConnection();
		return id;
	}
	public static int getSaleTypeId(String name)
	{
		int id=-1;
		try {
			openConnection();
			String sql="select stid from saletype where saletname=?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				id=rs.getInt(1);
			}
			
		}catch(Exception e)
		{
			e.getStackTrace();
			e.printStackTrace();
		}
		closeConnection();
		return id;
	}
	public static int getPayTid(String ptype)
	{
		int id=-1;
		try {
			openConnection();
			String sql="select ptid from paymenttype where ptype=?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, ptype);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				id=rs.getInt(1);
			}
			
		}catch(Exception e)
		{
			e.getStackTrace();
			e.printStackTrace();
		}
		closeConnection();
		return id;
	}
	public static int getSICid(String sicname)
	{
		int id=-1;
		try {
			openConnection();
			String sql="SELECT sitemscategories.sicid FROM sitemscategories WHERE sitemscategories.sicname=?;";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, sicname);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				id=rs.getInt(1);
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		closeConnection();
		return id;
	}
	public static String getSICName(int sicid)
	{
		String name="";
		try {
			openConnection();
			String sql="SELECT sitemscategories.sicname FROM sitemscategories WHERE sitemscategories.sicid=?;";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, sicid);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				name=rs.getString(1);
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		closeConnection();
		return name;
	}
	public static int getSICprice(int sicid)
	{
		int price=-1;
		try {
			openConnection();
			String sql="SELECT sitemscategories.sicprice FROM sitemscategories WHERE sitemscategories.sicid=?;";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, sicid);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				price=rs.getInt(1);
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		closeConnection();
		return price;
	}
/////////////////////////////////order table///////////////////////////////////////////////////
	public static boolean insertSaleDish(int ordno,int qty,int tot,int saleid,int dishid)
	{
		try {
			openConnection();
			String sql="insert into saledishes(orderno,qty,total,saleid,dishid) values(?,?,?,?,?);";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, ordno);
			ps.setInt(2, qty);
			ps.setInt(3, tot);
			ps.setInt(4, saleid);
			ps.setInt(5, dishid);
			int line=ps.executeUpdate();
			closeConnection();
			return line>0;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	public static boolean insertSales(int id,int tno,String note,int staffid,int saletypeid)
	{
		try {
			openConnection();
			String sql="insert into sales(said,tableNO,remark,sid,saletypeid) values(?,?,?,?,?);";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setInt(2, tno);
			ps.setString(3,note);
			ps.setInt(4, staffid);
			ps.setInt(5, saletypeid);
			int line=ps.executeUpdate();
			closeConnection();
			return line>0;
		}catch(Exception e)
		{
			e.getStackTrace();
			return false;
		}
	}
	public static boolean insertSalesWithoutTN(int id,String date,int staffid,int saletypeid)
	{
		try {
			openConnection();
			String sql="insert into sales(said,saleDate,sid,saletypeid) values(?,?,?,?);";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setDate(2,java.sql.Date.valueOf(date));
			ps.setInt(3, staffid);
			ps.setInt(4, saletypeid);
			int line=ps.executeUpdate();
			closeConnection();
			return line>0;
		}catch(Exception e)
		{
			e.getStackTrace();
			return false;
		}
	}
	public static boolean insertSalesWN(int id,String date,int tno,int staffid,int saletypeid)
	{
		try {
			openConnection();
			String sql="insert into sales(said,saleDate,tableNO,sid,saletypeid) values(?,?,?,?,?);";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setString(2,date);
			ps.setInt(3, tno);
			ps.setInt(4, staffid);
			ps.setInt(5, saletypeid);
			int line=ps.executeUpdate();
			closeConnection();
			return line>0;
		}catch(Exception e)
		{
			e.getStackTrace();
			return false;
		}
	}
	public static boolean insertPayment(int ptid,int said)
	{
		try {
			openConnection();
			String sql="insert into payment(ptid,said) values(?,?);";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, ptid);
			ps.setInt(2, said);
			int line=ps.executeUpdate();
			closeConnection();
			return line>0;
		}catch(Exception e){
			e.getStackTrace();
			return false;
		}
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static boolean insertShopping(int id,int qty,String unit,int tot,int sid,int sicid)
	{
		try {
			openConnection();
			String sql="insert into shoppingitems(siid,iqty,qtyunit,total,staffid,sicateid) values(?,?,?,?,?,?);";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setInt(2, qty);
			ps.setString(3, unit);
			ps.setInt(4, tot);
			ps.setInt(5, sid);
			ps.setInt(6, sicid);
			int line=ps.executeUpdate();
			closeConnection();
			return line>0;
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	public static boolean updateShopping(int id,String name,int qty,String unit,int price,int sid,int tot)
	{
		try {
			openConnection();
			String sql="update shoppingitems set itemname=?,iqty=?,qtyunit=?,price=?,staffid=?,total=? where siid=?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setInt(2, qty);
			ps.setString(3, unit);
			ps.setInt(4, price);
			ps.setInt(5, sid);
			ps.setInt(6, tot);
			ps.setInt(7, id);
			int line=ps.executeUpdate();
			closeConnection();
			return line>0;
		}catch(Exception e)
		{
			e.getStackTrace();
			return false;
		}
	}
	public static boolean insertSICategories(int id,String name,int price)
	{
		try {
			openConnection();
			String sql="INSERT INTO sitemscategories(sicid,sicname,sicprice) values(?,?,?);";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setInt(3, price);
			int line=ps.executeUpdate();
			closeConnection();
			return line>0;
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		
	}
	public static boolean updateSICategories(int id,String name,int price)
	{
		try {
			openConnection();
			String sql="update sitemscategories set sicname=?,sicprice=? where sicid=?;";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setInt(2, price);
			ps.setInt(3, id);
			int line=ps.executeUpdate();
			closeConnection();
			return line>0;
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
////////////////////////////////////////insert values///////////////////////////////////////////////////////
	public static boolean insertDishes(String n,int p,int subcid)
	{
		try {
			openConnection();
			String sql="insert into dishes(dname,dprice,subcid) values(?,?,?)";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, n);
			ps.setInt(2, p);
			ps.setInt(3, subcid);
			int line=ps.executeUpdate();
			closeConnection();
			return line>0;
		}catch(Exception e){
			e.getStackTrace();
			return false;
		}
	}
	public static boolean insertSubC(String n,int cid)
	{
		try {
			openConnection();
			String sql="insert into subcategories(scname,cid) values(?,?)";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, n);
			ps.setInt(2, cid);
			int line=ps.executeUpdate();
			closeConnection();
			return line>0;
		}catch(Exception e){
			e.getStackTrace();
			return false;
		}
	}
	public static boolean insertCate(int cid,String n)
	{
		try {
			openConnection();
			String sql="insert into categories(cid,cname) values(?,?)";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, cid);
			ps.setString(2, n);
			int line=ps.executeUpdate();
			closeConnection();
			return line>0;
		}catch(Exception e){
			e.getStackTrace();
			return false;
		}
	}
	public static boolean insertSaleType(int stid,String type)
	{
		try {
			openConnection();
			String sql="insert into saletype(stid,saletname) values(?,?)";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, stid);
			ps.setString(2, type);
			int line=ps.executeUpdate();
			closeConnection();
			return line>0;
		}catch(Exception e){
			e.getStackTrace();
			return false;
		}
	}
	public static boolean insertPaymentType(int ptid,String type)
	{
		try {
			openConnection();
			String sql="insert into paymenttype(ptid,ptype) values(?,?)";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, ptid);
			ps.setString(2,type);
			int line=ps.executeUpdate();
			closeConnection();
			return line>0;
		}catch(Exception e){
			e.getStackTrace();
			return false;
		}
	}
	public static boolean insertAllToatal(int total,int said)
	{
		try {
			openConnection();
			String sql="update sales set alltotal=? where sales.said=?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, total);
			ps.setInt(2, said);
			int line=ps.executeUpdate();
			closeConnection();
			return line>0;
		}catch(Exception e){
			e.getStackTrace();
			return false;
		}
	}
///////////////////////////////////////////Delete/////////////////////////////////////////////////////////
	public static boolean deleteD(int did)
	{
		try {
			
			openConnection();
			String sql="delete from dishes where did=?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1,did);
			int line=ps.executeUpdate();
			closeConnection();
			return line>0;
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}	
	}
	public static boolean deleteSubC(int cid)
	{
		try {
			
			openConnection();
			String sql="delete from subcategories where scid=?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1,cid);
			int line=ps.executeUpdate();
			closeConnection();
			return line>0;
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}	
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////
	public static boolean UpdateDishes(int did,String dname,int dprice,int subcid)
	{
		try {
			openConnection();
			String sql="update dishes set dname=?,dprice=? where did=? and subcid=?;";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, dname);
			ps.setInt(2, dprice);
			ps.setInt(3, did);
			ps.setInt(4, subcid);
			int line=ps.executeUpdate();
			closeConnection();
			return line>0;
		}catch(Exception e)
		{
			e.getStackTrace();
			return false;
		}
	}	
	public static boolean UpdateSubC(int scid,String cname,int cid)
	{
		try {
			openConnection();
			String sql="update subcategories set scname=? where scid=? and cid=?;";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, cname);
			ps.setInt(2,scid);
			ps.setInt(3, cid);
			int line=ps.executeUpdate();
			closeConnection();
			return line>0;
		}catch(Exception e)
		{
			e.getStackTrace();
			return false;
		}
	}
//////////////////////////////////////Array List of all tables///////////////////////////////////////////////////
	public static ArrayList<Sales> getAllSales()
	{
		ArrayList<Sales> sales=new ArrayList<Sales>();
		try {
			openConnection();
			String sql="select distinct sales.said,sales.saleDate,sales.tableNO,sales.remark,sales.sid,sales.saletypeid,saletype.saletname,paymenttype.ptype,staffs.sname,sales.alltotal from sales,staffs,saletype,payment,paymenttype,saledishes where sales.sid=staffs.sid and sales.saletypeid=saletype.stid and sales.said=payment.said and payment.ptid=paymenttype.ptid and sales.said=saledishes.saleid;";
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				Sales sl=new Sales(rs.getInt("said"),rs.getString("saleDate"),rs.getInt("tableNO"),rs.getString("remark"),rs.getInt("sid"),rs.getInt("saletypeid"),rs.getString("saletname"),rs.getString("ptype"),rs.getString("sname"),rs.getInt("alltotal"));
				sales.add(sl);
			}
			closeConnection();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return sales;
	}
	public static ArrayList<Dish> getAllDishes()
	{
		ArrayList<Dish> dishes=new ArrayList<Dish>();
		try {
			openConnection();
			String sql="select * from dishes";
			PreparedStatement dps=con.prepareStatement(sql);
			ResultSet drs=dps.executeQuery();
			while(drs.next())
			{
				Dish d=new Dish(drs.getInt("did"), drs.getString("dname"),drs.getInt("dprice"),drs.getInt("subcid"));
				dishes.add(d);
			}
			closeConnection();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return dishes;
	}
	public static ArrayList<Sub_Categories> getAllSubC()
	{
		ArrayList<Sub_Categories> subc=new ArrayList<Sub_Categories>();
		try{
			openConnection();
			String sql="select * from subcategories";
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				Sub_Categories sub=new Sub_Categories(rs.getInt("scid"),rs.getString("scname"),rs.getInt("cid"));
				subc.add(sub);
			}
			closeConnection();
		
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		return subc;
	}
	public static ArrayList<Categories> getCate()
	{
		ArrayList<Categories> category=new ArrayList<Categories>();
		try{
			openConnection();
			String sql="select * from categories";
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				Categories cate=new Categories(rs.getInt("cid"),rs.getString("cname"));
				category.add(cate);
			}
			closeConnection();
		
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		return category;
	}
	
	public static ArrayList<Staff> getAllStaffs()
	{
		ArrayList<Staff> staffs=new ArrayList<Staff>();
		try {
			openConnection();
			String sql="select * from staffs";
			PreparedStatement ps=con.prepareStatement(sql);

			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				Staff s=new Staff(rs.getInt("sid"), rs.getString("sname"), rs.getString("sposition"), rs.getString("semail"), rs.getString("spassword"),rs.getDate("enrolldate").toString());
				staffs.add(s);
			}
			closeConnection();
		}catch(Exception e)
		{
			e.getStackTrace();
		}
		
		return staffs;
	}
	public static ArrayList<Staff> getStaffWP()
	{
		ArrayList<Staff> staffs=new ArrayList<Staff>();
		try {
			openConnection();
			String sql="select sname,sposition,semail from staffs";
			PreparedStatement ps=con.prepareStatement(sql);

			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				Staff s=new Staff(rs.getString("sname"), rs.getString("sposition"), rs.getString("semail"));
				staffs.add(s);
			}
			closeConnection();
		}catch(Exception e)
		{
			e.getStackTrace();
		}
		
		return staffs;
	}
	public static ArrayList<Staff> getAllSEmail()
	{
		ArrayList<Staff> staffs=new ArrayList<Staff>();
		try {
			openConnection();
			String sql="select semail from staffs";
			PreparedStatement ps=con.prepareStatement(sql);

			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				Staff s=new Staff(rs.getString("semail"));
				staffs.add(s);
			}
			closeConnection();
		}catch(Exception e)
		{
			e.getStackTrace();
		}
		
		return staffs;
	}
	public static ArrayList<Staff> getownRinfo()
	{
		ArrayList<Staff> staffs=new ArrayList<Staff>();
		try {
			openConnection();
			String sql="select * from staffs where semail=? and sposition='Receptionist';";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1,LoginUI_re.getStaffEmail());
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				Staff s=new Staff(rs.getInt("sid"), rs.getString("sname"), rs.getString("sposition"), rs.getString("semail"), rs.getString("spassword"),rs.getDate("enrolldate").toString());
				staffs.add(s);
			}
			closeConnection();
		}catch(Exception e)
		{
			e.getStackTrace();
		}
		
		return staffs;
	}
	public static ArrayList<Staff> getownMan()
	{
		ArrayList<Staff> staffs=new ArrayList<Staff>();
		try {
			openConnection();
			String sql="select * from staffs where semail=? and sposition!='Admin';";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1,LoginUI_re.getStaffEmail());
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				Staff s=new Staff(rs.getInt("sid"), rs.getString("sname"), rs.getString("sposition"), rs.getString("semail"), rs.getString("spassword"),rs.getDate("enrolldate").toString());
				staffs.add(s);
			}
			closeConnection();
		}catch(Exception e)
		{
			e.getStackTrace();
		}
		
		return staffs;
	}

	public static ArrayList<ShoppingItems> getAllShopIView()
	{
		ArrayList<ShoppingItems> shopitem=new ArrayList<ShoppingItems>();
		try {
			openConnection();
			String sql="SELECT siid,sicname,sicprice,iqty,qtyunit,buydate,total,staffid,sicateid,sname FROM shoppingitems,sitemscategories,staffs WHERE staffs.sid=shoppingitems.staffid AND shoppingitems.sicateid=sitemscategories.sicid;";
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				ShoppingItems si=new ShoppingItems(rs.getInt("siid"),rs.getInt("iqty"),rs.getString("qtyunit"),rs.getString("buydate"),rs.getInt("total"),rs.getInt("staffid"),rs.getInt("sicateid"),rs.getString("sname"),rs.getString("sicname"),rs.getInt("sicprice"));
				shopitem.add(si);
			}		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		closeConnection();
		return shopitem;
	}
	public static ArrayList<SItemCategories> getAllSICategories()
	{
		ArrayList<SItemCategories> sicate=new ArrayList<SItemCategories>();
		try {
			openConnection();
			String sql="SELECT * FROM sitemscategories;";
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				SItemCategories sic=new SItemCategories(rs.getInt("sicid"),rs.getString("sicname"),rs.getInt("sicprice"));
				sicate.add(sic);
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		closeConnection();
		return sicate;
	}
	public static ArrayList<Sale_Dishes_Joint> getAllSalesDishes(int id)
	{
		ArrayList<Sale_Dishes_Joint> saledish=new ArrayList<Sale_Dishes_Joint>();
		try {
			openConnection();
			String sql="select saledishes.sdid,saledishes.orderno,dishes.dname,dishes.dprice,saledishes.qty,saledishes.total,saledishes.saleid,saledishes.dishid from dishes,saledishes,sales where dishes.did=saledishes.dishid and sales.said=saledishes.saleid AND sales.said=?;";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				Sale_Dishes_Joint sd=new Sale_Dishes_Joint(rs.getInt("sdid"),rs.getInt("orderno"),rs.getString("dname"),rs.getInt("dprice"),rs.getInt("qty"),rs.getInt("total"),rs.getInt("saleid"));
				saledish.add(sd);
			}
			closeConnection();
		}catch(Exception e)
		{
			e.getStackTrace();
		}
		return saledish;
	}
	public static ArrayList<Sale_Dishes_Joint> getDish(int id)
	{
		ArrayList<Sale_Dishes_Joint> saledish=new ArrayList<Sale_Dishes_Joint>();
		try {
			openConnection();
			String sql="SELECT dishes.dname,saledishes.qty,dishes.dprice FROM dishes,saledishes WHERE dishes.did=saledishes.dishid AND saledishes.orderno=?;";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				Sale_Dishes_Joint sd=new Sale_Dishes_Joint(rs.getString("dname"),rs.getInt("qty"),rs.getInt("dprice"));
				saledish.add(sd);
			}
			closeConnection();
		}catch(Exception e)
		{
			e.getStackTrace();
		}
		return saledish;
	}
	
	public static ArrayList<Sale_Dishes_Joint> getViewDish(int id)
	{
		ArrayList<Sale_Dishes_Joint> saledish=new ArrayList<Sale_Dishes_Joint>();
		try {
			openConnection();
			String sql="SELECT dishes.dname,saledishes.qty,dishes.dprice FROM dishes,saledishes WHERE dishes.did=saledishes.dishid AND saledishes.sdid=?;";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				Sale_Dishes_Joint sd=new Sale_Dishes_Joint(rs.getString("dname"),rs.getInt("qty"),rs.getInt("dprice"));
				saledish.add(sd);
			}
			closeConnection();
		}catch(Exception e)
		{
			e.getStackTrace();
		}
		return saledish;
	}
	public static ArrayList<SaleType> getAllSType()
	{
		ArrayList<SaleType> stype=new ArrayList<SaleType>();
		try {
			openConnection();
			String sql="select * from saletype";
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				SaleType st=new SaleType(rs.getInt("stid"),rs.getString("saletname"));
				stype.add(st);
			}
			closeConnection();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return stype;
	}
	public static ArrayList<PaymentType> getAllPType()
	{
		ArrayList<PaymentType> ptype=new ArrayList<PaymentType>();
		try {
			openConnection();
			String sql="select * from paymenttype";
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				PaymentType pt=new PaymentType(rs.getInt("ptid"),rs.getString("ptype"));
				ptype.add(pt);
			}
			closeConnection();
		}
		catch(Exception e)
		{
			e.getStackTrace();
		}
		return ptype;
	}
	public static ArrayList<Payment> getAllPayment()
	{
		ArrayList<Payment> pay=new ArrayList<Payment>();
		try {
			openConnection();
			String sql="select * from payment";
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				Payment p=new Payment(rs.getInt("pid"),rs.getDate("paydate").toString(),rs.getInt("ptid"),rs.getInt("said"));
				pay.add(p);
			}
			closeConnection();
		}
		catch(Exception e)
		{
			e.getStackTrace();
		}
		return pay;
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static ArrayList<Sale_Dishes_Joint> getPopular(String startDate,String endDate)
	{
		ArrayList<Sale_Dishes_Joint> pop=new ArrayList<Sale_Dishes_Joint>();
		try {
			openConnection();
			String sql="SELECT dishes.dname,count(saledishes.qty),sales.saleDate FROM saledishes,dishes,sales WHERE dishes.did=saledishes.dishid AND sales.said=saledishes.saleid AND sales.saleDate>=DATE(?)AND sales.saleDate<=(?)GROUP BY saledishes.dishid ORDER BY COUNT(saledishes.qty) DESC LIMIT 5;";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1,startDate);
			ps.setString(2, endDate);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				Sale_Dishes_Joint po=new Sale_Dishes_Joint(rs.getString("dname"));
				pop.add(po);
			}
			closeConnection();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return pop;
	}
	public static ArrayList<Sale_Dishes_Joint> getPoor(String start,String end)
	{
		ArrayList<Sale_Dishes_Joint> poor=new ArrayList<Sale_Dishes_Joint>();
		try {
			openConnection();
			String sql="SELECT dishes.dname,count(saledishes.qty),sales.saleDate FROM saledishes,dishes,sales WHERE dishes.did=saledishes.dishid AND sales.said=saledishes.saleid AND sales.saleDate>=DATE(?)AND sales.saleDate<=(?)GROUP BY saledishes.dishid ORDER BY COUNT(saledishes.qty) LIMIT 5;";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, start);
			ps.setString(2, end);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				Sale_Dishes_Joint po=new Sale_Dishes_Joint(rs.getString("dname"));
				poor.add(po);
			}
			closeConnection();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return poor;
	}
	public static ArrayList<Sale_Dishes_Joint> getPoPShowDishes(String start,String end)
	{
		ArrayList<Sale_Dishes_Joint> saledish=new ArrayList<Sale_Dishes_Joint>();
		try {
			openConnection();
			String sql="select saledishes.dishid,dishes.dname,count(saledishes.qty)AS count from saledishes,dishes,sales WHERE sales.saleDate>=DATE(?)AND sales.saleDate<=DATE(?)AND dishes.did=saledishes.dishid AND sales.said=saledishes.saleid group by dishid order by count(saledishes.qty) desc;";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, start);
			ps.setString(2, end);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				Sale_Dishes_Joint sd=new Sale_Dishes_Joint(rs.getInt("dishid"),rs.getString("dname"),rs.getInt("count"));
				saledish.add(sd);
			}
			closeConnection();
		}catch(Exception e)
		{
			e.getStackTrace();
		}
		return saledish;
	}
	public static ArrayList<ShoppingItems> getqtyUnit()
	{
		ArrayList<ShoppingItems>unit=new ArrayList<ShoppingItems>();
		try {
			openConnection();
			String sql="select shoppingitems.";
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				ShoppingItems s=new ShoppingItems("qtyunit");
				unit.add(s);
			}
			closeConnection();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return unit;
	}
	public static ArrayList<ShoppingItems> getexpense(String startdate,String enddate)
	{
		ArrayList<ShoppingItems> expnese=new ArrayList<ShoppingItems>();
		try {
			openConnection();
			String sql="select shoppingitems.buydate,sum(shoppingitems.total)as expense FROM shoppingitems WHERE shoppingitems.buydate>=DATE(?)AND shoppingitems.buydate<=(?)GROUP BY shoppingitems.buydate;";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1,startdate);
			ps.setString(2,enddate);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				ShoppingItems si=new ShoppingItems(rs.getString("buydate"),rs.getInt("expense"));
				expnese.add(si);
			}
			closeConnection();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return expnese;
	}
	public static ArrayList<Sales> getincome(String start,String end)
	{
		ArrayList<Sales> income=new ArrayList<Sales>();
		try {
			openConnection();
			String sql="select sales.saleDate,sum(sales.alltotal)as income FROM sales WHERE sales.saleDate>=DATE(?) AND sales.saleDate<=(?) GROUP BY sales.saleDate;";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, start);
			ps.setString(2, end);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				Sales si=new Sales(rs.getString("saleDate"),rs.getInt("income"));
				income.add(si);
			}
			closeConnection();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return income;
	}
	public static ArrayList<ShoppingItems> getProfit(String start,String end)
	{
		ArrayList<ShoppingItems> shopitem=new ArrayList<ShoppingItems>();
		try {
			openConnection();
			String sql="SELECT shoppingitems.buydate,SUM(sales.alltotal)AS income,SUM(shoppingitems.total)AS expense,(SUM(sales.alltotal)-SUM(shoppingitems.total))AS profit FROM shoppingitems,sales WHERE shoppingitems.buydate>=DATE(?)AND shoppingitems.buydate<=(?)AND shoppingitems.buydate=sales.saleDate GROUP BY shoppingitems.buydate HAVING (SUM(sales.alltotal)-SUM(shoppingitems.total))>0;";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, start);
			ps.setString(2, end);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				ShoppingItems si=new ShoppingItems(rs.getString("buydate"),rs.getInt("income"),rs.getInt("expense"),rs.getInt("profit"));
				shopitem.add(si);
			}		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		closeConnection();
		return shopitem;
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void main(String args[])
	{
		//addStaff("Su Lay Phyu", "Admin", "sulay11@gmail.com", "Sulay1122","2021-12-25");
//		System.out.println(getexpense("2021-12-1","2021-12-5"));
//		System.out.println(getincome("2021-12-1","2021-12-5"));
//		System.out.println(getProfit("2021-12-1","2021-12-10"));
		System.out.println(getDish(199).size());
	}


}
