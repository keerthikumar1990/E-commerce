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
import wpl.dao.*;
import wpl.beans.BidDetailsBean;
import wpl.beans.CartBean;
import wpl.beans.CartDisplayBean;
import wpl.beans.PostBean;
import wpl.beans.UserBean;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
/**
 * Servlet implementation class CartController
 */
@WebServlet({ "/CartController", "/CartController.do" })
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CartManipulate cart = null;
	CartBean cartbean = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartController() {
        super();
        cart = new CartManipulate();
        cartbean = new CartBean();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		if(request.getParameter("cmd").equals("postbids")){
			HttpSession session = request.getSession();
			int userid= (int)session.getAttribute("user_id");
			int postid = Integer.parseInt(request.getParameter("postid"));
			ArrayList<BidDetailsBean> bidList = cart.getBidDetails(userid,postid);
			Gson gson = new Gson();
			JsonElement element = gson.toJsonTree(bidList, new TypeToken<List<BidDetailsBean>>() {}.getType());

			JsonArray jsonArray = element.getAsJsonArray();
			response.setContentType("application/json");
			response.getWriter().print(jsonArray);
		}

		if(request.getParameter("cmd").equals("cartdetails")){
			HttpSession session = request.getSession();
			int userid = (int)session.getAttribute("user_id");
			ArrayList<CartDisplayBean> cartitems = cart.getCartDisplayDetails(userid);
			Gson gson = new Gson();
			JsonElement element = gson.toJsonTree(cartitems, new TypeToken<List<CartDisplayBean>>() {}.getType());

			JsonArray jsonArray = element.getAsJsonArray();
			response.setContentType("application/json");
			response.getWriter().print(jsonArray);
		}
		if(request.getParameter("cmd").equals("cartCount")){
			HttpSession session = request.getSession();
			int userid = (int)session.getAttribute("user_id");
			int count = cart.getCartCount(userid);
			response.getWriter().print(count);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		if(request.getParameter("cmd").equals("addItem")){
			HttpSession session = request.getSession();
			int userid = (int)session.getAttribute("user_id");
			int bid_id = Integer.parseInt(request.getParameter("bid_id"));
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			cartbean.setUserid(userid);
			cartbean.setBidId(bid_id);
			cartbean.setQuantity(quantity);

			if(cart.addItemToCart(cartbean) == 0){
				response.getWriter().append("inserted");
			}else if(cart.addItemToCart(cartbean) == 1){
				response.getWriter().append("updated");
			}
			else{
				response.getWriter().append("failed");
			}
		}
		
		if(request.getParameter("cmd").equals("deleteItem")){
			int cartid = Integer.parseInt(request.getParameter("cart_id"));
			if(cart.deleteItemFromCart(cartid)){
				response.getWriter().append("success");
			}else{
				response.getWriter().append("failed");
			}
		}
		
		if(request.getParameter("cmd").equals("updateQuantity")){
			int cartid = Integer.parseInt(request.getParameter("cart_id"));
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			if(cart.updateQuantityInCart(cartid, quantity)){
				response.getWriter().append("success");
			}else{
				response.getWriter().append("failed");
			}
		}
	}

}
