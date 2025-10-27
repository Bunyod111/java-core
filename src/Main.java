//Functional Interface
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
          class SimpleThreadPool {
            private final int numThreads;
            private final List<Thread> workerThreads;
            private final LinkedList<Runnable> taskQueue;
            private volatile boolean isShutdown;
            private class Worker implements Runnable {
                @Override
                public void run() {
                    while (true) {
                        Runnable task = null;
                        synchronized (taskQueue) {
                            while (taskQueue.isEmpty() && !isShutdown) {
                                try {
                                    taskQueue.wait();
                                } catch (InterruptedException e) {
                                    return;
                                }
                            }
                            if (isShutdown && taskQueue.isEmpty()) {
                                break;
                            }
                            if (!taskQueue.isEmpty()) {
                                task = taskQueue.removeFirst();
                            }
                        }
                        if (task != null) {
                            try {
                                task.run();
                            } catch (RuntimeException e) {
                                     }
                        }
                    }
                }
            }
            public SimpleThreadPool(int numThreads) {
                this.numThreads = numThreads;
                this.taskQueue = new LinkedList<>();
                this.workerThreads = new ArrayList<>(numThreads);
                this.isShutdown = false;

                for (int i = 0; i < numThreads; i++) {
                    Thread worker = new Thread(new Worker());
                    worker.setName("localpool-worker-" + i);
                    worker.start();
                    workerThreads.add(worker);
                }
            }
            public void submit(Runnable task) {
                if (isShutdown) {
                    return;
                }
                synchronized (taskQueue) {
                    taskQueue.addLast(task);
                    taskQueue.notifyAll();
                }
            }
            public void shutdown() {
                isShutdown = true;
                synchronized (taskQueue) {
                    taskQueue.notifyAll();
                }
                for (Thread thread : workerThreads) {
                    try {
                        thread.join();
                    } catch (InterruptedException e) {
                        // Игнорируем
                    }
                }
            }
        }
        System.out.println("создаем пул с 3 потоками ");
        SimpleThreadPool pool = new SimpleThreadPool(3);

        System.out.println("добавляем 10 задач ");
        for (int i = 0; i < 10; i++) {
            int taskNumber = i;
            pool.submit(() -> {
                try {
                    System.out.println("задача " + taskNumber + " начинается. поток: " + Thread.currentThread().getName() +"\n");
                    Thread.sleep(500);
                    System.out.print("Задача " + taskNumber + " финиш\n");
                } catch (InterruptedException e) {}
            });
        }
        System.out.print("задачи добавлены. вызываем shutdown()\n");
        pool.shutdown();
        System.out.println("программа завершена");
    }
}