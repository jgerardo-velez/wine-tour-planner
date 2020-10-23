package edu.cetys.springlabs.dto.form;

//import lombok.Getter;
//import lombok.Setter;

//@Getter 
//@Setter
public class UserCredentialForm {
	private String email;
	private String password;
	private String role;
	
	public UserCredentialForm() {	
	}
	
	public UserCredentialForm(String email, String password, String role) {
		this.email = email;
		this.password = password;
		this.role = role;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserCredential [email=" + email + ", password=" + password + ", role=" + role + ", toString()="
				+ super.toString() + "]";
	}
	
}
