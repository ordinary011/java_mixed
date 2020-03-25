package multithreading;

class NewThread5 implements Runnable {
    String name;
    Thread t;
    boolean suspendFlag;

    public NewThread5(String threadName) {
        name = threadName;
        t = new Thread(this, name);
        System.out.println("New thread " + t);
        suspendFlag = true;
        t.start();
    }

    @Override
    public void run() {
        try {
            for (int i = 15; i > 0; i--) {
                System.out.println(name + ": " + i);
                Thread.sleep(200);
                synchronized (this){
                    while (suspendFlag){
                        wait();
                    }
                }
            }
        }catch (InterruptedException e){
            System.out.println(name + " Interrupted");
        }
        System.out.println(name + " exiting");
    }

    synchronized void mysuspend(){
        suspendFlag = true;
    }

    synchronized void myresume(){
        suspendFlag = false;
        notify();
    }
}

public class SuspendResume {
    public static void main(String[] args) {
        NewThread5 obj1 = new NewThread5("One");
        NewThread5 obj2 = new NewThread5("Two");

        try {
            Thread.sleep(1000);
            obj1.mysuspend();
            System.out.println("suspending thread one");
            Thread.sleep(1000);
            obj1.myresume();
            System.out.println("resuming thread one");

            obj2.mysuspend();
            System.out.println("Suspending thread two");
            Thread.sleep(1000);
            obj2.myresume();
            System.out.println("resuming thread two");
        }catch (InterruptedException e){
            System.out.println("main thread interrupted");
        }

        try {
            System.out.println("waiting for threads to finish");
            obj1.t.join();
            obj2.t.join();
        }catch (InterruptedException e){
            System.out.println("main thread interrupted");
        }
        System.out.println("main thread exiting");
    }
}
