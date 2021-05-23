package org.example;

import java.lang.ref.WeakReference;

public class WeakReferenceExample {

    public static void main(String[] args) {
        A a = new A();
        B b = new B(a);
        a = null;
        System.gc();

        System.out.println(a);
        System.out.println(b.weakA());// null
        System.out.println(b.getA()); // 在b中引用a所以没有被回收
    }

    static class A {

    }

    static class B {
        // 即只要对象被除WeakReference对象之外所有的对象解除引用后，该对象便可以被GC回收
        private WeakReference<A> weakA;
        private A a;

        public B(A a) {
            weakA = new WeakReference<>(a);
            this.a = a;
        }

        public A weakA() {
            return weakA.get();
        }

        public A getA() {
            return a;
        }

        public void setA(A a) {
            this.a = a;
        }

    }
}

