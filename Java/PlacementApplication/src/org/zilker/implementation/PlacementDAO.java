package org.zilker.implementation;

import java.sql.SQLException;

import org.zilker.beans.CompanyDetails;
import org.zilker.beans.StudentDetails;

public interface PlacementDAO {
	public void insertStudentDetails(StudentDetails studentdet) throws SQLException;

	public void insertCompanyDetails(CompanyDetails companydet) throws SQLException;

	public void updateStudentDetails(StudentDetails studentDetails) throws SQLException;

	public void updateCompanyDetails(CompanyDetails companyDetails) throws SQLException;

	public void enterStudentsPlaced(int companyid, int regno) throws SQLException;

	public boolean checkEligibilty(int regno, int companyid) throws SQLException;

	public void displayCompanyDetails(int companyid) throws SQLException;

	public void displayStudentDetails(int regno) throws SQLException;

	public void displayDetailsDepartment(int flag, String deptname) throws SQLException;

	public boolean validateTeacher(String department, String password) throws SQLException;

	public void noOfStudentsPlaced(String deptName) throws SQLException;

	public void placementPercentage(String deptName) throws SQLException;
}
