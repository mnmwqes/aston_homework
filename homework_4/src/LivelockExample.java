public class LivelockExample {
    static class Worker {
        private boolean active = true;

        public boolean isActive() {
            return active;
        }

        public void work(Worker other) {
            while (active) {
                if (other.isActive()) {
                    System.out.println(Thread.currentThread().getName() + " уступает другому.");
                    try {
                        Thread.sleep(1000);
                    }
                    catch (InterruptedException ignored) {
                    }
                    continue;
                }
                System.out.println(Thread.currentThread().getName() + " выполняет работу.");
                active = false;
            }
        }
    }

    public static void main(String[] args) {
        Worker worker1 = new Worker();
        Worker worker2 = new Worker();

        Thread thr1 = new Thread(() -> worker1.work(worker2), "Поток thr1");
        Thread thr2 = new Thread(() -> worker2.work(worker1), "Поток thr2");

        thr1.start();
        thr2.start();
    }
}