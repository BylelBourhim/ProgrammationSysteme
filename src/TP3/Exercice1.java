package TP3;

public class Exercice1 {
    private static int x = 0;
    private static final int N = 50000;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < N; i++) {
                x++;
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < N; i++) {
                x++;
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("x = " + x);
    }
}