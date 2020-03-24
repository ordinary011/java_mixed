package multithreading;

public class Synch {
    public static void main(String[] args) {
        Callme target = new Callme();
        Caller ob1 = new Caller(target, "Hello");
        Caller ob2 = new Caller(target, "Synchronized");
        Caller ob3 = new Caller(target, "world");

        try{
            ob1.t.join();
            ob2.t.join();
            ob3.t.join();
        }catch (InterruptedException e){
            System.out.println("Interrupted");
        }
        System.out.println("finished all of the threads");
    }
}

class Callme {
//    synchronized void call(String msg){
    void call(String msg){
        System.out.print("[" + msg);
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            System.out.println("interrupted");
        }
        System.out.println("]");
    }

//    synchronized void func(String msg){
    void func(String msg){
        System.out.println("in func for word: " + msg);
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            System.out.println("interrupted");
        }
        System.out.println("func for word: " + msg + " is finished");;
    }
}

class Caller implements Runnable {
    String msg;
    Callme target;
    Thread t;

    public Caller(Callme target, String msg) {
        this.msg = msg;
        this.target = target;
        t = new Thread(this);
        t.start();
    }

    public void run(){
        synchronized (target){
            target.call(msg);
            target.func(msg);
        }
    }
}