package merge_sort;

public class MergeSort {
    public static void main(String[] args) {
//        For the below input of size = 20000000 and maximum = 1000000
//
//        Start Time: 1764206734480
//        End Time  : 1764206736057
//        It took 1577 milli seconds
//
        int size = 20000000;
        int maximum = 1000000;
        int[] arr = Helper.generateRandomArray(size,maximum);
        Timer timer = new Timer();
//        System.out.println("Before Sorting the array values");
//        Helper.displayArray(arr);
        timer.start();
        mergeSort(arr);
        timer.stop();
        timer.display();
//        System.out.println("After Sorting the array values");
//        Helper.displayArray(arr);
    }

    public static void mergeSort(int[] arr) {
        if(arr == null || arr.length == 0) {
           return;
        }
        mergeSort(arr,0,arr.length-1);
    }

    public static void mergeSort(int[]  arr, int left, int right) {
        if(left < right) {
           int mid = left + (right - left)/2;
           mergeSort(arr,left,mid);
           mergeSort(arr,mid+1,right);
           merge(arr,left,mid,right);
        }
    }

    public static void merge(int[] arr, int left,int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        int i,j;
        int idx = left;
        int[] L = new int[n1];
        int[] R = new int[n2];
        for(i = 0; i < n1; i++) {
            L[i] = arr[i + left];
        }
        for(j = 0; j < n2; j++) {
            R[j] = arr[mid + 1 + j];
        }
        i = 0;
        j = 0;
        while(i < n1 && j < n2 ) {
            if(L[i] <= R[j]) {
               arr[idx++] = L[i++];
            } else {
               arr[idx++] = R[j++];
            }
        }
        while(i < n1) {
            arr[idx++] = L[i++];
        }
        while(j < n2) {
            arr[idx++] = R[j++];
        }
    }
}
