package org.jvm.error;

import java.util.ArrayList;
import java.util.List;

public class HeapOOM {
    public static void main(String[] args) {
        List<HeapOOM> list = new ArrayList<HeapOOM>();
        while(true) {
            list.add(new HeapOOM());
        }
    }
}
