package TP3;

public class Exercice3{
    static int x = Integer.MIN_VALUE;
    private static final int N = 50000;
    static int[] tab = {0,2,8,5,7,6,5,9,55,6,8,44,7759,85,65541,625,5,26,6,9,8,74,415,12,562,56,25,5,56,7,-2};
    static final Object mutex_tab = new Object();


    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < tab.length/4; i++) {
                //Le premier thread s'occupe du premier quart
                synchronized (mutex_tab) {

                    if(tab[i] > x) {
                        x = tab[i];
                    }
                }
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = tab.length/4; i < tab.length/2; i++) {
                //Le deuxième thread s'occupe du deuxième quart
                synchronized (mutex_tab) {
                    if(tab[i] > x) {
                        x = tab[i];
                    }
                }

            }
        });

        Thread t3 = new Thread(() -> {
            for (int i = tab.length/2; i < 3*tab.length/4; i++) {
                //Le troisième thread s'occupe du troisième quart
                synchronized (mutex_tab) {
                    if(tab[i] > x) {
                        x = tab[i];
                    }
                }
            }
        });

        Thread t4 = new Thread(() -> {
            for (int i = 3*tab.length/4; i < tab.length; i++) {
                //Le quatrième thread s'occupe du dernier quart
                synchronized (mutex_tab) {
                    if(tab[i] > x) {
                        x = tab[i];
                    }
                }
            }
        });

        //Comparer le resultat de chaque thread et garder le plus grand



        t1.start();
        t2.start();
        t3.start();
        t4.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();

        System.out.println("x = " + x);
    }
}


