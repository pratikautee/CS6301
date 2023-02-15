// Starter code for SP3.
// Version 1.0 (Fri, Feb 3).

// Change following line to your NetId

// Project by pga210001, axk190189
package axk190189;

import java.util.LinkedList;
import java.util.Queue;

public class Num implements Comparable<Num> {

    static long defaultBase = 10; // Change as needed
    long base = defaultBase; // Change as needed
    long[] list; // array to store arbitrarily large integers
    boolean isNegative; // boolean flag to represent negative numbers
    int len; // actual number of elements of array that are used; number is stored in
             // list[0..len-1]
    int numLength; // length of each long number

    public Num(String s) {
        numLength = 4;
        list = new long[(s.length()/numLength)+2];
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

    public Num(long[] n , int length)
    {
        numLength = 4;
        list = n;
        len = length;

    }


    public static Num add(Num a, Num b) {
        int aLen = a!=null? a.len:0; int bLen= b!=null?b.len:0;
        int maxLen = Math.max(aLen, bLen);
        long[] result = new long[maxLen + 1];
        long carry = 0;
        long maxPower = (long) Math.pow(10,a.numLength);
        int idx;
        for ( idx = 0; idx < maxLen; idx++) {
            long aVal = idx < a.len ? a.list[idx] : 0;
            long bVal = idx < b.len ? b.list[idx] : 0;
            long tempResult = aVal + bVal + carry;
            carry = tempResult/maxPower;
            tempResult = tempResult%maxPower;
            result[idx] = tempResult;
        }
        if(carry==1)
        {result[idx] = carry; idx++;}

        int actualLength = result[maxLen] == 0 ? maxLen : maxLen + 1;
        return new Num(result,actualLength);
    }
    public static Num product(Num a, Num b) {
        Num sum = new Num("0");
        for(int i=0;i<a.len;i++)
        {
            long carry =0;
            long pow = (long)Math.pow(10, a.numLength);
            long [] intermediateProduct = new long[a.len + b.len];
            int idx=0;
            for(int j=0;j<b.len;j++)
            {
                long res = (b.list[j]*a.list[i])+carry;
                carry = res /pow;

                res = res%pow;
                if(j==0)
                { for(int u=0;u<i;u++)
                 { intermediateProduct[idx]=0; idx++; }
                }
                intermediateProduct[idx]=res; idx++;
            }
            if(carry!=0)
            {
                intermediateProduct[idx]=carry;idx++;}

            sum = Num.add(sum,new Num(intermediateProduct,idx));
        }

        return sum;
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

    public static Num evaluateExp(String expr) {
        char[] expChars = expr.trim().toCharArray();
        Queue<String> expQueue = new LinkedList<>();
        StringBuilder digitString = new StringBuilder();
        for (char t : expChars) {
            if(Character.toString(t).equals("-")|| Character.toString(t).equals("/")){
                throw new UnsupportedOperationException("Cannot use "+Character.toString(t)+" operation." );
            }
            else if (Character.isWhitespace(t)) {
                continue;
            } else if (Character.toString(t).equals("+") || Character.toString(t).equals("*")
                    || Character.toString(t).equals("(") || Character.toString(t).equals(")")) {
                if (digitString.length() > 0) {
                    expQueue.add(digitString.toString().trim());
                    digitString = new StringBuilder();
                }
                expQueue.add(Character.toString(t));

            } else if (Character.isDigit(t)) {
                digitString.append(Character.toString(t));
            }
        }
        if (digitString.length() > 0) {
            expQueue.add(digitString.toString().trim());
            digitString = new StringBuilder();
        }
        System.out.println("QUEUE IS: " + expQueue);
        Num result = evaluateE(expQueue);
        return result;
    }

    private static Num evaluateE(Queue<String> q) {
        Num value1 = evaluateT(q);
        while (q.size()>0 && q.peek().equals("+")) {
            String op = q.remove();
            Num value2 = evaluateT(q);
            if (op.equals("+")) {
                value1 = Num.add(value1, value2);
            }
        }
        return value1;
    }

    private static Num evaluateT(Queue<String> q) {
        Num value1 = evaluateF(q);
        while (q.size()>0 && q.peek().equals("*")) {
            String op = q.remove();
            Num value2 = evaluateT(q);
            if (op.equals("*")) {
                value1 = Num.product(value1, value2);
            }
        }
        return value1;
    }

    private static Num evaluateF(Queue<String> q) {
        Num value1;
        if (q.size()>0 && q.peek().equals("(")) {
            String op = q.remove();
            value1 = evaluateE(q);
            op = q.remove();
        }
        else {
            String num = q.remove();
            value1 = new Num(num);
        }
        return value1;
    }

    public static void main(String[] args) {
        // Num x = new Num("6563232325");
        // Num y = new Num("4323232001");
        // Num z = Num.add(x, y);
        // System.out.println(z);
        // Num a = Num.product(x, y);
        // System.out.println(a);
        // if (a != null)
        //     a.printList();
        Num answer = evaluateExp("( (35 + 5)+10 + 5)*2*4"); //440
        System.out.println(answer);
        Num answer2 = evaluateExp("( (35 + 5)*10 + 5)*2*4 7"); //38070
        System.out.println(answer2);
        Num answer3 = evaluateExp("(3 + 4) *5"); //35
        System.out.println(answer3);
    }
}
