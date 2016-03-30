package com.day7.multithreads;

public class Sync implements Runnable{

	Sync sync;
	Thread t;
	String name;
	static double num;
	
	Sync() {
		
	}
	
	Sync(String name, Sync sync) {
		this.sync = sync;
		this.name = name;
		t = new Thread(this, name);
		System.out.println(t);
		t.start();
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		sync.calc(name);
	}

	//Resource to be locked with the current thread
	public void calc(String name) {
		System.out.println(name + "About to start the calculation");
		synchronized(this) {
			double result = num * num;
			System.out.println(name + "About to print the result");
			
			try {
				Thread.sleep(2500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(name + "The result is: " + result);
		}
	}
	
	public static void main(String[] args) {
		num = Double.parseDouble(args[0]);
		Sync sync = new Sync();
		Sync s1 = new Sync("One ", sync);
		Sync s2 = new Sync("Two ", sync);
		Sync s3 = new Sync("Three ", sync);
	}
}
