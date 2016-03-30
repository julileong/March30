package com.day7.multithreads;

public class MyThread implements Runnable {

	Thread t; // To create new thread
	String name;
	static String data;	
	
	MyThread(String name) {
		this.name = name;
		t = new Thread(this, name);
		System.out.println(t);
		t.start();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (int i = 0; i < data.length(); i++) {
			System.out.println(name + " : " + (char)(data.charAt(i) - 32));
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(name + "about to exit.....");
	}
	
	//hello
	public static void main(String[] args) {
		data = args[0];
		MyThread m1 = new MyThread("One");
		for (int i = data.length() - 1; i >= 0; i--) {
			System.out.println("Main: " + data.charAt(i));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
