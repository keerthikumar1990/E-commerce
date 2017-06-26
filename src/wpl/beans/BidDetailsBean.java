package wpl.beans;

public class BidDetailsBean {

    private String bookname;
    private String bidder_firstname;
    private String bidder_lastname;
    private String bidder_email;
    private double bidamount;
    private int bid_id;
    /**
     * Default constructor. 
     */
    public BidDetailsBean() {
    	bookname = "";
    	bidder_firstname = "";
    	bidder_lastname = "";
    	bidder_email = "";
    	bidamount = 0;
    	bid_id = 0;
    }
    public String getBookname() {
		return bookname;
	}
	
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public String getBidderFirstName() {
		return bidder_firstname;
	}
	
	public void setBidderFirstName(String bidder_firstname) {
		this.bidder_firstname = bidder_firstname;
	}
	public String getBidderLastName() {
		return bidder_lastname;
	}
	
	public void setBidderLastName(String bidder_lastname) {
		this.bidder_lastname = bidder_lastname;
	}
	public String getBidderEmail() {
		return bidder_email;
	}
	
	public void setBidderEmail(String bidder_email) {
		this.bidder_email = bidder_email;
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
}
