package exam.test;

public class T {
	public static void main(String[] args) {
		m(40, 4);
	}

	public static void m(long fileSize, int taskCount) {
		long perThread = fileSize / taskCount;
		long lowerBound = 0;
		long upperBound = 0;

		for (int i = taskCount - 1; i >= 0; i--) {
			lowerBound = i * perThread;
			if (i == taskCount - 1) {
				upperBound = fileSize;
			} else {
				upperBound = lowerBound + perThread - 1;
			}
			if (upperBound < 0) {
				break;
			}
			System.out.println(lowerBound + "," + upperBound);
		}
	}
}
