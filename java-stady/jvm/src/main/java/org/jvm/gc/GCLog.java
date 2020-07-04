package org.jvm.gc;

/**
 * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
 * -XX:MaxTenuringThreshold=5 晋升老年代的最大阀值（理论上的最大值，jvm可以自动调节，最大值不会超过5，也可能提前晋升到老边年代）
 *      默认值15，CMS中6，G1中15
 */
public class GCLog {
    public static void main(String[] args) {
        int size = 1024*1024*3;
        byte[] b1 = new byte[size];
        byte[] b2 = new byte[size];
        byte[] b3 = new byte[size];
//        byte[] b4 = new byte[size];
        System.out.println("ending...");
    }
}
/*
Parallel Scavenge
新生代gc前->新生代gc后(新生代总大小，8:1:1 总大小9M，一个survivor被浪费掉) 堆gc前->堆gc后(堆总大小，19M，一个survivor被浪费掉)
 */
