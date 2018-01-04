package org.zilker.raxit;
import java.util.*;
import java.util.logging.Logger;

public class SquirrelCigar {
	
	private static final Logger logger = Logger.getLogger(SquirrelCigar.class.getName());
	
	 public static void main(String args[]) {
	        Scanner scn = new Scanner(System.in);
	        logger.info("Enter the day : ");
	        String day = scn.next();
	        logger.info("Enter the cigars : ");
	        int cigar = scn.nextInt();
	        if(day.equals("Saturday") || day.equals("Sunday")){
	            if(cigar >= 40){
	            	logger.info("Successful");
	            }
	            else{
	                logger.info("Unsuccessful");
	            }
	        }
	        else{
	            if(cigar >= 40 && cigar <= 60){
	                logger.info("Successful");
	            }
	            else{
	                logger.info("Unuccessful");
	            }
	        }
	    }
}
