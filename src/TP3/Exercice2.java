package TP3;


public class Exercice2 {
    static int x = 0;
    private static final int N = 50000;
    static Object mutex_x = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < N; i++) {
                synchronized(mutex_x) {
                    x++;
                }
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < N; i++) {
                synchronized(mutex_x) {
                    x++;
                }

            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("x = " + x);
    }
}

class Action extends Thread {
    static final int n=5000;
    public void run() {
        for(int i=0;i<n;i++)
            synchronized(Exercice2.mutex_x) {
                Exercice2.x++;
            }
    }
}











