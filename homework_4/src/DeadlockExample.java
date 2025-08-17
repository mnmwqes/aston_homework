public class DeadlockExample {
    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static void main(String[] args) {
        Thread thr1 = new Thread(() -> {
            System.out.println("\nПоток thr1: Попытка захватить lock1");
            synchronized (lock1) {
                System.out.println("Поток thr1: Захватил успешно lock1");
                System.out.println("\nПоток thr1: Попытка захватить lock2");
                try {
                    Thread.sleep(1000);
                }
                catch (InterruptedException ignored) {
                }
                synchronized (lock2) {
                    System.out.println("Поток thr1: Захватил успешно lock2");
                }
            }
        });

        Thread thr2 = new Thread(() -> {
            System.out.println("\nПоток thr2: Попытка захватить lock2");
            synchronized (lock2) {
                System.out.println("Поток thr2: Захватил успешно lock2");
                try {
                    Thread.sleep(1000);
                }
                catch (InterruptedException ignored) {
                }
                System.out.println("\nПоток thr2: Попытка захватить lock1");
                synchronized (lock1) {
                    System.out.println("Поток thr2: Захватил успешно lock1");
                }
            }
        });

        thr1.start();
        thr2.start();
    }
}