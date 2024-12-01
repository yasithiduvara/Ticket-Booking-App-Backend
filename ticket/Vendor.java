package ticket;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Vendor implements Runnable {
    private static final AtomicInteger globalTicketId = new AtomicInteger(1);
    private static final AtomicInteger vendorIdCounter = new AtomicInteger(1);

    private final int vendorId;
    private final TicketPool ticketPool;
    private final int ticketReleaseRate;
    private final int releaseInterval;

    public Vendor(TicketPool ticketPool, int ticketReleaseRate, int releaseInterval) {
        this.vendorId = vendorIdCounter.getAndIncrement();
        this.ticketPool = ticketPool;
        this.ticketReleaseRate = ticketReleaseRate;
        this.releaseInterval = releaseInterval;
    }

    private List<Ticket> generateTickets() {
        List<Ticket> tickets = new ArrayList<>();
        for (int i = 0; i < ticketReleaseRate; i++) {
            int ticketId = globalTicketId.getAndIncrement();
            tickets.add(new Ticket(ticketId, "Event by Vendor #" + vendorId, 50.0));
        }
        return tickets;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                List<Ticket> newTickets = generateTickets();
                ticketPool.addTickets(newTickets);
                System.out.println("Vendor #" + vendorId + " released " + newTickets.size() + " tickets.");
                Thread.sleep(releaseInterval);
            } catch (InterruptedException e) {
                System.out.println("Vendor #" + vendorId + " interrupted.");
                Thread.currentThread().interrupt();
            }
        }
    }
}
