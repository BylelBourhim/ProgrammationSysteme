package TP3;

public class Exercice3Multi {
    public static void main(String[] args) throws InterruptedException {
        int[] tab = {0, 2, 8, 5, 7, 6, 5, 9, 55, 6, 8, 44, 7759, 85, 625, 5, 26, 6, 9, 8, 74, 415, 12, 562, 56, 25, 5, 56, 7, -2, 65541, 65542};
        int n = 8; // Number of threads
        int segmentLength = tab.length / n;
        MultithreadingDemo[] threads = new MultithreadingDemo[n];

        long startTime = System.nanoTime(); // Start time

        for (int i = 0; i < n; i++) {
            int start = i * segmentLength;
            int end = (i == n - 1) ? tab.length : start + segmentLength;
            threads[i] = new MultithreadingDemo(tab, start, end);
            threads[i].start();
        }

        for (MultithreadingDemo thread : threads) {
            thread.join();
        }

        long endTime = System.nanoTime(); // End time
        long duration = endTime - startTime; // Calculate duration

        System.out.println("Valeur max = " + MultithreadingDemo.getMaxValue());
        System.out.println("Execution time (nanoseconds) = " + duration);
    }
}