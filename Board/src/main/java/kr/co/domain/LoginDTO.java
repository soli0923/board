package kr.co.domain;

import java.io.Serializable;

public class LoginDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String pw;
	private boolean useCookie;
	
	public LoginDTO() {
		// TODO Auto-generated constructor stub
	}

	public LoginDTO(int id, String pw, boolean useCookie) {
		super();
		this.id = id;
		this.pw = pw;
		this.useCookie = useCookie;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public boolean isUseCookie() {
		return useCookie;
	}

	public void setUseCookie(boolean useCookie) {
		this.useCookie = useCookie;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
