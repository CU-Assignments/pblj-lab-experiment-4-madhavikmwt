import java.util.*;

class TicketBookingSystem {
    private final boolean[] seats;
    
    public TicketBookingSystem(int totalSeats) {
        seats = new boolean[totalSeats];
    }
    
    public synchronized boolean bookSeat(int seatNumber, String userName) {
        if (seatNumber < 1 || seatNumber > seats.length) {
            System.out.println("Invalid seat number!");
            return false;
        }
        if (seats[seatNumber - 1]) {
            System.out.println(userName + ": Seat " + seatNumber + " is already booked!");
            return false;
        }
        seats[seatNumber - 1] = true;
        System.out.println(userName + " booked seat " + seatNumber);
        return true;
    }
}

class User extends Thread {
    private final TicketBookingSystem system;
    private final int seatNumber;
    private final String userName;
    
    public User(TicketBookingSystem system, int seatNumber, String userName, int priority) {
        this.system = system;
        this.seatNumber = seatNumber;
        this.userName = userName;
        setPriority(priority);
    }
    
    @Override
    public void run() {
        system.bookSeat(seatNumber, userName);
    }
}

public class TicketBookingApp {
    public static void main(String[] args) {
        TicketBookingSystem system = new TicketBookingSystem(5);
        
        // Test Case 1: No bookings initially
        System.out.println("No bookings yet.");
        
        // Test Case 2: Successful booking
        new User(system, 1, "Anish (VIP)", Thread.MAX_PRIORITY).start();
        new User(system, 2, "Bobby (Regular)", Thread.NORM_PRIORITY).start();
        new User(system, 3, "Charlie (VIP)", Thread.MAX_PRIORITY).start();
        
        // Test Case 3: Thread priority (VIP first)
        new User(system, 4, "Bobby (Regular)", Thread.MIN_PRIORITY).start();
        new User(system, 4, "Anish (VIP)", Thread.MAX_PRIORITY).start();
        
        // Test Case 4: Preventing double booking
        new User(system, 1, "Bobby (Regular)", Thread.NORM_PRIORITY).start();
        
        // Test Case 5: Booking after all seats taken
        new User(system, 3, "New User (Regular)", Thread.NORM_PRIORITY).start();
        
        // Test Case 6: Invalid seat selection
        new User(system, 0, "Invalid User", Thread.NORM_PRIORITY).start();
        new User(system, 6, "Out of Bounds User", Thread.NORM_PRIORITY).start();
        
        // Test Case 7: Simultaneous bookings (Concurrency Test)
        for (int i = 1; i <= 10; i++) {
            new User(system, (i % 5) + 1, "User " + i, Thread.NORM_PRIORITY).start();
        }
    }
}
//Madhavi Kumawat_22BCS12660
