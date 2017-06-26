package wpl.beans;

public class CartDisplayBean {

    private String bookname;
    private String biddername;
    private double bidamount;
    private int quantity;
    private int cart_id;
    /**
     * Default constructor. 
     */
    public CartDisplayBean() {
    	bookname = "";
    	biddername = "";
    	bidamount = 0;
    	quantity = 0;
    	cart_id = 0;
    }
    public String getBookname() {
		return bookname;
	}
	
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public String getBidderName() {
		return biddername;
	}
	
	public void setBidderName(String biddername) {
		this.biddername = biddername;
	}

	public double getBidamount() {
		return bidamount;
	}
	
	public void setBidamount(double bidamount) {
		this.bidamount = bidamount;
	}
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getCartId() {
		return cart_id;
	}
	
	public void setCartId(int cart_id) {
		this.cart_id = cart_id;
	}
}
