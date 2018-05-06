package algs;

/**
 * @see https://algs4.cs.princeton.edu/
 * @author Administrator
 *
 */
public class BinarySearch {

	public static void main(String[] args) {
		int[] arr = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		System.out.println(rank(6, arr));
	}

	private static int rank(int key, int[] arr) {
		int lo = 0;
		int hi = arr.length - 1;
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			if (key < arr[mid]) {
				hi = mid - 1;
			} else if (key > arr[mid]) {
				lo = mid + 1;
			} else {
				return mid;
			}
		}
		return -1;
	}
}
