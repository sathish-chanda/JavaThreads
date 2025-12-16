package merge_sort;

import java.lang.reflect.Array;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;

public class MergeSortMainThread {

    private static final int MAX_THREADS = 20;

    private static class MergeSortThread extends Thread {
        MergeSortThread(int[] arr, int left, int right) {
            super(() -> MergeSortMainThread.mergeSort(arr,left,right));
            this.start();
        }
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

    public static void startMergeSortUsingThreads(int[] arr) {
        final int size = arr.length;
        boolean distributeArrayEqually = size % MAX_THREADS == 0;
        int numberOfElementPerThread = distributeArrayEqually ? size / MAX_THREADS : size / (MAX_THREADS - 1);
        numberOfElementPerThread = Math.max(numberOfElementPerThread, MAX_THREADS);
        final ArrayList<MergeSortThread> threads = new ArrayList<>();
        for(int i = 0; i < size; i += numberOfElementPerThread) {
            int remaining = size - i;
            int right = remaining < numberOfElementPerThread ? i + (remaining - 1) : i + (numberOfElementPerThread - 1);
            final MergeSortThread mst = new MergeSortThread(arr, i,right);
            threads.add(mst);
        }
        for (Thread mst : threads) {
            try {
                mst.join();
            } catch (InterruptedException ex) {
                // do nothing
                System.out.println("Got InterruptedException exception");
            }
        }

        // merge the sub arrays after getting sorted by the threads
        for (int i = 0; i < size; i += numberOfElementPerThread) {
            int mid = i == 0 ? 0 : i - 1;
            int remaining = size - 1;
            int right = remaining < numberOfElementPerThread ? i + remaining - 1 : i + numberOfElementPerThread - 1;
            merge(arr,0,mid,right);
        }
    }

    public static void main(String[] args) {
        Timer timer = new Timer();
        int size = 20000000;
        int maximum = 1000000;
        int[] arr = Helper.generateRandomArray(size,maximum);
        int[] copiedArray = Arrays.copyOf(arr, arr.length);
        timer.start();
        startMergeSortUsingThreads(arr);
        timer.stop();
        timer.display("MergeSortUsingThreads  ");
        timer.start();
        MergeSort.mergeSort(copiedArray);
        timer.stop();
        timer.display("NormalMergeSort        ");
    }
}
