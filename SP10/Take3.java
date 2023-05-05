import java.util.*;
public class  Take3{

public static void mergeSort(int[]a)
{
    int[] b = new int[a.length];
    System.arraycopy(a, 0, b, 0, a.length);
    mergeSort(a,b,0,a.length-1);
}
public static void mergeSort(int[]a , int[]b, int p, int r)
{
    if(p<r)
    {
        int q = p+ (r-p)/2;
        mergeSort(b,a, p,q );
        mergeSort(b,a,q+1,r);
        merge(a,b,p,q,r);
    }
}
public static void merge(int[]a, int[] b, int p, int q, int r )
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
    int[]a = {1,7,3,-4,3};
    
    Take3.mergeSort(a);
    System.out.println(Arrays.toString(a));
}

}