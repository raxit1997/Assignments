package org.zilker.raxit;

import java.sql.*;
import java.util.*;
import java.util.logging.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainClass {

	public static final Logger logger = Logger.getLogger(MainClass.class.getName());
	public static final Pattern emailPattern = Pattern.compile("^[a-z0-9._%+-]+@[a-z0-9]+\\.[a-z]{2,6}$");

	public static void main(String[] args) throws SQLException {
		int contactid, flag;
		String firstname = null, lastname = null, email = null, officeNumber = null, mobileNumber = null,
				homeNumber = null, officeExtensionNo = null, homeAreaCode = null, homeCountryCode = null,
				mobileCountryCode = null;
		Scanner scn = new Scanner(System.in);
		int ch = 0;
		do {
			logger.info(
					"  1.Enter new contact.\n\t2.Update a contact.\n\t3.Display contacts.\n\t4.Display contacts in order.\n\t5.Exit\nEnter your choice : ");
			ch = scn.nextInt();
			ContactDAO cdao = new ContactDAOImpl();
			switch (ch) {
			case 1:
				try {
					ContactDetails contactDetails = new ContactDetails();
					logger.info("Enter Contact ID : ");
					contactid = scn.nextInt();
					logger.info("Enter first name : ");
					firstname = scn.next();
					logger.info("Enter last name : ");
					lastname = scn.next();
					logger.info("Enter email ID : ");
					email = scn.next();
					if (!validateEmail(email)) {
						logger.info("Invalid emailid");
						break;
					}
					contactDetails.setContactID(contactid);
					contactDetails.setEmail(email);
					contactDetails.setFirstName(firstname);
					contactDetails.setLastName(lastname);
					cdao.insertDetails(contactDetails);
					logger.info("Do you want to enter office number(1.yes 2.no) : ");
					flag = scn.nextInt();
					if (flag == 1) {
						logger.info("Enter Office Extension number : ");
						officeExtensionNo = scn.next();
						logger.info("Enter Office number : ");
						officeNumber = scn.next();
						if(officeNumber.length() != 10 && officeNumber.matches(".[a-z].*")) {
							logger.info("Enter a 10 digit number");
							break;
						}
					}
					logger.info("Do you want to enter mobile number(1.yes 2.no) : ");
					flag = scn.nextInt();
					if (flag == 1) {
						logger.info("Enter Mobile Country Code : ");
						mobileCountryCode = scn.next();
						logger.info("Enter Mobile number : ");
						mobileNumber = scn.next();
						if(mobileNumber.length() != 10 && mobileNumber.matches(".[a-z].*")) {
							logger.info("Enter a 10 digit number");
							break;
						}
					}
					logger.info("Do you want to enter home number(1.yes 2.no) : ");
					flag = scn.nextInt();
					if (flag == 1) {
						logger.info("Enter Home Area Code : ");
						homeAreaCode = scn.next();
						logger.info("Enter Home Country Code : ");
						homeCountryCode = scn.next();
						logger.info("Enter Home Number : ");
						homeNumber = scn.next();
						if(homeNumber.length() != 8 && homeNumber.matches(".[a-z].*")) {
							logger.info("Enter a 8 digit number");
							break;
						}
					}
					contactDetails.setOfficeExtensionNo(officeExtensionNo);
					contactDetails.setOfficeNumber(officeNumber);
					contactDetails.setMobileCountryCode(mobileCountryCode);
					contactDetails.setMobileNumber(mobileNumber);
					contactDetails.setHomeAreaCode(homeAreaCode);
					contactDetails.setHomeCountryCode(homeCountryCode);
					contactDetails.setHomeNumber(homeNumber);
					cdao.insertNumbers(contactDetails);
				} catch (InputMismatchException e) {
					logger.info("Enter correct inputs");
				}
				break;
			case 2:
				try {
					ContactDetails contactDetails = new ContactDetails();
					logger.info("Enter Contact ID : ");
					contactid = scn.nextInt();
					cdao.displayOne(contactid);
					logger.info("Enter first name : ");
					firstname = scn.next();
					logger.info("Enter last name : ");
					lastname = scn.next();
					logger.info("Enter email ID : ");
					email = scn.next();
					if (!validateEmail(email)) {
						logger.info("Invalid emailid");
						break;
					}
					contactDetails.setContactID(contactid);
					contactDetails.setEmail(email);
					contactDetails.setFirstName(firstname);
					contactDetails.setLastName(lastname);
					cdao.updateDetails(contactDetails);
					logger.info("Do you want to enter office number(1.yes 2.no) : ");
					flag = scn.nextInt();
					if (flag == 1) {
						logger.info("Enter Office Extension number : ");
						officeExtensionNo = scn.next();
						logger.info("Enter Office number : ");
						officeNumber = scn.next();
						if(officeNumber.length() != 10 && officeNumber.matches(".[a-z].*")) {
							logger.info("Enter a 10 digit number");
							break;
						}
						contactDetails.setOfficeExtensionNo(officeExtensionNo);
						contactDetails.setOfficeNumber(officeNumber);
					}
					logger.info("Do you want to enter mobile number(1.yes 2.no) : ");
					flag = scn.nextInt();
					if (flag == 1) {
						logger.info("Enter Mobile Country Code : ");
						mobileCountryCode = scn.next();
						logger.info("Enter Mobile number : ");
						mobileNumber = scn.next();
						if(mobileNumber.length() != 10 && mobileNumber.matches(".[a-z].*")) {
							logger.info("Enter a 10 digit number");
							break;
						}
						contactDetails.setMobileCountryCode(mobileCountryCode);
						contactDetails.setMobileNumber(mobileNumber);
					}
					logger.info("Do you want to enter home number(1.yes 2.no) : ");
					flag = scn.nextInt();
					if (flag == 1) {
						logger.info("Enter Home Area Code : ");
						homeAreaCode = scn.next();
						logger.info("Enter Home Country Code : ");
						homeCountryCode = scn.next();
						logger.info("Enter Home Number : ");
						homeNumber = scn.next();
						if(homeNumber.length() != 8 && homeNumber.matches(".[a-z].*")) {
							logger.info("Enter a 8 digit number");
							break;
						}
						contactDetails.setHomeAreaCode(homeAreaCode);
						contactDetails.setHomeCountryCode(homeCountryCode);
						contactDetails.setHomeNumber(homeNumber);
					}
					cdao.insertNumbers(contactDetails);
				} catch (InputMismatchException e) {
					logger.info("Enter correct inputs");
				}
				break;
			case 3:
				logger.info("  1.All\n\t2.One");
				int choice = scn.nextInt();
				if (choice == 1) {
					cdao.displayAll();
				} else {
					logger.info("Enter the contactid : ");
					contactid = scn.nextInt();
					cdao.displayOne(contactid);
				}
				break;
			case 4:
				logger.info("  1.Sort by first name.\n\t2Sort by last name.\nEnter your choice : ");
				flag = scn.nextInt();
				cdao.displaySorted(flag);
				break;
			case 5:
				logger.info("Exiting...");
				break;
			default:
				logger.info("Enter the right choice");
				break;
			}
		} while (ch != 5);
	}

	private static boolean validateEmail(String email) {
		Matcher matcher = emailPattern.matcher(email);
		return matcher.matches();
	}

}
