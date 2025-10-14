import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BookingSystem system = new BookingSystem();
        Scanner scanner = new Scanner(System.in);
        String command;

        System.out.println("✈️ Добро пожаловать в систему бронирования авиабилетов!");

        do {
            System.out.println("\nМеню:");
            System.out.println("1 - Показать все места");
            System.out.println("2 - Забронировать место");
            System.out.println("3 - Отменить бронь");
            System.out.println("4 - Информация о месте");
            System.out.println("0 - Выход");
            System.out.print("Ваш выбор: ");
            command = scanner.nextLine();

            switch (command) {
                case "1":
                    system.showAllSeats();
                    break;
                case "2":
                    System.out.print("Введите номер места (например 3B): ");
                    String seatNum = scanner.nextLine();
                    System.out.print("Введите ФИО пассажира: ");
                    String name = scanner.nextLine();
                    system.bookSeat(seatNum, name);
                    break;
                case "3":
                    System.out.print("Введите номер места для отмены: ");
                    String cancelSeat = scanner.nextLine();
                    system.cancelBooking(cancelSeat);
                    break;
                case "4":
                    System.out.print("Введите номер места: ");
                    String infoSeat = scanner.nextLine();
                    system.showBookingInfo(infoSeat);
                    break;
                case "0":
                    System.out.println("👋 До свидания!");
                    break;
                default:
                    System.out.println("❗ Неверный ввод.");
            }

        } while (!command.equals("0"));

        scanner.close();
    }
}