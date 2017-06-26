package wpl.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import wpl.beans.BidDetailsBean;
import wpl.beans.MyPostsBean;
import wpl.beans.PostDetailsBean;
import wpl.beans.UserBean;
import wpl.dao.PostManipulate;

/**
 * Servlet implementation class PostsController
 */
@WebServlet({ "/PostsController", "/PostsController.do" })
public class PostsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PostManipulate post;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostsController() {
        super();
        post = new PostManipulate();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		if(request.getParameter("cmd").equals("postDetails")){
			HttpSession session = request.getSession();
			UserBean userbean = new UserBean();
			userbean.setUserid((int)session.getAttribute("user_id"));
			String query = request.getParameter("query");
			ArrayList<PostDetailsBean> postList = null;
			try {
				postList = post.getPostDetails(userbean,query);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Gson gson = new Gson();
			JsonElement element = gson.toJsonTree(postList, new TypeToken<List<PostDetailsBean>>() {}.getType());
	
			JsonArray jsonArray = element.getAsJsonArray();
			response.setContentType("application/json");
			response.getWriter().print(jsonArray);
	   }
		if(request.getParameter("cmd").equals("myposts")){
			HttpSession session = request.getSession();
			int userid = (int)session.getAttribute("user_id");
			ArrayList<MyPostsBean> myPosts = post.getMyPosts(userid);
			Gson gson = new Gson();
			JsonElement element = gson.toJsonTree(myPosts, new TypeToken<List<MyPostsBean>>() {}.getType());

			JsonArray jsonArray = element.getAsJsonArray();
			response.setContentType("application/json");System.out.println(jsonArray);
			response.getWriter().print(jsonArray);
		}
		if(request.getParameter("cmd").equals("closePost")){
			int postid = Integer.parseInt(request.getParameter("post_id"));
			if(post.setPostStatus(postid)){
				response.getWriter().print("success");	
			}else{
				response.getWriter().print("failed");
			}
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
