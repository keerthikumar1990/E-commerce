package wpl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.sql.CallableStatement;

import wpl.beans.*;
import wpl.conn.*;;

public class BidManipulate {
	
	private Connection conn;
	private CallableStatement calstat; 
	public BidManipulate() {
		conn = DBUtil.getConnection();
		calstat = null;
	}
	public int placeBid (int userid, PlaceBidBean placebid ) {
		
		int res = -1;
		try {
			String storeProc = "{call spr_placeBid(?,?,?,?)}";
			calstat = conn.prepareCall(storeProc);
			calstat.setInt(1, userid);
			calstat.setInt(2, placebid.getPostId());
			calstat.setDouble(3, placebid.getBidamount());
			calstat.registerOutParameter(4, Types.INTEGER);
			calstat.executeUpdate();
			res = calstat.getInt(4);
			calstat.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
}
