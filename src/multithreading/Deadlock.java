package multithreading;

class A {
    synchronized void foo(B b){
        String name = Thread.currentThread().getName();
        System.out.println(name + " entered A.foo");

        try {
            Thread.sleep(3000);
        }catch (InterruptedException e){
            System.out.println("A interrupted");
        }

        System.out.println(name + " trying to call B.last()");
        b.last();
    }

    synchronized void last(){
        System.out.println("Inside A.last()");
    }
}

class B {
    synchronized void bar(A a){
        String name = Thread.currentThread().getName();
        System.out.println(name + " entered B.bar");

        try {
            Thread.sleep(3000);
        }catch (InterruptedException e){
            System.out.println("B interrupted");
        }

        System.out.println(name + " trying to call A.last()");
        a.last();
    }

    synchronized void last(){
        System.out.println("Inside B.last()");
    }
}

public class Deadlock implements Runnable {
    A a = new A();
    B b = new B();

    public Deadlock() {
        Thread.currentThread().setName("MainThread");
        Thread t = new Thread(this, "RacingThread");
        t.start();

        a.foo(b);
        System.out.println("back in main thread");
    }

    @Override
    public void run() {
        b.bar(a);
        System.out.println("back in other thread");
    }

    public static void main(String[] args) {
        new Deadlock();
    }
}
