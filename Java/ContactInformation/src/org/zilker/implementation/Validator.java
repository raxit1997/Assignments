package org.zilker.implementation;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

	private static final Pattern emailPattern = Pattern.compile("^[a-z0-9._%+-]+@[a-z0-9]+\\.[a-z]{2,6}$");
	private static final Logger logger = Logger.getLogger(Validator.class.getName());

	public static boolean validateEmail(String email) {
		Matcher matcher = emailPattern.matcher(email);
		if (!matcher.matches()) {
			logger.warning("Invalid Email ID");
			return false;
		}
		return true;
	}

	public static void validateHomeNumber(String homeAreaCode, String homeCountryCode, String homeNumber) {
		if (homeNumber.length() != 8 || homeAreaCode.matches(".[a-z].*") || homeNumber.matches(".[a-z].*")
				|| homeCountryCode.matches(".[a-z].*")) {
			logger.warning("The entered home number is not of correct format");
		}
	}

	public static void validateMobileNumber(String mobileCountryCode, String mobileNumber) {
		if (mobileCountryCode.matches(".[a-z].*") || mobileNumber.matches(".[a-z].*") || mobileNumber.length() != 10) {
			logger.warning("The entered mobile number is not of correct format");
		}
	}

	public static void validateOfficeNumber(String officeExtensionCode, String officeNumber) {
		if (officeExtensionCode.matches(".[a-z].*") || officeNumber.matches(".[a-z].") || officeNumber.length() != 10) {
			logger.warning("The entered office number is not of correct format");
		}
	}

}
