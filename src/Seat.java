public class Seat {
    private String seatNumber;
    private String seatClass; // "Economy", "Business"
    private boolean isBooked;
    private String passengerName;

    public Seat(String seatNumber, String seatClass) {
        this.seatNumber = seatNumber;
        this.seatClass = seatClass;
        this.isBooked = false;
        this.passengerName = null;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public String getSeatClass() {
        return seatClass;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void book(String name) {
        this.isBooked = true;
        this.passengerName = name;
    }

    public void cancel() {
        this.isBooked = false;
        this.passengerName = null;
    }

    @Override
    public String toString() {
        return seatNumber + "," + seatClass + "," + isBooked + "," + (passengerName == null ? "" : passengerName);
    }

    public static Seat fromString(String line) {
        String[] parts = line.split(",");
        Seat seat = new Seat(parts[0], parts[1]);
        if (Boolean.parseBoolean(parts[2])) {
            seat.book(parts.length > 3 ? parts[3] : "");
        }
        return seat;
    }
}