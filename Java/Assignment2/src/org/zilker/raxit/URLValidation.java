package org.zilker.raxit;

import java.util.*;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLValidation {

	public static final Logger logger = Logger.getLogger(URLValidation.class.getName());
	// Regular expression for URL Validation
	public static final Pattern URLPattern = Pattern
			.compile("^(http://|https://)(www.)?([a-zA-Z0-9]+)[\\.][\\/a-z0-9\\.!@%^&*()-=?,]*$");

	// Driver function
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		logger.info("Enter the URL : ");
		String url = scn.next();
		Matcher matcher = URLPattern.matcher(url);
		if (matcher.matches()) {
			logger.info("URL is valid");
		} else {
			logger.info("URL is invalid");
		}
		scn.close();
	}

}
