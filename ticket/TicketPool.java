package ticket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TicketPool {
    private final List<Ticket> tickets;
    private final int maxCapacity;

    public TicketPool(int maxTicketCapacity) {
        this.tickets = Collections.synchronizedList(new ArrayList<>());
        this.maxCapacity = maxTicketCapacity;
    }

    public synchronized void addTickets(List<Ticket> newTickets) {
        if (tickets.size() + newTickets.size() > maxCapacity) {
            System.out.println("Cannot add tickets. Max capacity reached.");
            return;
        }
        tickets.addAll(newTickets);
        System.out.println(newTickets.size() + " tickets added. Total tickets: " + tickets.size());
    }

    public synchronized Ticket removeTicket() {
        if (tickets.isEmpty()) {
            System.out.println("No tickets available for purchase.");
            return null;
        }
        Ticket ticket = tickets.remove(0);
        System.out.println("Ticket purchased: " + ticket);
        return ticket;
    }

    public synchronized int getAvailableTicketsCount() {
        return tickets.size();
    }
}

