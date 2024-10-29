package ticket;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Vendor implements Runnable {
    private static final AtomicInteger vendorIdCounter = new AtomicInteger(1);
    private final int vendorId;
    private final TicketPool ticketPool;
    private final int ticketReleaseRate; // Number of tickets to add per release
    private int releaseInterval = 0; // Interval between ticket releases in milliseconds

    public Vendor(TicketPool ticketPool, int ticketReleaseRate) {
        this.vendorId = vendorIdCounter.getAndIncrement();
        this.ticketPool = ticketPool;
        this.ticketReleaseRate = ticketReleaseRate;
        this.releaseInterval = releaseInterval;
    }

    // Method to simulate ticket creation
    private List<Ticket> generateTickets() {
        return List.of(new Ticket(vendorId, "Event #" + vendorId, 50.0)); // Example single ticket generation
    }

    // Run method to release tickets at specified intervals
    @Override
    public void run() {
        while (true) {
            try {
                List<Ticket> newTickets = generateTickets();
                ticketPool.addTickets(newTickets);
                Logger.logInfo("Vendor #" + vendorId + " released " + newTickets.size() + " tickets.");
                Thread.sleep(releaseInterval);
            } catch (InterruptedException e) {
                Logger.logError("Vendor #" + vendorId + " interrupted.", e);
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
