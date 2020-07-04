package org.jvm.error;

import com.sun.xml.internal.xsom.XSUnionSimpleType;

public class JVMStackSOF {

    private int stackLength = 0;

    public void stackLeak() {

        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        JVMStackSOF stack = new JVMStackSOF();
        try {
            stack.stackLeak();
        } catch (Throwable e) {
            System.out.println(stack.stackLength);
            e.printStackTrace();
        }
    }
}
