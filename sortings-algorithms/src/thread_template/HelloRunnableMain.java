package thread_template;

public class HelloRunnableMain {
    public static void main(String[] args) {
        HelloRunnable helloRunnable = new HelloRunnable();
        Thread thread1 = new Thread(helloRunnable);
        Thread thread2 = new Thread(helloRunnable);
        System.out.println("Main thread#" + Thread.currentThread().getName());
        for(int i=1; i < 11; i++) {
            System.out.println("Main thread#" + Thread.currentThread().getName() + " - " + i);
        }
        thread1.start();
        thread2.start();
    }
}
