package org.jvm.error;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class DeadLoack {
    public static void main(String[] args) throws IOException {
        Thread a = new Thread(()->A.method(),"a");
        Thread b = new Thread(()->B.method(),"b");

        a.start();
        b.start();

        System.in.read();
    }
}

class A {
    public static synchronized void method() {
        System.out.println("into A.method()");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        B.method();
    }
}

class B {
    public static synchronized  void method() {
        System.out.println("into B.method()");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        A.method();
    }
}


