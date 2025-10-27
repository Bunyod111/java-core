// Publisher/Subscriber

import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {
    /*
     * Подписчик. Будет работать в отдельном потоке.
     */
    static class Subscriber implements Runnable {

        private LinkedBlockingQueue<String> sharedQueue;

        public Subscriber(LinkedBlockingQueue<String> queue) {
            this.sharedQueue = queue;
        }

        @Override
        public void run() {
            boolean isRunning = true;
            System.out.println("подписчик: Запущен и ждет сообщений...");

            while (isRunning) {
                try {
                    // .take() блокирует поток, пока не появится элемент
                    String message = sharedQueue.take();

                    if (message.equals("exit")) {
                        isRunning = false; // Завершаем цикл
                        System.out.println("подписчик говорит, что получил 'exit' и останавливается");
                    } else {
                        System.out.println("подписчик получил: " + message);
                    }

                } catch (InterruptedException e) {
                    System.out.println("подписчик: поток был прерван!");
                    isRunning = false;
                }
            }

            System.out.println("подписчик: Поток завершен.");
        }
    }

    /*
     * Издатель (главный поток).
     */
    public static void main(String[] args) {
        // 1. Создаем общую очередь
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<String>();
        // 2. Создаем и запускаем поток подписчика
        Subscriber subscriber = new Subscriber(queue);
        Thread subscriberThread = new Thread(subscriber);
        subscriberThread.start();

        // 3. Главный поток (Издатель) читает ввод из консоли
        System.out.println("напишите слово (или 'exit' для завершения):");
        Scanner scanner = new Scanner(System.in);
        String userInput;
        try {
            while (true) {
                userInput = scanner.nextLine();

                // .put() кладет элемент в очередь
                queue.put(userInput);

                // Если ввели "exit", выходим из цикла издателя
                if (userInput.equals("exit")) {
                    System.out.println("exit написали, значит пока");
                    break;
                }
            }
        } catch (InterruptedException e) {
            System.out.println("главный поток был закончен");
        }
        try {
            subscriberThread.join();
        } catch (InterruptedException e) {
            System.out.println("ошибка при ожидании завершения подписчика");
        }

        scanner.close();
        System.out.println("успешно всё завершилось");
    }
}