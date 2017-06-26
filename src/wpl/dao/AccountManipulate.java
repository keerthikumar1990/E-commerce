package wpl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import java.sql.CallableStatement;

import wpl.beans.*;
import wpl.conn.*;;

public class AccountManipulate {
	
	private static Connection conn;
	private CallableStatement calstat; 
	public AccountManipulate() {
		conn = DBUtil.getConnection();
		calstat = null;
	}
	public boolean registerUser (RegistrationBean user ) {
		boolean flag = false;
		try {
			String storedProcedure = "{call spr_registerUser(?,?,?,?,?,?,?)}";
			calstat = conn.prepareCall(storedProcedure);
			calstat.setString(1, user.getFirstname());
			calstat.setString(2, user.getLastname());
			calstat.setString(3, user.getEmail());
			calstat.setString(4, user.getPassword());
			calstat.setString(5, user.getPhone());
			calstat.setString(6, user.getLocation());
			calstat.registerOutParameter(7, Types.INTEGER);
			calstat.executeUpdate();
			int res = calstat.getInt(7);
			calstat.close();
			if (res == 0)
			{
				flag = true;
			}
			else
			{
				flag = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		return flag;
	}
	
	public boolean validateUser(UserBean user) {
	     boolean flag =false;
	     try{
	    	 
	    	String storedProcedure = "{call spr_validateUser(?,?,?,?,?,?,?,?,?)}";
			calstat = conn.prepareCall(storedProcedure);
			calstat.setString(1, user.getEmail());
			calstat.setString(2, user.getPassword());
			calstat.setString(3, user.getCurLocation());
			calstat.registerOutParameter(4, Types.INTEGER);
			calstat.registerOutParameter(5, Types.TIMESTAMP);
			calstat.registerOutParameter(6, Types.VARCHAR);
			calstat.registerOutParameter(7, Types.VARCHAR);
			calstat.registerOutParameter(8, Types.VARCHAR);
			calstat.registerOutParameter(9, Types.INTEGER);
			calstat.executeQuery();
			int res = calstat.getInt(4);
			if (res == 0)
			{
				
				Date lst_login = calstat.getTimestamp(5);
				user.setFirstname(calstat.getString(6));
				user.setLastname(calstat.getString(7));
				user.setLstLocation(calstat.getString(8));
				user.setUserid(calstat.getInt(9));
				String str_lstlogin = new SimpleDateFormat("MM-dd-yyyy hh:mm a").format(lst_login);
				user.setLastLogin(str_lstlogin);
				flag = true;
				
			}
			else
			{
				flag = false;
			}
			calstat.close();
	     }catch(Exception e)
	     {
	         e.printStackTrace();
	     }
	     return flag;                 
     }   
	
	public boolean checkEmailPresent(String email) {
		boolean flag = false;
		try {
			
			String storedProcedure = "{call spr_getEmail(?)}";
			calstat = conn.prepareCall(storedProcedure);
			calstat.setString(1, email);			
			ResultSet resultSet = calstat.executeQuery();
			if (resultSet.next())
			{
				int count = Integer.parseInt(resultSet.getString(1));
				System.out.println(count);
				if (count == 0)
				{
					flag = false;
				}
				else
				{
					flag = true;
				}
				
			}
			
			resultSet.close();
			calstat.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	public int updatePassword(String email, String oldpassword, String newpassword) {
		int res = -2;
		try {
			
			String storedProcedure = "{call spr_changePassword(?,?,?,?)}";
			calstat = conn.prepareCall(storedProcedure);
			calstat.setString(1, email);
			calstat.setString(2, oldpassword);
			calstat.setString(3, newpassword);
			calstat.registerOutParameter(4, Types.INTEGER);
			calstat.executeQuery();
			res = calstat.getInt(4);
			calstat.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public int updateProfile(int userid, String firstname, String lastname, String phone) {
		int res = -1;
		try {
			
			String storedProcedure = "{call spr_updateProfile(?,?,?,?,?)}";
			calstat = conn.prepareCall(storedProcedure);
			calstat.setInt(1, userid);
			calstat.setString(2, firstname);
			calstat.setString(3, lastname);
			calstat.setString(4, phone);
			calstat.registerOutParameter(5, Types.INTEGER);
			calstat.executeQuery();
			res = calstat.getInt(5);
			calstat.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public HashMap<String,String> getProfile(int userid) {
		HashMap<String,String> res = new HashMap<>();
		try {
			
			String storedProcedure = "{call spr_getProfile(?)}";
			calstat = conn.prepareCall(storedProcedure);
			calstat.setInt(1, userid);
			ResultSet resultSet = calstat.executeQuery();
			if (resultSet.next())
			{
				res.put("firstname", resultSet.getString("firstname"));
				res.put("lastname", resultSet.getString("lastname"));
				res.put("email", resultSet.getString("email"));
				res.put("phone", resultSet.getString("phone"));
			}
			calstat.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

}
