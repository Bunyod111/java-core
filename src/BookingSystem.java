import java.io.*;
import java.util.*;

public class BookingSystem {
    private Plane plane;
    private final String DATA_FILE = "seats.txt";

    public BookingSystem() {
        plane = new Plane();
        loadFromFile();
    }

    public void showAllSeats() {
        System.out.println("\n=== Схема самолета ===");
        for (Seat seat : plane.getSeats()) {
            String status = seat.isBooked() ? "ЗАНЯТО" : "СВОБОДНО";
            System.out.printf("%-5s | %-8s | %-8s | %s\n",
                    seat.getSeatNumber(),
                    seat.getSeatClass(),
                    status,
                    seat.isBooked() ? seat.getPassengerName() : "");
        }
    }

    public void bookSeat(String number, String name) {
        Seat seat = plane.getSeatByNumber(number);
        if (seat == null) {
            System.out.println("❗ Место не найдено.");
            return;
        }
        if (seat.isBooked()) {
            System.out.println("❗ Место уже занято.");
            return;
        }
        seat.book(name);
        saveToFile();
        System.out.println("✔️ Бронь успешна.");
    }

    public void cancelBooking(String number) {
        Seat seat = plane.getSeatByNumber(number);
        if (seat == null) {
            System.out.println("❗ Место не найдено.");
            return;
        }
        if (!seat.isBooked()) {
            System.out.println("❗ Место уже свободно.");
            return;
        }
        seat.cancel();
        saveToFile();
        System.out.println("✔️ Бронь отменена.");
    }

    public void showBookingInfo(String number) {
        Seat seat = plane.getSeatByNumber(number);
        if (seat == null) {
            System.out.println("❗ Место не найдено.");
            return;
        }
        if (!seat.isBooked()) {
            System.out.println("Место свободно.");
        } else {
            System.out.printf("Место %s (%s): забронировано на имя %s\n",
                    seat.getSeatNumber(), seat.getSeatClass(), seat.getPassengerName());
        }
    }

    private void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(DATA_FILE))) {
            for (Seat seat : plane.getSeats()) {
                writer.println(seat.toString());
            }
        } catch (IOException e) {
            System.out.println("❗ Ошибка при сохранении файла.");
        }
    }

    private void loadFromFile() {
        File file = new File(DATA_FILE);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            List<Seat> loadedSeats = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                loadedSeats.add(Seat.fromString(line));
            }
            plane.getSeats().clear();
            plane.getSeats().addAll(loadedSeats);
        } catch (IOException e) {
            System.out.println("❗ Ошибка загрузки данных.");
        }
    }
}