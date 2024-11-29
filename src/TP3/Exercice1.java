package TP3;

public class Exercice1 {
    private static int x = 0;
    private static final int N = 50000;

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new IncrementTask());
        Thread thread2 = new Thread(new IncrementTask());

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("Final value of x: " + x);
    }

    static class IncrementTask implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < N; i++) {
                x++;
            }
        }
    }
}