package jvm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/*
 * 	VM Args:-Xms20m -Xmx20m -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=E:/cms.dump
 * 	VM Arags:-Xmx13m
 */
public class OutOfMemeroy {
	public static final int SIZE = 2*1024*1024;
	public static void main(String[] args) throws IOException {
		int[] i = new int[SIZE];
	}
}
