package multithreading;

public class PC {
    public static void main(String[] args) {
        Q q = new Q();
        new Producer(q);
        new Consumer(q);
    }
}

class Q {
    int n;
    boolean valueSet = false;

    synchronized int get(){
        while(!valueSet){
            try{
                wait();
            }catch (InterruptedException e){
                System.out.println("interrupted");
            }
        }
        System.out.println("Got: " + n);
        valueSet = false;
        notify();
        return n;
    }

    synchronized void put(int n){
        while(valueSet){
            try{
                wait();
            }catch (InterruptedException e){
                System.out.println("interrupted");
            }
        }
        this.n = n;
        valueSet = true;
        System.out.println("put: " + n);
        notify();
    }
}

class Producer implements Runnable{
    Q q;

    public Producer(Q q) {
        this.q = q;
        new Thread(this, "Producer").start();
    }

    @Override
    public void run() {
        int i = 0;

        while (i < 10){
            q.put(i++);
        }
    }
}

class Consumer implements Runnable {
    Q q;

    public Consumer(Q q) {
        this.q = q;
        new Thread(this, "Consumer").start();
    }

    @Override
    public void run() {
        int i = 0;
        while (i < 10){
            q.get();
            i++;
        }
    }
}