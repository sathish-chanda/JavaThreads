package thread_template;

public class HelloThreadMain {
    public static void main(String[] args) {
        HelloThread thread1 = new HelloThread();
        HelloThread thread2 = new HelloThread();
        System.out.println("Main thread#" + Thread.currentThread().getName());
        for(int i=1; i < 11; i++) {
            System.out.println("Main thread#" + Thread.currentThread().getName() + " - " + i);
        }
        thread1.start();
        thread2.start();
    }
}
