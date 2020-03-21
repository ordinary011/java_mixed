package multithreading;

public class ThreadJoinDemo {
    public static void main(String[] args) {
        NewThread3 ob1 = new NewThread3("One");
//        NewThread3 ob2 = new NewThread3("Two");
//        NewThread3 ob3 = new NewThread3("Three");

        try {
            System.out.println("Waiting for thread to finish");
            ob1.t.join();
//            ob2.t.join();
//            ob3.t.join();
        }catch (InterruptedException e){
            System.out.println("Main thread interrupted");
        }

        System.out.println("main thread exiting");
    }
}


class NewThread3 implements Runnable{
    Thread t;
    String name;

    public NewThread3(String threadname) {
        name = threadname;
        t = new Thread(this, name);
        System.out.println("New thread " + t);
        t.start();
    }

    @Override
    public void run() {
        try {
            for(int  i= 10; i > 0; i--){
                System.out.println(name + ": " + i);
                Thread.sleep(1000);
            }
        }catch (InterruptedException e){
            System.out.println(name + ": interrupted");
        }
        System.out.println(name + ": exiting");
    }
}