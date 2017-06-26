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
import java.util.List;

import java.sql.CallableStatement;

import wpl.beans.BidDetailsBean;
import wpl.beans.CartBean;
import wpl.beans.CartDisplayBean;
import wpl.beans.CheckoutBean;
import wpl.beans.UserBean;
import wpl.conn.*;;

public class CartManipulate {
	private static Connection conn;
	private CallableStatement calstat; 
	public CartManipulate() {
		conn = DBUtil.getConnection();
		calstat = null;
	}
	
	public int addItemToCart (CartBean cart) {
		int res = -1;
		try {
			String insertItemStoreProc = "{call spr_addToCart(?,?,?,?)}";
			calstat = conn.prepareCall(insertItemStoreProc);
			calstat.setInt(1, cart.getUserid());
			calstat.setInt(2, cart.getBidId());
			calstat.setInt(3, cart.getQuantity());
			calstat.registerOutParameter(4, Types.INTEGER);
			calstat.executeUpdate();
			res = calstat.getInt(4);
			calstat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public boolean deleteItemFromCart (int cartid) {
		boolean flag =false;
		try {
			String deleteSP = "{call spr_deleteCartItem(?,?)}";
			calstat = conn.prepareCall(deleteSP);
			calstat.setInt(1, cartid);
			calstat.registerOutParameter(2, Types.INTEGER);
			calstat.executeUpdate();
			int res = calstat.getInt(2);
			if (res == 0)
			{
				flag = true;
				
			}
			else
			{
				flag = false;
			}
			calstat.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return flag;
	}
	public boolean updateQuantityInCart (int cartid, int quantity) {
		boolean flag =false;
		try {
			String deleteSP = "{call spr_updateQuantity(?,?,?)}";
			calstat = conn.prepareCall(deleteSP);
			calstat.setInt(1, cartid);
			calstat.setInt(2, quantity);
			calstat.registerOutParameter(3, Types.INTEGER);
			calstat.executeUpdate();
			int res = calstat.getInt(3);
			if (res == 0)
			{
				flag = true;
				
			}
			else
			{
				flag = false;
			}
			calstat.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return flag;
	}
	
	public ArrayList<CheckoutBean> checkout (int userid) {
		CheckoutBean checkout = new CheckoutBean();
		ArrayList<CheckoutBean> checkoutlist = new ArrayList<CheckoutBean>();
		try {
			String sp = "{call spr_placeOrder(?,?)}";
			calstat = conn.prepareCall(sp);
			calstat.setInt(1, userid);
			calstat.registerOutParameter(2, Types.INTEGER);
			calstat.execute();
			int res = calstat.getInt(2);
			if (res == 0)
			{
				ResultSet rs = calstat.getResultSet();

	            while(rs.next()) {	
	            	
	            	checkout.setBookname(rs.getString("book_name"));
	            	checkout.setBidderName(rs.getString("biddername"));
	            	checkout.setBidderEmail(rs.getString("email"));
	            	checkout.setBidamount(rs.getDouble("bid_amount"));
	            	checkout.setQuantity(rs.getInt("quantity"));
	            	checkout.setCartId(rs.getInt("cart_id"));
	            	checkoutlist.add(checkout);
	            }
				
			}
			calstat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return checkoutlist;
	}
	
	public ArrayList<BidDetailsBean> getBidDetails(int userid, int postid) {
        ArrayList<BidDetailsBean> bidList = new ArrayList<BidDetailsBean>();
        try {
            
        	String storedProcedure = "{call spr_getBidDetails(?,?)}";
			calstat = conn.prepareCall(storedProcedure);
			calstat.setInt(1, userid);
			calstat.setInt(2, postid);
			ResultSet rs = calstat.executeQuery();

            while(rs.next()) {	
            	BidDetailsBean bidbean = new BidDetailsBean();
            	bidbean.setBookname(rs.getString("book_name"));
            	bidbean.setBidderFirstName(rs.getString("firstname"));
            	bidbean.setBidderLastName(rs.getString("lastname"));
            	bidbean.setBidderEmail(rs.getString("email"));
            	bidbean.setBidamount(rs.getDouble("bid_amount"));
            	bidbean.setBidId(rs.getInt("bid_id"));
            	bidList.add(bidbean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bidList;
    }
	
	public ArrayList<CartDisplayBean> getCartDisplayDetails(int userid) {
        ArrayList<CartDisplayBean> cartitems = new ArrayList<CartDisplayBean>();
        try {
            
        	String storedProcedure = "{call spr_cartDisplay(?)}";
			calstat = conn.prepareCall(storedProcedure);
			calstat.setInt(1, userid);			
			ResultSet rs = calstat.executeQuery();

            while(rs.next()) {	
            	CartDisplayBean cdBean = new CartDisplayBean();
            	
            	cdBean.setBookname(rs.getString("book_name"));
            	cdBean.setBidderName(rs.getString("biddername"));
            	cdBean.setBidamount(rs.getDouble("bid_amount"));
            	cdBean.setQuantity(rs.getInt("quantity"));
            	cdBean.setCartId(rs.getInt("cart_id"));
            	cartitems.add(cdBean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cartitems;
    }
	
	public int getCartCount(int userid) {
        int count = 0; 
		try {
            
        	String storedProcedure = "{call spr_getCartCount(?,?)}";
			calstat = conn.prepareCall(storedProcedure);
			calstat.setInt(1, userid);			
			calstat.registerOutParameter(2, Types.INTEGER);
			calstat.executeUpdate();
			count = calstat.getInt(2);
			calstat.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }

}
