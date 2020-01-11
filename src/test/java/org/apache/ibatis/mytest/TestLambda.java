package org.apache.ibatis.mytest;


import org.junit.Test;

import java.util.Random;

public class TestLambda {

  @Test
  public void testDemo(){
    TestDemo t = new TestDemo();
  }
  public static class TestDemo {

    static  {
      tryImplementation(TestDemo::hello1);
      tryImplementation(TestDemo::hello2);
      tryImplementation(TestDemo::hello3);
    }

    public static synchronized void hello1() {
      try {
        Thread.sleep(5000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("hello1");
    }

    public static synchronized void hello2() {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("hello2");
    }

    public static synchronized void hello3() {
      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("hello3");
    }

    private static void tryImplementation(Runnable runnable) {
      try {
        runnable.run();
      } catch (Throwable t) {
        // ignore
      }
    }
  }
}
