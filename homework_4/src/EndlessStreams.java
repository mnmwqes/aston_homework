public class EndlessStreams {
    private static final Object lock = new Object();
    private static boolean turnOne = true; // Чей ход: true = "1"; false = "2"

    public static void main(String[] args) {
        Thread thr1 = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    while (!turnOne) {
                        try {
                            lock.wait();
                        }
                        catch (InterruptedException ignored) {
                        }
                    }
                    System.out.print("Поток thr1.\n");
                    turnOne = false;
                    lock.notifyAll();
                }
            }
        });

        Thread thr2 = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    while (turnOne) {
                        try {
                            lock.wait();
                        }
                        catch (InterruptedException ignored) {
                        }
                    }
                    System.out.print("Поток thr2.\n");
                    turnOne = true;
                    lock.notifyAll();
                }
            }
        });

        thr1.start();
        thr2.start();
    }
}
