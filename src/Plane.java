import java.util.*;

public class Plane {
    private List<Seat> seats;

    public Plane() {
        seats = new ArrayList<>();
        generateSeats(); // initialize if file not present
    }

    private void generateSeats() {
        // Business: 1A–1D, 2A–2D (8 мест)
        for (int row = 1; row <= 2; row++) {
            for (char col = 'A'; col <= 'D'; col++) {
                seats.add(new Seat(row + "" + col, "Business"));
            }
        }
        // Economy: 3A–10D (32 места)
        for (int row = 3; row <= 10; row++) {
            for (char col = 'A'; col <= 'D'; col++) {
                seats.add(new Seat(row + "" + col, "Economy"));
            }
        }
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public Seat getSeatByNumber(String number) {
        for (Seat seat : seats) {
            if (seat.getSeatNumber().equalsIgnoreCase(number)) {
                return seat;
            }
        }
        return null;
    }
}