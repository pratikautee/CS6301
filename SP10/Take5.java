import java.util.*;
public class Take5 {

   public static final int T = 4; 

    public  void insertionSort(int []a, int start, int end)
    {  
        int j;
        for (int p = start; p <= end; p++) {
            int tmp = a[p];
            for(j = p; j > start && tmp < a[j - 1]; j--) {
                a[j] = a[j-1];
            }
            a[j] = tmp;
     
    }
}




    public void mergeSort(int[]a ){

        int n = a.length;
        insertionSort(a,0, Math.min(T-1,a.length-1)); 
        
        int[]b = new int[n];
        int[]inp = a;
        for (int i = T;i<n;i = 2*i)
        {   
            for(int j = 0; j<n; j= j+2*i)
            {
                merge(b,inp, j , j+i-1,j+2*i-1);
            }
            int []t = inp;
            inp = b;
            b= t;
         
        }
        if(inp!=a)
        {
            System.arraycopy(inp, 0, a, 0, inp.length);
        }
    }
    public void merge(int[]a, int[]b, int p, int q, int r)
    {
        int i = p;
        int k = p;
        int j = q+1;
        while( (i<=q) && (j<=r))
        {   System.out.println("here");
            if(b[i]<=b[j]) 
              a[k++] = b[i++];
            else
              a[k++] = b[j++];

        }
        while(i<=q) a[k++] = b[i++];
        while(j<=r) a[k++] = b[j++];
    }

    public static void main(String[]args)
{
    int[]a = {3,4,2,1};
    
    new Take5().mergeSort(a);
    System.out.println(Arrays.toString(a));
}

    
}
