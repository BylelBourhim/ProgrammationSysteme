package TP3;

class MultithreadingDemo extends Thread {
    private int[] tab;
    private int start;
    private int end;
    private static int x = Integer.MIN_VALUE;
    private static final Object mutex_x = new Object();

    public MultithreadingDemo(int[] tab, int start, int end) {
        this.tab = tab;
        this.start = start;
        this.end = end;
    }

    public void run() {
        for (int i = start; i < end; i++) {
            synchronized (mutex_x) {
                if (tab[i] > x) {
                    x = tab[i];
                }
            }
        }
    }

    public static int getMaxValue() {
        return x;
    }
}