package org.ko.concurrency.thread;

public class CompletableRunnable {

	public static void main(String[] args) throws InterruptedException {
		Completable c = new Completable();
		Thread thread = new Thread(c, "sub");
		thread.start();
		
		thread.join();
		
		System.out.printf("%s", Thread.currentThread().getName());
		System.out.println(c.comleted);
	}
	
	private static class Completable implements Runnable {

		//volatile
		private volatile boolean comleted = false;
		public void run() {
			System.out.printf("%s", Thread.currentThread().getName());
			comleted = true;
		}
		public boolean isComleted() {
			return comleted;
		}
		public void setComleted(boolean comleted) {
			this.comleted = comleted;
		}
		
		
		
	}
}
