package wpl.beans;


public class RegistrationBean {
    private String firstname;
    private String password;
    private String email;
    private String lastname;
    private String phone;
    private String location;
    /**
     * Default constructor. 
     */
    public RegistrationBean() {
    	firstname = "";
    	password = "";
    	email = "";
    	lastname = "";
    	phone = "";
    	location = "";
    }
	
	public String getFirstname() {
		return firstname;
	}
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
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
	public String getLastname() {
		return lastname;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
}
