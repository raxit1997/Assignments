package org.zilker.implementation;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.zilker.beans.*;

public class Validator {
	
	private static final Pattern emailPattern = Pattern.compile("^[a-z0-9._%+-]+@[a-z0-9]+\\.[a-z]{2,6}$");
	private static final Logger logger = Logger.getLogger(Validator.class.getName());
	
	public static void validateStudent(StudentDetails studentDetails) {
		if(studentDetails.getRegno() > 300 || studentDetails.getRegno() < 100) {
			logger.warning("The registration number should be in the range between 100 and 300");
		}
		if(studentDetails.getCgpa() < 0 || studentDetails.getCgpa() > 10) {
			logger.warning("The cgpa should be in the range between 0 and 10");
		}
		if(!validateEmail(studentDetails.getEmail())) {
			logger.warning("The entered email is invalid. Correct Format : abc@xyz.aaa");
		}
		if(studentDetails.getArrears() < 0) {
			logger.warning("The arrears should be more than or equal to zero.");
		}
	}
	
	public static void validateCompany(CompanyDetails companyDetails) {
		if(companyDetails.getCompanyid() < 100 || companyDetails.getCompanyid() > 300) {
			logger.warning("The company ID should be in the range between 100 and 300");
		}
		if(companyDetails.getCgpaCriteria() < 0 || companyDetails.getCgpaCriteria() > 10) {
			logger.warning("The cgpa criteria should be in the range between 0 and 10");
		}
		if(companyDetails.getArrearCriteria() < 0) {
			logger.warning("The arrear criteria should be more than or equal to zero.");
		}
	}
	
	private static boolean validateEmail(String email) {
		Matcher matcher = emailPattern.matcher(email);
		return matcher.matches();
	}

}
