package thread_template;
/*
    Implementation of thread using Thread class

    Simple and easy to implements but can't extend more than one class.
 */
public class HelloThread extends Thread {
    @Override
    public void run() {
        for(int i=1; i < 11; i++) {
            System.out.println("Thread#" + this.getName() + " - " + i);
        }
    }
}
