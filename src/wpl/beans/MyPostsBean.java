package wpl.beans;

public class MyPostsBean {
	
    private String categoryname;
    private String bookname;
    private int year;
    private String publishername;
    private String condition;
    private String authorname;
    private int post_id;
    private String status;
    
    /**
     * Default constructor. 
     */
    public MyPostsBean() {
    	bookname = "";
    	publishername = "";
    	condition = "";
    	authorname="";
    	categoryname="";
    	year = 0;
    	post_id = 0;
    	status = "";
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
	public String getCategoryname() {
		return categoryname;
	}
	
	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}
	public int getPostid() {
		return post_id;
	}
	
	public void setPostid(int post_id) {
		this.post_id = post_id;
	}
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
}
