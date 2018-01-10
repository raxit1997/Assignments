package org.zilker.thread;

import java.util.logging.Logger;

class WorkerThread implements Runnable {

	private String message;
	private int time;
	private static Logger logger = Logger.getLogger(WorkerThread.class.getName());
	
	public WorkerThread(String s, int time) {
		this.message = s;
		this.time = time;
	}

	public void run() {
		logger.info("Counter " + Thread.currentThread().getName().substring(14) + " is allocated for customer " + message);
		processmessage();
		logger.info("Counter " + Thread.currentThread().getName().substring(14) + " is free");
	}

	private void processmessage() {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}