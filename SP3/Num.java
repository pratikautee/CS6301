
// Starter code for SP3.
// Version 1.0 (Fri, Feb 3).

// Change following line to your NetId
package idsa;

import java.util.Arrays;

public class Num  implements Comparable<Num> {

    static long defaultBase = 10;  // Change as needed
    long base = defaultBase;  // Change as needed
    long[] list;  // array to store arbitrarily large integers
    boolean isNegative;  // boolean flag to represent negative numbers
    int len;  // actual number of elements of array that are used;  number is stored in list[0..len-1]
    
    public Num(String s) {
        s = s.trim();
        list = new long[s.length()];
        for (int idx = 0; idx < s.length(); idx++) {
            try{
                list[idx] = Long.parseLong(String.valueOf(s.charAt(idx)));
            }
            catch (NumberFormatException e) {
                continue;
            }
        }
        len = list.length;
    }


    public static Num add(Num a, Num b) {
        int maxLen = Math.min(a.len, b.len);
        long[] addResult = new long[maxLen + 1];
        long carry = 0;
        //TODO
	return null;
    }


    public static Num product(Num a, Num b) {
	return null;
    }
	
	// Return number to a string in base 10
    public String toString() {
        String numString = new String();
        for (long n: list){
            numString = numString.concat(String.valueOf(n));
        }
        return numString;
    }

	//  methods below are optional
	
	public static Num subtract(Num a, Num b) {
	return null;
    }
    // Use divide and conquer
    public static Num power(Num a, long n) {
	return null;
    }

    // Use divide and conquer or division algorithm to calculate a/b
    public static Num divide(Num a, Num b) {
	return null;
    }


    // Utility functions
    // compare "this" to "other": return +1 if this is greater, 0 if equal, -1 otherwise
    public int compareTo(Num other) {
	return 0;
    }
    
    // Output using the format "base: elements of list ..."
    public void printList() {
		System.out.print(defaultBase + " : ");
		for(int i=0; i<list.length; i++) {
			System.out.print(list[i] + " ");
		}
	System.out.println();
    }
    


    public long base() { return base; }

    // Return number equal to "this" number, in base=newBase
    public Num convertBase(int newBase) {
	return null;
    }

    // Divide by 2, for using in binary search
    public Num by2() {
	return null;
    }

 


    public static void main(String[] args) {
	Num x = new Num("8888888888888");
	Num y = new Num("2");
	// Num z = Num.add(x, y);
	// System.out.println(z);
	// Num a = Num.product(x, y);
	System.out.println(x.toString());
    // if(a != null) a.printList();
    x.printList();
    y.printList();
    
    }
}
