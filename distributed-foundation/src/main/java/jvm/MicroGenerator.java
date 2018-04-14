package jvm;

import javassist.ClassPool;

public class MicroGenerator {
	public static void main(String[] args) throws Exception {
		for (int i = 0; ; i++) {
			generate("eu.plumbr.demo.Generated" + i);
			System.out.println(i);
		}
	}

	public static Class<?> generate(String name) throws Exception {
		ClassPool pool = ClassPool.getDefault();
		return pool.makeClass(name).toClass();
	}
}
