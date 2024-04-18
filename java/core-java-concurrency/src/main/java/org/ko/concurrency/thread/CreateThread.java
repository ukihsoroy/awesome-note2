package org.ko.concurrency.thread;

public class CreateThread {

	public static void main(String[] args) {
		
		
//		AtomicBoolean java5
		Thread thread = new Thread(new Runnable() {
			
			public void run() {
				System.out.printf("%s", Thread.currentThread().getName());
			}
		}, "sub");
		
		thread.start();
		System.out.printf("%s", Thread.currentThread().getName());
	}
}
