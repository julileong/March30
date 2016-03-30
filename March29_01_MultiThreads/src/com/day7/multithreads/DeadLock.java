package com.day7.multithreads;

class A
{
  synchronized void show(B b)
  {
    String name = Thread.currentThread().getName();
    System.out.println(name + " Entered A.show()");
    try
    {
      Thread.sleep(1000);
    } catch(Exception e)
      {
        System.out.println("A interrupted");
      }
    System.out.println(name + " trying to call B.last()");
    b.last();  // Blocked 
  }

  synchronized void last()
  {
    System.out.println("Inside A.last()");
  }
}

class B
{
  synchronized void show(A a)
  {
    String name = Thread.currentThread().getName();
    System.out.println(name + " Entered B.show()");
    try
    {     Thread.sleep(1000);
    } catch(Exception e)
      {
        System.out.println(" B interrupted");
      }
    System.out.println(name + " trying to call A.last()");
    a.last();
  }

  synchronized void last()
  {
    System.out.println("Inside B.last()");
  }
}

class DeadLock implements Runnable
{
   A a;
   B b;
  DeadLock()
  {
    a = new A();
    b = new B();
    Thread.currentThread().setName("Main Thread");
    Thread t = new Thread(this,"Child Thread");
    t.start();
    a.show(b);
    System.out.println("Back in Main Thread");
  }

  public void run()
  {
    b.show(a);
    System.out.println("Back in other Thread");
  }

  public static void main(String a[])
  {
    new DeadLock();
  }
}