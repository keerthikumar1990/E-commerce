package wpl.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import wpl.beans.*;
import wpl.dao.*;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")

public class Register extends HttpServlet {
	
	private AccountManipulate account;
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
        account = new AccountManipulate();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("login.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		// For registration
		if(!request.getParameter( "regCmd" ).equals("") && request.getParameter( "regCmd" ).equals("Register")){
			RegistrationBean register = new RegistrationBean();
			register.setFirstname( request.getParameter( "firstname" ) );
			register.setLastname( request.getParameter( "lastname" ) );
			register.setEmail( request.getParameter( "email" ) );
			register.setPassword( request.getParameter( "password" ) );
			register.setPhone( request.getParameter( "phone" ) );
			register.setLocation( request.getParameter( "location" ) );
			if(account.registerUser(register)){
				request.setAttribute("registrationMsg", "Registration successful! Login to continue");
			    request.getRequestDispatcher("login.jsp").forward(request, response);
			}else{
				request.setAttribute("registrationMsg", "Registration failed! Please try again");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		}else if(!request.getParameter( "regCmd" ).equals("") && request.getParameter( "regCmd" ).equals("Login")){
			// For login
			
			response.setContentType("text/html;charset=UTF-8");
			UserBean login = new UserBean();
			login.setEmail( request.getParameter( "login_email" ) );
			login.setPassword( request.getParameter( "login_password" ) );
			login.setCurLocation( request.getParameter( "location" ) );
	        if(account.validateUser(login))
	        {
	        	String lastlogin = login.getLastLogin()+" "+login.getLstLocation();
	        	HttpSession session = request.getSession();
	        	session.setAttribute("lastlogin", lastlogin);
	        	session.setAttribute("user_email", login.getEmail());
	        	session.setAttribute("user_firstname", login.getFirstname());
	        	session.setAttribute("user_lastname", login.getLastname());
	        	session.setAttribute("user_id", login.getUserid());
	        	if(request.getParameter( "url" ) != null && !request.getParameter( "url" ).equals("")){
	        		request.getRequestDispatcher(request.getParameter( "url" )).forward(request, response);
	        	}else{
	                request.getRequestDispatcher("index.jsp").forward(request, response);
	        	}
	        }
	        else
	        {
	        	request.setAttribute("registrationMsg", "Wrong username and/or password. Try again!");
	        	if(request.getParameter( "url" ) != null && !request.getParameter( "url" ).equals("")){
	        		request.getRequestDispatcher("login.jsp?redirect="+request.getParameter( "url" )).forward(request, response);
	        	}else{
	        		request.getRequestDispatcher("login.jsp").forward(request, response);
	        	}
	        	
	        }
			
		}
		else{
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		
		
	}

}
