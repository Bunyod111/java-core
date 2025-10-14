import java.io.*;
import java.time.LocalDateTime;
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
            // Если бронь устарела — сбрасываем
            if (seat.isExpired()) {
                seat.cancel();
            }

            String status = seat.getStatus().name();
            String bookedTime = seat.getBookingTime() != null ? seat.getBookingTime().toString() : "-";
            System.out.printf("%-5s | %-8s | %-10s | %-20s | Бронь: %s\n",
                    seat.getSeatNumber(),
                    seat.getSeatClass(),
                    status,
                    seat.getPassengerName() == null ? "-" : seat.getPassengerName(),
                    bookedTime);
        }
        saveToFile(); // Сохраняем после очистки истекших броней
    }

    public void bookSeat(String number, String name) {
        Seat seat = plane.getSeatByNumber(number);
        if (seat == null) {
            System.out.println("❗ Место не найдено.");
            return;
        }
        if (seat.getStatus() != Seat.Status.FREE) {
            System.out.println("❗ Место не свободно.");
            return;
        }
        seat.book(name);
        saveToFile();
        System.out.println("✔️ Бронь создана.");
    }

    public void payForSeat(String number) {
        Seat seat = plane.getSeatByNumber(number);
        if (seat == null) {
            System.out.println("❗ Место не найдено.");
            return;
        }
        if (seat.getStatus() != Seat.Status.BOOKED) {
            System.out.println("❗ Сначала нужно забронировать.");
            return;
        }
        seat.pay();
        saveToFile();
        System.out.println("✔️ Оплата прошла успешно.");
    }

    public void cancelBooking(String number) {
        Seat seat = plane.getSeatByNumber(number);
        if (seat == null) {
            System.out.println("❗ Место не найдено.");
            return;
        }
        if (seat.getStatus() == Seat.Status.FREE) {
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
        if (seat.getStatus() == Seat.Status.FREE) {
            System.out.println("Место свободно.");
        } else {
            System.out.printf("Место %s (%s): %s\nПассажир: %s\nДата брони: %s\nВылет: %s\n",
                    seat.getSeatNumber(),
                    seat.getSeatClass(),
                    seat.getStatus(),
                    seat.getPassengerName(),
                    seat.getBookingTime(),
                    seat.getDepartureDate());
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

    public void setDepartureDateForAll(String date) {
        for (Seat seat : plane.getSeats()) {
            seat.setDepartureDate(date);
        }
        saveToFile();
    }
}