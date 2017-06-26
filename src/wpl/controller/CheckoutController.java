package wpl.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import wpl.beans.CheckoutBean;
import wpl.dao.CartManipulate;
import wpl.dao.Mailer;

/**
 * Servlet implementation class CheckoutController
 */
@WebServlet({ "/CheckoutController", "/CheckoutController.do" })
public class CheckoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CartManipulate cart = null;
    CheckoutBean checkout = null; 
    Mailer mail = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckoutController() {
        super();
        cart = new CartManipulate();
        checkout = new CheckoutBean();
        mail = new Mailer();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession();
		int userid = (int)session.getAttribute("user_id");
		String useremail = (String)session.getAttribute("user_email");
		String userfirstname = (String)session.getAttribute("user_firstname");
		String userlastname = (String)session.getAttribute("user_lastname");
		ArrayList<CheckoutBean> coDetails = cart.checkout(userid);
		if(!coDetails.isEmpty()){
			String bidderemail_title = "";
			String bidderemail_body = "";
			double total = 0;
			String orderinfo = "";
			String bidinfo = "";
			for(CheckoutBean c: coDetails)
			{
				bidderemail_title += "Book Stacks - Your bid has been successfully checked out";
				orderinfo += "Book name: "+c.getBookname()+"\n";
				orderinfo += "Bidder name: "+c.getBidderName()+"\n";
				orderinfo += "Bid amount: "+c.getBidamount()+"\n";
				orderinfo += "Quantity: "+c.getQuantity()+"\n";
				orderinfo += "-------------------------------------------------------------------------------------\n\n";
				bidinfo += "Book name: "+c.getBookname()+"\n";
				bidinfo += "Bid amount: "+c.getBidamount()+"\n";
				bidinfo += "Quantity: "+c.getQuantity()+"\n";
				bidinfo += "Purchaser name: "+userfirstname+" "+userlastname;
				total += c.getBidamount()*c.getQuantity();
				bidderemail_body += "Dear "+c.getBidderName()+",\n\n";
				bidderemail_body += "Please find the details below: \n\n";
				bidderemail_body += bidinfo;
				
				try {
					Mailer.Send(c.getBidderEmail(), bidderemail_title, bidderemail_body);
				} catch (AddressException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			orderinfo += "Total: $"+total+"\n";
			String purchaseremail_title = "Book Stacks - Order Confirmation";
			String purchaseremail_body = "Dear "+userfirstname+" "+userlastname+",\n\n";
			purchaseremail_body += "Please find your order details below: \n\n";
			purchaseremail_body += orderinfo;
			// Send email to Purchaser
			try {
				Mailer.Send(useremail, purchaseremail_title, purchaseremail_body);
			} catch (AddressException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		request.getRequestDispatcher("checkout.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
