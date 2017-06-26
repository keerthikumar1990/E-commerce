package wpl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.sql.CallableStatement;

import wpl.beans.*;
import wpl.conn.*;;

public class PostManipulate {
	
	private Connection conn;
	private CallableStatement calstat; 
	public PostManipulate() {
		conn = DBUtil.getConnection();
		calstat = null;
	}
	public boolean createPost (PostBean post ) {
		
		boolean flag = false;
		try {
			String storeProc = "{call spr_createPost(?,?,?,?,?,?,?,?)}";
			calstat = conn.prepareCall(storeProc);
			//remove 1
			calstat.setInt(1, post.getUserid());
			calstat.setInt(2, post.getCategory());
			calstat.setString(3, post.getBookname());
			calstat.setInt(4, post.getYear());
			calstat.setString(5, post.getPublishername());
			calstat.setString(6, post.getCondition());
			calstat.setString(7, post.getAuthorname());
			calstat.registerOutParameter(8, Types.INTEGER);
			calstat.executeUpdate();
			int res = calstat.getInt(8);
			calstat.close();
			if (res == 0)
			{
				flag =  true;
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
	public HashMap<Integer,String> getCategories() {
		
		HashMap<Integer,String> categories = new HashMap<>();
		try {
			String query = "select * from category";
			PreparedStatement preparedStatement = conn.prepareStatement( query );
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				categories.put(resultSet.getInt("category_id"),resultSet.getString( "category_name" ));
			}
			resultSet.close();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categories;
	}
	
	public ArrayList<PostDetailsBean> getPostDetails(UserBean user, String query) throws Exception {
        ArrayList<PostDetailsBean> postList = new ArrayList<PostDetailsBean>();
        try {
            if(query.equals("null")){
            	query = "";;
            }
        	String storedProcedure = "{call spr_getPostDetails(?,?)}";
			calstat = conn.prepareCall(storedProcedure);
			calstat.setInt(1, user.getUserid());
			calstat.setString(2, query);
			ResultSet rs = calstat.executeQuery();

            while(rs.next()) {	
            	PostDetailsBean postbean = new PostDetailsBean();
            	postbean.setPostid(rs.getInt("post_id"));
            	postbean.setOwnerUserId(rs.getInt("user_id"));
            	postbean.setOwnername(rs.getString("ownername"));
            	postbean.setBookname(rs.getString("book_name"));
            	postbean.setAuthorname(rs.getString("author_name"));
            	postbean.setYear(rs.getInt("year"));
            	postbean.setCategoryname(rs.getString("category_name"));
            	postbean.setPublishername(rs.getString("publisher_name"));
            	postbean.setCondition(rs.getString("condition"));
            	postList.add(postbean);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return postList;
    }
	
	public ArrayList<MyPostsBean> getMyPosts(int userid) {
        ArrayList<MyPostsBean> postList = new ArrayList<MyPostsBean>();
        try {
            
        	String storedProcedure = "{call spr_getMyPosts(?)}";
			calstat = conn.prepareCall(storedProcedure);
			calstat.setInt(1, userid);			
			ResultSet rs = calstat.executeQuery();

            while(rs.next()) {	
            	MyPostsBean postbean = new MyPostsBean();
            	postbean.setPostid(rs.getInt("post_id"));
               	postbean.setBookname(rs.getString("book_name"));
            	postbean.setAuthorname(rs.getString("author_name"));
            	postbean.setYear(rs.getInt("year"));
            	postbean.setCategoryname(rs.getString("category_name"));
            	postbean.setPublishername(rs.getString("publisher_name"));
            	postbean.setCondition(rs.getString("condition"));
            	postbean.setStatus(rs.getString("status"));
            	postList.add(postbean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return postList;
    }
    public boolean setPostStatus (int postid) {
		
		boolean flag = false;
		try {
			String storeProc = "{call spr_updatePostStatus(?,?)}";
			calstat = conn.prepareCall(storeProc);
			calstat.setInt(1, postid);
			calstat.registerOutParameter(2, Types.INTEGER);
			calstat.executeUpdate();
			int res = calstat.getInt(2);
			calstat.close();
			if (res == 0)
			{
				flag =  true;
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
    
    public static byte[] compress(String str) throws Exception {

        System.out.println("String length : " + str.length());
        ByteArrayOutputStream obj=new ByteArrayOutputStream();
        GZIPOutputStream gzip = new GZIPOutputStream(obj);
        gzip.write(str.getBytes("UTF-8"));
        gzip.close();
        String outStr = obj.toString("UTF-8");
        System.out.println("Output String length : " + outStr.length());
        return obj.toByteArray();
     }

      public static String decompress(byte[] bytes) throws Exception {

        GZIPInputStream gis = new GZIPInputStream(new ByteArrayInputStream(bytes));
        BufferedReader bf = new BufferedReader(new InputStreamReader(gis, "UTF-8"));
        String outStr = "";
        String line;
        while ((line=bf.readLine())!=null) {
          outStr += line;
        }
        System.out.println("Output String lenght : " + outStr.length());
        return outStr;
     }
}
