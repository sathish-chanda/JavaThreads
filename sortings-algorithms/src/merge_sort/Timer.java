package merge_sort;

public class Timer {
    private long startTime;
    private long endTime;
    private long duration;
    public void start() {
        startTime = System.currentTimeMillis();
    }
    public void stop() {
        endTime = System.currentTimeMillis();
        duration();
    }
    public void duration() {
        duration = endTime - startTime;
    }
    public void display(String message) {
        System.out.print(message);
        System.out.print("\t| Start Time: " + startTime);
        System.out.print("\t| End Time  : " + endTime);
        System.out.println("\t| It took " + duration + " milli seconds");
    }
    public void display() {
        display("");
    }
}
