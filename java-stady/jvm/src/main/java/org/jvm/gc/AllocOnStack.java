package org.jvm.gc;

/**
 * -Xms10M -Xmx10M -XX:+PrintCommandLineFlags -XX:+PrintGC -XX:+DoEscapeAnalysis -XX:+EliminateAllocations -XX:-UseTLAB
 * 栈上分配，没实验出来，还是执行GC了
 */
public class AllocOnStack {
    public static class Person{
        public int age;
        public String name;
    }

    public static void alloc(){
        Person p = new Person();
        p.age=20;
        p.name="tom";
    }

    public static void main(String[] args) {
        for(int i=0;i<100000000;i++){
            alloc();
        }
        System.out.println("ending");
    }
}
