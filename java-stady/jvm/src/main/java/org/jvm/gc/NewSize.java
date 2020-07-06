package org.jvm.gc;

/**
 * -Xms20m -Xmx20m -Xmn2m -XX:SurvivorRatio=2 -XX:+PrintGCDetails
 */
public class NewSize {
    public static void main(String[] args) {
        byte[] bytes = null;
        for (int i = 0; i < 10; i++) {
            bytes = new byte[1 * 1024 * 1024];
        }
    }
}
