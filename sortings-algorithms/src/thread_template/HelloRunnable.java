package thread_template;

public class HelloRunnable implements Runnable {
    @Override
    public void run() {
        for (int i = 1; i < 11; i++) {
            System.out.println("ThreadRunnable-" + Thread.currentThread().getName() + " - " + i);
        }
    }
}
