import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Seat {
    public enum Status {
        FREE,
        BOOKED,
        PAID
    }

    private String seatNumber;
    private String seatClass;
    private Status status;
    private String passengerName;
    private LocalDateTime bookingTime;
    private String departureDate; // формат: "2025-10-15 15:30"

    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Seat(String seatNumber, String seatClass) {
        this.seatNumber = seatNumber;
        this.seatClass = seatClass;
        this.status = Status.FREE;
    }

    public void book(String name) {
        this.passengerName = name;
        this.status = Status.BOOKED;
        this.bookingTime = LocalDateTime.now();
    }

    public void pay() {
        if (this.status == Status.BOOKED) {
            this.status = Status.PAID;
        }
    }

    public void cancel() {
        this.passengerName = null;
        this.status = Status.FREE;
        this.bookingTime = null;
    }

    public boolean isExpired() {
        if (status == Status.BOOKED && bookingTime != null) {
            return bookingTime.plusMinutes(24).isBefore(LocalDateTime.now());
        }
        return false;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public String getSeatClass() {
        return seatClass;
    }

    public Status getStatus() {
        return status;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    @Override
    public String toString() {
        return seatNumber + "," + seatClass + "," + status + "," +
                (passengerName == null ? "" : passengerName) + "," +
                (bookingTime == null ? "" : bookingTime.format(FORMAT)) + "," +
                (departureDate == null ? "" : departureDate);
    }

    public static Seat fromString(String line) {
        String[] parts = line.split(",", -1);
        Seat seat = new Seat(parts[0], parts[1]);
        seat.status = Status.valueOf(parts[2]);
        seat.passengerName = parts[3].isEmpty() ? null : parts[3];

        if (!parts[4].isEmpty()) {
            seat.bookingTime = LocalDateTime.parse(parts[4], FORMAT);
        }

        if (!parts[5].isEmpty()) {
            seat.departureDate = parts[5];
        }

        return seat;
    }
}