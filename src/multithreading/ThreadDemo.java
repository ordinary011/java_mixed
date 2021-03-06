package multithreading;

public class ThreadDemo {
    public static void main(String[] args) {
        new NewThread();

        try {
            for (int i = 5; i > 0; i--) {
                System.out.println("Main Thread " + i);
                Thread.sleep(1000);
            }
        }catch (InterruptedException e){
            System.out.println("Main thread interrupted");
        }
        System.out.println("Main thread exiting");
    }
}

class NewThread implements Runnable{
    Thread t;

    public NewThread() {
        this.t = new Thread(this, "child Thread");
        System.out.println("child thread created " + t);
        t.start();
    }

    @Override
    public void run() {
        System.out.println("child thread started");
        try {
            for(int  i= 5; i > 0; i--){
                System.out.println("Child thread " + i);
                Thread.sleep(500);
            }
        }catch (InterruptedException e){
            System.out.println("cHILD interrupted");
        }
        System.out.println("exiting child thread");
    }
}
