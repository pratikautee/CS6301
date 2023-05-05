import java.util.*;

public class  Take4{

static final int T = 6;
public  void mergeSort(int[]a)
{
    int[] b = new int[a.length];
    System.arraycopy(a, 0, b, 0, a.length);
    mergeSort(a,b,0,a.length-1);
}

public  void insertionSort(int []a, int start, int end)
{  
    // System.out.println("Sorting from "+start+" to "+end);
    // System.out.println("Before");
    // for(int i = start;i<=end;i++)
    //  System.out.println(a[i]);
    
    
    int j;
    for (int p = start; p <= end; p++) {
        int tmp = a[p];
        for(j = p; j > start && tmp < a[j - 1]; j--) {
            a[j] = a[j-1];
        }
        a[j] = tmp;
 
}
// System.out.println("After");
//     for(int i = start;i<=end;i++)
//      System.out.println(a[i]);
}

public  void mergeSort(int[]a , int[]b, int p, int r)
{  if((r-p+1)<T)
    { insertionSort(a,p,r); return;}
   
    if(p<r)
    {
        int q = p+ (r-p)/2;
        mergeSort(b,a, p,q );
        mergeSort(b,a,q+1,r);
        merge(a,b,p,q,r);
    }
}
public  void merge(int[]a, int[] b, int p, int q, int r )
{
    int i =p, k=p, j = q+1;
    while(i<=q && j<=r)
    {
        if(b[i]<=b[j])
        {
            a[k++] = b[i++];
        }
        else 
         a[k++] = b[j++];
    }
    while(i<=q) a[k++] = b[i++];
    while(j<=r) a[k++] = b[j++];
} 
public static void main(String[]args)
{
    int[]a = {9,8,7,6,5,4,3,2,1};
    
    new Take4().mergeSort(a);
    System.out.println(Arrays.toString(a));
}

}