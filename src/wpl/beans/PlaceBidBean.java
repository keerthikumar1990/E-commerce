package wpl.beans;

public class PlaceBidBean {

    private double bidamount;
    private int bid_id;
    private int post_id;
    /**
     * Default constructor. 
     */
    public PlaceBidBean() {
    	bidamount = 0;
    	bid_id = 0;
    	post_id = 0;
    }
    
	public double getBidamount() {
		return bidamount;
	}
	
	public void setBidamount(double bidamount) {
		this.bidamount = bidamount;
	}
	public int getBidId() {
		return bid_id;
	}
	
	public void setBidId(int bid_id) {
		this.bid_id = bid_id;
	}
	public int getPostId() {
		return post_id;
	}
	
	public void setPostId(int post_id) {
		this.post_id = post_id;
	}
}
