package wpl.beans;

public class CartBean {

    private int user_id;
    private int post_id;
    private int bid_id;
    private int qty;
    /**
     * Default constructor. 
     */
    public CartBean() {
    	user_id = 0;
    	post_id = 0;
    	bid_id = 0;
    	qty = 0;
    }
    public int getUserid() {
		return user_id;
	}
	
	public void setUserid(int user_id) {
		this.user_id = user_id;
	}
	public int getPostid() {
		return post_id;
	}
	
	public void setPostid(int post_id) {
		this.post_id = post_id;
	}
	public int getBidId() {
		return bid_id;
	}
	
	public void setBidId(int bid_id) {
		this.bid_id = bid_id;
	}
	public int getQuantity() {
		return qty;
	}
	
	public void setQuantity(int qty) {
		this.qty = qty;
	}
}
