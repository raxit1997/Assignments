package org.zilker.raxit;

import java.sql.SQLException;
import java.util.*;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainClass {

	public static final Logger logger = Logger.getLogger(MainClass.class.getName());
	public static final Pattern emailPattern = Pattern.compile("^[a-z0-9._%+-]+@[a-z0-9]+\\.[a-z]{2,6}$");

	public static void main(String[] args) throws SQLException {
		String deptName, password;
		Scanner scn = new Scanner(System.in);
		int ch, i, count, companyid, regno;
		PlacementDAO pdao = new PlacementDAOImpl();
		do {
			logger.info("  1.Admin\n\t2.Student\n\t3.Company\n\t4.HOD\n\t5.Dean\n\t6.Exit\nEnter your choice : ");
			ch = scn.nextInt();
			switch (ch) {
			case 1:
				adminOptions();
				break;
			case 2:
				try {
					logger.info("Enter your registration number : ");
					regno = scn.nextInt();
					logger.info("Check your eligibility in a company\nEnter the companyID : ");
					companyid = scn.nextInt();
					if (pdao.checkEligibilty(regno, companyid)) {
						logger.info("You are eligible to sit for this company.\n");
					} else {
						logger.info("You are not eligible to sit for this company.\n");
					}
				} catch(InputMismatchException e) {
					logger.info("Enter correct inputs.");
				}
				break;
			case 3:
				try {
					logger.info("Enter the companyID : ");
					companyid = scn.nextInt();
					logger.info("Enter the number of students who got placed : ");
					count = scn.nextInt();
					for (i = 0; i < count; i++) {
						logger.info("Enter the registration number of student " + (i + 1) + " : ");
						regno = scn.nextInt();
						pdao.enterStudentsPlaced(companyid, regno);
					}
				} catch(InputMismatchException e) {
					logger.info("Enter correct inputs.");
				} catch(NullPointerException e) {
					
				}
				break;
			case 4:
				try {
					logger.info("Enter your department name : ");
					deptName = scn.next().toUpperCase();
					logger.info("Enter your password : ");
					password = scn.next();
					if (!pdao.validateTeacher(deptName, password)) {
						logger.info("Wrong department name or password");
						break;
					}
					logger.info("1.All students information\n\t2.Individual Student information\nEnter your choice : ");
					int flag = scn.nextInt();
					if (flag == 1) {
						regno = 0;
					} else {
						regno = scn.nextInt();
					}
					pdao.displayDetailsDepartment(regno, deptName);
				} catch(InputMismatchException e) {
					logger.info("Enter correct inputs.");
				}
				break;
			case 5:
				deanOptions();
				break;
			case 6:
				logger.info("Exiting...");
				break;
			default:
				logger.info("Enter the right choice");
				break;
			}
		} while (ch != 6);
	}

	public static void deanOptions() throws SQLException {
		Scanner scn = new Scanner(System.in);
		PlacementDAO pdao = new PlacementDAOImpl();
		int choice;
		String password, deptName;
		logger.info("Enter your password : ");
		password = scn.next();
		if (pdao.validateTeacher("DEAN", password)) {
			logger.info("  1.Number of students placed.\n\t2.Placement percentage");
			choice = scn.nextInt();
			switch (choice) {
			case 1:
				logger.info("  1.College level.\n\t2.Department level.\nEnter your choice : ");
				choice = scn.nextInt();
				if (choice == 1) {
					pdao.noOfStudentsPlaced("");
				} else {
					logger.info("Enter the department name: ");
					deptName = scn.next().toUpperCase();
					pdao.noOfStudentsPlaced(deptName);
				}
				break;
			case 2:
				logger.info("  1.College level.\n\t2.Department level.\nEnter your choice : ");
				choice = scn.nextInt();
				if (choice == 1) {
					pdao.placementPercentage("");
				} else {
					logger.info("Enter the department name: ");
					deptName = scn.next().toUpperCase();
					pdao.placementPercentage(deptName);
				}
				break;
			default:
				logger.info("Enter the right choice");
				break;
			}
		}
	}

	public static void adminOptions() throws SQLException {
		Scanner scn = new Scanner(System.in);
		PlacementDAO pdao = new PlacementDAOImpl();
		String deptName, studentName, companyName, email, placedStatus;
		int flag, ch, companyid, regno, arrears, arrearCriteria;
		float cgpa, cgpaCriteria;
		logger.info("  1.Enter the student details\n\t2.View the student details\n\t3.Update the student details\n\t"
				+ "4.Enter company details\n\t5.View company details\n\t6.Update company details\nEnter your choice : ");
		ch = scn.nextInt();
		switch (ch) {
		case 1:
			StudentDetails studentdet = new StudentDetails();
			try {
				logger.info("Enter the regno : ");
				regno = scn.nextInt();
				logger.info("Enter the name : ");
				studentName = scn.next();
				logger.info("Enter the department name : ");
				deptName = scn.next().toUpperCase();
				logger.info("Enter the emailid : ");
				email = scn.next();
				if (!validateEmail(email)) {
					logger.info("Invalid emailid");
					break;
				}
				logger.info("Enter the cgpa : ");
				cgpa = scn.nextFloat();
				logger.info("Enter the number of arrears : ");
				arrears = scn.nextInt();
				studentdet.setName(studentName);
				studentdet.setRegno(regno);
				studentdet.setPlacedStatus("no");
				studentdet.setCgpa(cgpa);
				studentdet.setArrears(arrears);
				studentdet.setEmail(email);
				studentdet.setDeptName(deptName);
				pdao.insertStudentDetails(studentdet);
			} catch (InputMismatchException e) {
				logger.info("Enter the correct inputs");
			}
			break;
		case 2:
			try {
				logger.info(
						"  1.All student details\n\t2.Individual student details\n\t3.Department-wise student details\nEnter the choice : ");
				flag = scn.nextInt();
				if (flag == 1) {
					pdao.displayStudentDetails(0);
				} else if (flag == 2) {
					logger.info("\nEnter the student registration number : ");
					regno = scn.nextInt();
					pdao.displayStudentDetails(regno);
				} else if (flag == 3) {
					logger.info("\nEnter the department name : ");
					deptName = scn.next().toUpperCase();
					pdao.displayDetailsDepartment(0, deptName);
				}
			} catch (InputMismatchException e) {
				logger.info("Enter integer input for choice.");
			}
			break;
		case 3:
			StudentDetails studentDetails = new StudentDetails();
			try {
				logger.info("Enter the student registration number : ");
				regno = scn.nextInt();
				pdao.displayStudentDetails(regno);
				logger.info("Enter the name : ");
				studentName = scn.next();
				logger.info("Enter the department name : ");
				deptName = scn.next().toUpperCase();
				logger.info("Enter the emailid : ");
				email = scn.next();
				if (!validateEmail(email)) {
					logger.info("Invalid emailid");
					break;
				}
				logger.info("Enter the cgpa : ");
				cgpa = scn.nextFloat();
				logger.info("Enter the number of arrears : ");
				arrears = scn.nextInt();
				studentDetails.setName(studentName);
				studentDetails.setRegno(regno);
				studentDetails.setPlacedStatus("no");
				studentDetails.setCgpa(cgpa);
				studentDetails.setArrears(arrears);
				studentDetails.setEmail(email);
				studentDetails.setDeptName(deptName);
				pdao.updateStudentDetails(studentDetails);
			} catch (InputMismatchException e) {
				logger.info("Enter the correct inputs");
			}
			break;
		case 4:
			CompanyDetails companydet = new CompanyDetails();
			try {
				logger.info("Enter the companyid : ");
				companyid = scn.nextInt();
				logger.info("Enter the name : ");
				companyName = scn.next();
				logger.info("Enter the criteria for CGPA : ");
				cgpaCriteria = scn.nextFloat();
				logger.info("Enter the criteria for maximum arrears : ");
				arrearCriteria = scn.nextInt();
				companydet.setCompanyid(companyid);
				companydet.setName(companyName);
				companydet.setCgpaCriteria(cgpaCriteria);
				companydet.setArrearCriteria(arrearCriteria);
				pdao.insertCompanyDetails(companydet);
			} catch (InputMismatchException e) {
				logger.info("Enter the correct inputs");
			}
			break;
		case 5:
			try {
				logger.info("  1.All company information\n\t2.Single company information\nEnter the choice : ");
				flag = scn.nextInt();
				if (flag == 1) {
					pdao.displayCompanyDetails(0);
				} else if (flag == 2) {
					logger.info("Enter the companyID : ");
					companyid = scn.nextInt();
					pdao.displayCompanyDetails(companyid);
				}
			} catch (InputMismatchException e) {
				logger.info("Enter integer for choice.");
			}
			break;
		case 6:
			CompanyDetails companyDetails = new CompanyDetails();
			try {
				logger.info("Enter the companyid : ");
				companyid = scn.nextInt();
				pdao.displayCompanyDetails(companyid);
				logger.info("Enter the name : ");
				companyName = scn.next();
				logger.info("Enter the criteria for CGPA : ");
				cgpaCriteria = scn.nextFloat();
				logger.info("Enter the criteria for maximum arrears : ");
				arrearCriteria = scn.nextInt();
				companyDetails.setCompanyid(companyid);
				companyDetails.setName(companyName);
				companyDetails.setCgpaCriteria(cgpaCriteria);
				companyDetails.setArrearCriteria(arrearCriteria);
				pdao.updateCompanyDetails(companyDetails);
			} catch (InputMismatchException e) {
				logger.info("Enter the correct inputs");
			}
			break;
		default:
			logger.info("Enter the right choice");
			break;
		}
	}

	private static boolean validateEmail(String email) {
		Matcher matcher = emailPattern.matcher(email);
		return matcher.matches();
	}

}
