/** Sample starter code for merge sort.
 *  @author rbk
 *  @author SA 
 * shuffling the array after every trail takes lot of time
 * create worst case input and copy to input array after every trail
 * not the best option. but saves time
 * if space is an issue, use csgrads1.utdallas.edu server
 */

package idsa;

import java.util.Random;
import java.util.*;

public class Msort {
	public static Random random = new Random();
	public static int numTrials = 50;
	public static int[] wcInput; // inp array
	public static int threshold = 16; // threshold for insertion sort

	public static void main(String[] args) {
		int n = 10;
		int choice = 1 + random.nextInt(4);
		if (args.length > 0) {
			n = Integer.parseInt(args[0]);
		}
		if (args.length > 1) {
			choice = Integer.parseInt(args[1]);
		}
		if (args.length > 2) {
			threshold = Integer.parseInt(args[1]);
		}
		int[] arr = new int[n]; // initally inp. finally sorted
		wcInput = new int[n];
		wcInitArray(wcInput, 0, wcInput.length);
		Timer timer = new Timer();
		switch (choice) {
			case 0:
				for (int i = 0; i < numTrials; i++) {
					initArray(arr);
					mergeSort0(arr);

				}
				break;
			case 3:
				for (int i = 0; i < numTrials; i++) {
					initArray(arr);
					mergeSort3(arr);
				}
				break; 
			case 4:
				for (int i = 0; i < numTrials; i++) {
					initArray(arr);
					mergeSort4(arr);
					//System.out.println(Arrays.toString(arr));
				}
				break;
			case 5:
				for (int i = 0; i < numTrials; i++) {
					initArray(arr);
					mergeSort5(arr);
				}
				break;
			case 6:
				for (int i = 0; i < numTrials; i++) {
					initArray(arr);
					mergeSort6(arr);
					//System.out.println(Arrays.toString(arr));
				}
				break;
		}
		timer.end();
		timer.scale(numTrials);

		System.out.println("Choice: " + choice + "\n" + timer);
	}

	public static void insertionSort(int[] arr) {
		insertionSort(arr, 0, arr.length - 1);
	}

	public static void insertionSort(int[] a, int start, int end) {
		int j;
		for (int p = start; p <= end; p++) {
			int tmp = a[p];
			for (j = p; j > start && tmp < a[j - 1]; j--) {
				a[j] = a[j - 1];
			}
			a[j] = tmp;

		}
	}

	// Merge sort 0 

	public static void mergeSort0Helper(int[] a, int p, int r) {
		if (p < r) {
			int q = p + (r - p) / 2;
			mergeSort0Helper(a, p, q);
			mergeSort0Helper(a, q + 1, r);
			merge0(a, p, q, r);
		}

	}

	public static void merge0(int[] a, int p, int q, int r) {
		int[] b = new int[a.length];
		for (int i = 0; i < a.length; i++) {
			b[i] = a[i];
		}
		int i = p, k = p, j = q + 1;
		while (i <= q && j <= r) {
			if (b[i] <= b[j])
				a[k++] = b[i++];
			else
				a[k++] = b[j++];
		}
		while (i <= q)
			a[k++] = b[i++];
		while (j <= r)
			a[k++] = b[j++];
	}
// Merge Sort 3
	public static void mergeSort3Helper(int[] a, int[] b, int p, int r) {
		if (p < r) {
			int q = p + (r - p) / 2;
			mergeSort3Helper(b, a, p, q);
			mergeSort3Helper(b, a, q + 1, r);
			sharedMerge(a, b, p, q, r);
		}
	}



	// Merge Sort 4

	public static void mergeSort4Helper(int[] a, int[] b, int p, int r) {
		if ((r - p + 1) < threshold) {
			insertionSort(a, p, r);
			return;
		}

		if (p < r) {
			int q = p + (r - p) / 2;
			mergeSort4Helper(b, a, p, q);
			mergeSort4Helper(b, a, q + 1, r);
			sharedMerge(a, b, p, q, r);
		}
	}

  // Shared between 3,4,5,6
	public static void sharedMerge(int[] a, int[] b, int p, int q, int r) 
	{
		int i = p, k = p, j = q + 1;
		while (i <= q && j <= r) {
			if (b[i] <= b[j]) {
				a[k++] = b[i++];
			} else
				a[k++] = b[j++];
		}
		while (i <= q)
			a[k++] = b[i++];
		while (j <= r)
			a[k++] = b[j++];
	}	

	public static void mergeSort0(int[] arr) {
		mergeSort0Helper(arr, 0, arr.length - 1);
	}

	public static void mergeSort3(int[] arr) {
		int[] b = new int[arr.length];
		System.arraycopy(arr, 0, b, 0, arr.length);
		mergeSort3Helper(arr, b, 0, arr.length - 1);
	}

	public static void mergeSort4(int[] arr) {
		int[] b = new int[arr.length];
		System.arraycopy(arr, 0, b, 0, arr.length);
		mergeSort4Helper(arr, b, 0, arr.length - 1);
	}

