package org.example;

public class Javap {
    private long value;

    public synchronized  void incr() {
        ++value;
    }
}
