package jvm;

public class KillOOM {
	public static void main(String[] args) {
		java.util.List<int[]> l = new java.util.ArrayList();
		for (int i = 10000; i < 100000; i++) {
			try {
				l.add(new int[100_000_000]);
			} catch (Throwable t) {
				t.printStackTrace();
			}
		}
	}
}
