package com.enterprise.beans;

public class UserBean {
	
	private int id;
	private String username;
	private String password;
	private String email;
	private String nickname;
	private String firstName;
	private String lastName;
	private String dateOfBirth;
	private AddressBean address;
	private int creditCardNumber;
	
	private boolean isAdmin;

	/**
	 * Activated = 1, Not Activated = 2, Banned = 3
	 */
	private int accountState;

	public UserBean() {
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public AddressBean getAddress() {
		return address;
	}

	public void setAddress(AddressBean address) {
		this.address = address;
	}

	public int getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(int creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}
	
	public boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public int getAccountState() {
		return accountState;
	}

	public void setAccountState(int accountState) {
		this.accountState = accountState;
	}
	
	

}
