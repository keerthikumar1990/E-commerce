package wpl.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.api.Session;

import wpl.dao.*;
/**
 * Servlet implementation class AjaxController
 */
@WebServlet("/AjaxController")
public class AjaxController extends HttpServlet {
	private AccountManipulate account;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxController() {
        super();
        account = new AccountManipulate();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		if(request.getParameter("cmd") != null && request.getParameter("cmd").equals("checkuser")){
		    String email = request.getParameter("email");
		    if(account.checkEmailPresent(email)){
		    	response.getWriter().append("invalid");
		    }else{
		    	response.getWriter().append("valid");
		    }
		}
		if(request.getParameter("cmd") != null && request.getParameter("cmd").equals("updatePassword")){
		    String oldpassword = request.getParameter("oldpassword");
		    String password = request.getParameter("newpassword");
		    HttpSession session = request.getSession();
		    //System.out.println(account.updatePassword((String)session.getAttribute("user_email"), oldpassword, password));
		    int result = account.updatePassword((String)session.getAttribute("user_email"), oldpassword, password);
		    if(result == 0){
		    	response.getWriter().append("success");
		    }else if(result == -1){
		    	response.getWriter().append("oldinvalid");
		    }else{
		    	response.getWriter().append("failed");
		    }
		}
		if(request.getParameter("cmd") != null && request.getParameter("cmd").equals("updateProfile")){
		    String firstname = request.getParameter("firstname");
		    String lastname = request.getParameter("lastname");
		    String phone = request.getParameter("phone");
		    HttpSession session = request.getSession();
		    int result = account.updateProfile((int)session.getAttribute("user_id"), firstname, lastname,phone);
		    if(result == 0){
		    	session.setAttribute("user_firstname",firstname);
		    	session.setAttribute("user_lastname", lastname);
		    	response.getWriter().append("success");
		    }else{
		    	response.getWriter().append("failed");
		    }
		}
		if(request.getParameter("cmd") != null && request.getParameter("cmd").equals("fetchProfile")){
			HttpSession session = request.getSession();
			HashMap<String,String> res = account.getProfile((int)session.getAttribute("user_id"));
			StringBuilder sb = new StringBuilder();
			for (Map.Entry<String, String> entry : res.entrySet())
			{
			    sb.append(entry.getKey() + "/" + entry.getValue()+"#");
			}
			
			response.getWriter().append(sb);
			
		}
	}

}
