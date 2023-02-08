// Starter code for SP3.
// Version 1.0 (Fri, Feb 3).

// Change following line to your NetId
package axk190189;

import java.time.temporal.Temporal;
import java.util.Arrays;

public class Num implements Comparable<Num> {

    static long defaultBase = 10; // Change as needed
    long base = defaultBase; // Change as needed
    long[] list; // array to store arbitrarily large integers
    boolean isNegative; // boolean flag to represent negative numbers
    int len; // actual number of elements of array that are used; number is stored in
             // list[0..len-1]
    int numLength; // length of each long number

    public Num(String s) {
        list = new long[s.length()];
        numLength = 4;
        StringBuilder numberToString = new StringBuilder(s);
        String reverse = numberToString.reverse().toString();
        int start = 0;
        len = 0;
        while (start < reverse.length()) {
            int end = Math.min(reverse.length(), start + numLength);
            StringBuilder currString = new StringBuilder(reverse.substring(start, end));
            Long currNum = Long.parseLong(currString.reverse().toString());
            list[len] = currNum;
            len++;
            start = end;
        }
    }

    public static Num add(Num a, Num b) {
        int maxLen = Math.max(a.len, b.len);
        long[] result = new long[maxLen + 1];
        long carry = 0;
        for (int i = 0; i < maxLen; i++) {
            long aVal = i < a.len ? a.list[i] : 0;
            long bVal = i < b.len ? b.list[i] : 0;
            long tempResult = aVal + bVal + carry;
            if (tempResult >= Math.pow(10, a.numLength)) {
                carry = 1;
                tempResult = tempResult % (long) (Math.pow(10, a.numLength));
            } else {
                carry = 0;
            }
            result[i] = tempResult;
        }
        int actualLength = result[maxLen] == 0 ? maxLen : maxLen + 1;
        StringBuilder numString = new StringBuilder();
        for (int i = 0; i < actualLength - 1; i++) {
            StringBuilder tempNum = new StringBuilder(String.valueOf(result[i]));
            if (tempNum.length() < a.numLength) {
                while (tempNum.length() != a.numLength) {
                    tempNum.insert(0, "0");
                }
            }
            numString.insert(0, tempNum.toString());
        }
        numString.insert(0, String.valueOf(result[actualLength - 1]));
        return new Num(numString.toString());  
    }

    public static Num product(Num a, Num b) {
        return null;
    }

    // Return number to a string in base 10
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < len - 1; i++) {
            StringBuilder currNum = new StringBuilder(String.valueOf(list[i]));
            if (currNum.length() < numLength) {
                while (currNum.length() != numLength)
                    currNum.insert(0, "0");
            }
            result.insert(0, currNum.toString());
        }
        result.insert(0, String.valueOf(list[len - 1]));
        return result.toString();
    }

    // methods below are optional

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
    // compare "this" to "other": return +1 if this is greater, 0 if equal, -1
    // otherwise
    public int compareTo(Num other) {
        return 0;
    }

    // Output using the format "base: elements of list ..."
    public void printList() {
        System.out.print(base + " : ");
        for (int i = 0; i < len; i++) {
            System.out.print(list[i] + " ");
        }
        System.out.println();
    }

    public long base() {
        return base;
    }

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
        Num z = Num.add(x, y);
        System.out.println(z);
        Num a = Num.product(x, y);
        System.out.println(a);
        if (a != null)
            a.printList();
    }
}
