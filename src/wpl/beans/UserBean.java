package wpl.beans;

public class UserBean {

	private String firstname;
	private String lastname;
    private String password;
    private String email;
    private String cur_location;
    private String lst_location;
    private String last_login;
    private int user_id;
    /**
     * Default constructor. 
     */
    public UserBean() {
    	password = "";
    	email = "";
    	cur_location = "";
    	lst_location = "";
    	last_login = "";
    	lastname = "";
    	firstname = "";
    	user_id = 0;
    }
    public int getUserid() {
		return user_id;
	}
	
	public void setUserid(int user_id) {
		this.user_id = user_id;
	}
    public String getFirstname() {
		return firstname;
	}
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
    public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	
	public String getLastLogin() {
		return last_login;
	}
	
	public void setLastLogin(String lst_login) {
		this.last_login = lst_login;
	}

	public String getCurLocation() {
		return cur_location;
	}
	
	public void setCurLocation(String location) {
		this.cur_location = location;
	}
	public String getLstLocation() {
		return lst_location;
	}
	
	public void setLstLocation(String location) {
		this.lst_location = location;
	}
}
