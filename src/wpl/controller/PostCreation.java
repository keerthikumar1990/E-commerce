package wpl.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import wpl.beans.*;
import wpl.dao.*;

/**
 * Servlet implementation class PostCreation
 */
@WebServlet("/PostCreation")

public class PostCreation extends HttpServlet {
	
	private PostManipulate post;
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostCreation() {
        super();
        // TODO Auto-generated constructor stub
        post = new PostManipulate();
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
		PostBean postinst = new PostBean();
		HttpSession session = request.getSession();
		postinst.setUserid((int)session.getAttribute("user_id"));
		postinst.setBookname( request.getParameter( "bookname" ) );
		postinst.setCategory(Integer.parseInt(request.getParameter( "category" )));
		postinst.setPublishername( request.getParameter( "publishername" ) );
		postinst.setYear(Integer.parseInt(( request.getParameter( "year" ))));
		postinst.setCondition( request.getParameter( "condition" ) );
		postinst.setAuthorname(request.getParameter( "authorname" ));
		System.out.println("user_ID"+postinst.getUserid());
		
		if(post.createPost(postinst))
        {
			request.setAttribute("creationMsg", "Post was created successfully! Want to create another post?");
        	request.getRequestDispatcher("createpost.jsp").forward(request, response);
        }
        else
        {
        	request.setAttribute("creationMsg", "Sorry! Error in creating your post. Try again!");
        	request.getRequestDispatcher("createpost.jsp").forward(request, response);
        }
		
	}

}
