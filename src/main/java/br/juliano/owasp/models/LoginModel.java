package br.juliano.owasp.models;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "logins")
public class LoginModel implements Serializable {

	private static final long serialVersionUID = -7089686072951056056L;
	
	@Id
	@GeneratedValue
	private UUID id;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;
	
	public LoginModel() {
	}
	
	public LoginModel(String email, String password) {
		this.email = email;
		this.password = password;
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

}