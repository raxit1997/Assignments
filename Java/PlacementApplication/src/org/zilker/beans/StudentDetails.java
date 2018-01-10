package org.zilker.beans;

public class StudentDetails {

	private String name, email, placedStatus, deptName;
	private int regno, arrears;
	private float cgpa;

	// Getters
	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getDeptName() {
		return deptName;
	}

	public String getPlacedStatus() {
		return placedStatus;
	}

	public int getRegno() {
		return regno;
	}

	public int getArrears() {
		return arrears;
	}

	public float getCgpa() {
		return cgpa;
	}

	// Setters
	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPlacedStatus(String placedStatus) {
		this.placedStatus = placedStatus;
	}

	public void setRegno(int regno) {
		this.regno = regno;
	}

	public void setArrears(int arrears) {
		this.arrears = arrears;
	}

	public void setCgpa(float cgpa) {
		this.cgpa = cgpa;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

}
