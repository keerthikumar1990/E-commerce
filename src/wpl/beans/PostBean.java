package wpl.beans;


public class PostBean {
	
	private int userid;
    private int category;
    private String bookname;
    private int year;
    private String publishername;
    private String condition;
    private String authorname;
    
    /**
     * Default constructor. 
     */
    public PostBean() {
    	bookname = "";
    	publishername = "";
    	condition = "";
    	authorname="";
    }
	
    public int getUserid() {
		return userid;
	}
	
	public void setUserid(int userid) {
		this.userid = userid;
	}
	
	public String getBookname() {
		return bookname;
	}
	
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	
	public String getAuthorname() {
		return authorname;
	}
	
	public void setAuthorname(String authorname) {
		this.authorname = authorname;
	}
	
	public int getCategory() {
		return category;
	}
	
	public void setCategory(int category) {
		this.category = category;
	}
	public int getYear() {
		return year;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	public String getPublishername() {
		return publishername;
	}
	
	public void setPublishername(String publishername) {
		this.publishername = publishername;
	}
	public String getCondition() {
		return condition;
	}
	
	public void setCondition(String condition) {
		this.condition = condition;
	}
}
