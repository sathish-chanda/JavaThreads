package merge_sort;

public class Helper {

    public static int[] generateRandomArray(int size, int maximum) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * maximum);
        }
        return arr;
    }

    public static void displayArray(int[] arr) {
        for(int i=0; i < arr.length; i++) {
            System.out.println( i + " -> " + arr[i]);
        }
    }
}