	public static void mergeSort5(int[] arr) {
		int n = arr.length;

		int[] b = new int[n];
		int[] inp = arr;
		for (int i = 1; i < n; i = 2 * i) {
			for (int j = 0; j < n; j = j + 2 * i) {
				sharedMerge(b, inp, j, Math.min(n - 1, j + i - 1), Math.min(n - 1, j + 2 * i - 1));
			}
			int[] t = inp;
			inp = b;
			b = t;

		}
		if (inp != arr) {
			System.arraycopy(inp, 0, arr, 0, inp.length);
		}
	}

	public static void mergeSort6(int[] arr) {
		int n = arr.length;
		for (int j = 0; j < n; j += threshold)
			insertionSort(arr, j, Math.min(j + threshold - 1, arr.length - 1));

		int[] b = new int[n];
		int[] inp = arr;
		for (int i = threshold; i < n; i = 2 * i) {
			for (int j = 0; j < n; j = j + 2 * i) {
				sharedMerge(b, inp, j, Math.min(n - 1, j + i - 1), Math.min(n - 1, j + 2 * i - 1));
			}
			int[] t = inp;
			inp = b;
			b = t;

		}
		if (inp != arr) {
			System.arraycopy(inp, 0, arr, 0, inp.length);
		}
	}

	/*
	 * initialize the array with worst case input. Nice algorithm
	 * src:
	 * https://stackoverflow.com/questions/24594112/when-will-the-worst-case-of-
	 * merge-sort-occur
	 */
	public static void wcInitArray(int[] arr, int start, int sz) {

		if (sz == 1) {
			arr[start] = 1;
			return;
		}
		int lsz = sz / 2;
		// int rsz = (sz%2 == 0 ? lsz : lsz+1);
		wcInitArray(arr, start, lsz);
		wcInitArray(arr, start + lsz, (sz % 2 == 0 ? lsz : lsz + 1));
		for (int i = start; i < start + lsz; i++) {
			arr[i] *= 2;
		}
		for (int i = start + lsz; i < start + sz; i++) {
			arr[i] = arr[i] * 2 - 1;
		}
	}

	// copy array inp to arr
	public static void initArray(int[] arr) {
		System.arraycopy(wcInput, 0, arr, 0, arr.length);
	}

	/**
	 * Timer class for roughly calculating running time of programs
	 * 
	 * @author rbk
	 *         Usage: Timer timer = new Timer();
	 *         timer.start();
	 *         timer.end();
	 *         System.out.println(timer); // output statistics
	 */

	public static class Timer {
		long startTime, endTime, elapsedTime, memAvailable, memUsed;
		boolean ready;

		public Timer() {
			startTime = System.currentTimeMillis();
			ready = false;
		}

		public void start() {
			startTime = System.currentTimeMillis();
			ready = false;
		}

		public Timer end() {
			endTime = System.currentTimeMillis();
			elapsedTime = endTime - startTime;
			memAvailable = Runtime.getRuntime().totalMemory();
			memUsed = memAvailable - Runtime.getRuntime().freeMemory();
			ready = true;
			return this;
		}

		public long duration() {
			if (!ready) {
				end();
			}
			return elapsedTime;
		}

		public long memory() {
			if (!ready) {
				end();
			}
			return memUsed;
		}

		public void scale(int num) {
			elapsedTime /= num;
		}

		public String toString() {
			if (!ready) {
				end();
			}
			return "Time: " + elapsedTime + " msec.\n" + "Memory: " + (memUsed / 1048576) + " MB / "
					+ (memAvailable / 1048576) + " MB.";
		}
	}

	/**
	 * @author rbk : based on algorithm described in a book
	 */

	/* Shuffle the elements of an array arr[from..to] randomly */
	public static class Shuffle {

		public static void shuffle(int[] arr) {
			shuffle(arr, 0, arr.length - 1);
		}

		public static <T> void shuffle(T[] arr) {
			shuffle(arr, 0, arr.length - 1);
		}

		public static void shuffle(int[] arr, int from, int to) {
			int n = to - from + 1;
			for (int i = 1; i < n; i++) {
				int j = random.nextInt(i);
				swap(arr, i + from, j + from);
			}
		}

		public static <T> void shuffle(T[] arr, int from, int to) {
			int n = to - from + 1;
			Random random = new Random();
			for (int i = 1; i < n; i++) {
				int j = random.nextInt(i);
				swap(arr, i + from, j + from);
			}
		}

		static void swap(int[] arr, int x, int y) {
			int tmp = arr[x];
			arr[x] = arr[y];
			arr[y] = tmp;
		}

		static <T> void swap(T[] arr, int x, int y) {
			T tmp = arr[x];
			arr[x] = arr[y];
			arr[y] = tmp;
		}

		public static <T> void printArray(T[] arr, String message) {
			printArray(arr, 0, arr.length - 1, message);
		}

		public static <T> void printArray(T[] arr, int from, int to, String message) {
			System.out.print(message);
			for (int i = from; i <= to; i++) {
				System.out.print(" " + arr[i]);
			}
			System.out.println();
		}
	}
}
