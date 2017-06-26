package wpl.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import wpl.beans.PostDetailsBean;
import wpl.beans.UserBean;
import wpl.dao.*;
/**
 * Servlet implementation class PostAjax
 */
@WebServlet("/PostAjax")
public class PostAjax extends HttpServlet {
	private PostManipulate post;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostAjax() {
        super();
        post = new PostManipulate();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

			HashMap<Integer,String> categories = post.getCategories();
			StringBuilder sb = new StringBuilder();
			for (Map.Entry<Integer, String> entry : categories.entrySet())
			{
			    sb.append(entry.getKey() + "/" + entry.getValue()+".");
			}
			
			response.getWriter().append(sb);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
	}

}
