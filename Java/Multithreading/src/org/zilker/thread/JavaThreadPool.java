package org.zilker.thread;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class JavaThreadPool {

	private static final Logger logger = Logger.getLogger(WorkerThread.class.getName());
	private static String counters, customers, time;
	static Scanner scn;
	private static int[] threadTime;
	
	public static void main(String[] args) {  
		try {
			scn = new Scanner(System.in);
			logger.info("Enter the number of counters : ");
			counters = scn.next();
	        ExecutorService executor = Executors.newFixedThreadPool(Integer.parseInt(counters));
	        logger.info("Enter the number of customers : ");
			customers = scn.next();
			threadTime = new int[Integer.parseInt(customers)];
	        for (int i = 0; i < Integer.parseInt(customers); i++) {
	        	logger.info("Enter the time(in seconds) spent in the counter for customer " + (i+1) + " : ");
	        	time = scn.next();
	        	threadTime[i] = Integer.parseInt(time) * 1000;
	          }  
	        for (int i = 0; i < Integer.parseInt(customers); i++) {
	        	Runnable worker = new WorkerThread("" + (i+1), threadTime[i]);  
	            executor.execute(worker);
	        }
	        executor.shutdown();
		} catch(NumberFormatException e) {
			logger.warning("Enter integer value.");
		}
    }  
	
}
