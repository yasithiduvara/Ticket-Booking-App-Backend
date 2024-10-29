package ticket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TicketPool {

    // Thread-safe list to hold tickets
    private final List<Ticket> tickets;

    // Constructor initializes a synchronized list
    public TicketPool(int maxTicketCapacity) {
        this.tickets = Collections.synchronizedList(new ArrayList<>());
    }

    // Adds tickets to the pool, synchronized to ensure thread safety
    public void addTickets(List<Ticket> newTickets) {
        synchronized (tickets) {
            tickets.addAll(newTickets);
            System.out.println(newTickets.size() + " tickets added. Total tickets: " + tickets.size());
        }
    }

    // Retrieves and removes a ticket from the pool, returns null if no tickets are available
    public Ticket removeTicket() {
        synchronized (tickets) {
            if (tickets.isEmpty()) {
                System.out.println("No tickets available for purchase.");
                return null;
            }
            Ticket ticket = tickets.remove(0);
            System.out.println("Ticket purchased: " + ticket);
            return ticket;
        }
    }

    // Returns the current number of tickets available
    public int getAvailableTicketsCount() {
        synchronized (tickets) {
            return tickets.size();
        }
    }
}
