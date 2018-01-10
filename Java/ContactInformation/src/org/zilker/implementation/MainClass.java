package org.zilker.implementation;

import java.sql.*;
import java.util.*;
import java.util.logging.*;

import org.zilker.beans.ContactDetails;

public class MainClass {

	public static final Logger logger = Logger.getLogger(MainClass.class.getName());

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
			try{
				ch = Integer.parseInt(scn.next());
			} catch(NumberFormatException e) {
				logger.warning("Enter integer input.");
			}
			ContactDAO cdao = new ContactDAOImpl();
			switch (ch) {
			case 1:
				try {
					ContactDetails contactDetails = new ContactDetails();
					int count = cdao.getCount();
					logger.info("Enter first name : ");
					firstname = scn.next();
					logger.info("Enter last name : ");
					lastname = scn.next();
					logger.info("Enter email ID : ");
					email = scn.next();
					if(!Validator.validateEmail(email)) {
						break;
					}
					contactDetails.setContactID(count+1);
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
						Validator.validateOfficeNumber(officeExtensionNo, officeNumber);
					}
					logger.info("Do you want to enter mobile number(1.yes 2.no) : ");
					flag = scn.nextInt();
					if (flag == 1) {
						logger.info("Enter Mobile Country Code : ");
						mobileCountryCode = scn.next();
						logger.info("Enter Mobile number : ");
						mobileNumber = scn.next();
						Validator.validateMobileNumber(mobileCountryCode, mobileNumber);
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
						Validator.validateHomeNumber(homeAreaCode, homeCountryCode, homeNumber);
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
					logger.info("Enter first name : ");
					firstname = scn.next();
					cdao.displayOne(firstname);
					logger.info("Enter last name : ");
					lastname = scn.next();
					logger.info("Enter email ID : ");
					email = scn.next();
					Validator.validateEmail(email);
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
						Validator.validateOfficeNumber(officeExtensionNo, officeNumber);
					}
					logger.info("Do you want to enter mobile number(1.yes 2.no) : ");
					flag = scn.nextInt();
					if (flag == 1) {
						logger.info("Enter Mobile Country Code : ");
						mobileCountryCode = scn.next();
						logger.info("Enter Mobile number : ");
						mobileNumber = scn.next();
						Validator.validateMobileNumber(mobileCountryCode, mobileNumber);
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
						Validator.validateHomeNumber(homeAreaCode, homeCountryCode, homeNumber);
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
			case 3:
				logger.info("  1.All\n\t2.One");
				int choice = scn.nextInt();
				if (choice == 1) {
					cdao.displayAll();
				} else {
					logger.info("Enter the first name of the contact : ");
					firstname = scn.next();
					cdao.displayOne(firstname);
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

}
