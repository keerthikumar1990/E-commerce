package wpl.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import wpl.beans.PlaceBidBean;
import wpl.beans.UserBean;
import wpl.dao.BidManipulate;
import wpl.dao.Mailer;

/**
 * Servlet implementation class BidController
 */
@WebServlet({ "/BidController", "/BidController.do" })
public class BidController extends HttpServlet {
	private UserBean user = null;
	private PlaceBidBean bidbean = null;
	private BidManipulate bid = null;
	private Mailer mail = null;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BidController() {
        super();
        user = new UserBean();
        bidbean = new PlaceBidBean();
        bid = new BidManipulate();
        mail = new Mailer();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		if(request.getParameter("cmd").equals("placebid")){
			HttpSession session = request.getSession();
			int userid = (int)session.getAttribute("user_id");
			int post_id = Integer.parseInt(request.getParameter("post_id"));
			int bidamount = Integer.parseInt(request.getParameter("bidamount"));
			user.setUserid(userid);
			bidbean.setPostId(post_id);
			bidbean.setBidamount(bidamount);
			if(bid.placeBid(userid, bidbean) == 0){
				//mail.sendEmail("rkeerthikumar1990@gmail.com", "Bid successfull", "Test");
				response.getWriter().append("inserted");
			}else if(bid.placeBid(userid, bidbean) == 1){
				response.getWriter().append("updated");
			}
			else{
				response.getWriter().append("failed");
			}
		}
	}

}
