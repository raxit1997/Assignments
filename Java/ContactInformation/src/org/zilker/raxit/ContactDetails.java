package org.zilker.raxit;

public class ContactDetails {

	private String firstName, lastName, email;
	private String officeNumber, mobileNumber, homeNumber, officeExtensionNo, homeAreaCode, homeCountryCode, mobileCountryCode;
	private int contactid = 0;
	
	// Setters
	public void setContactID(int contactid) {
		this.contactid = contactid;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setOfficeExtensionNo(String officeExtensionNo) {
		this.officeExtensionNo = officeExtensionNo;
	}
	
	public void setOfficeNumber(String officeNumber) {
		this.officeNumber = officeNumber;
	}

	public void setMobileCountryCode(String mobileCountryCode) {
		this.mobileCountryCode = mobileCountryCode;
	}
	
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public void setHomeAreaCode(String homeAreaCode) {
		this.homeAreaCode = homeAreaCode;
	}
	
	public void setHomeCountryCode(String homeCountryCode) {
		this.homeCountryCode = homeCountryCode;
	}
	
	public void setHomeNumber(String homeNumber) {
		this.homeNumber = homeNumber;
	}

	// Getters
	public int getContactID() {
		return contactid;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public String getOfficeExtensionCode() {
		return officeExtensionNo;
	}

	public String getOfficeNumber() {
		return officeNumber;
	}

	public String getMobileCountryCode() {
		return mobileCountryCode;
	}
	
	public String getMobileNumber() {
		return mobileNumber;
	}

	public String getHomeAreaCode() {
		return homeAreaCode;
	}
	
	public String getHomeCountryCode() {
		return homeCountryCode;
	}
	
	public String getHomeNumber() {
		return homeNumber;
	}
	
}
