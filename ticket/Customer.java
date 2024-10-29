package ticket;

import java.util.concurrent.atomic.AtomicInteger;

public class Customer implements Runnable {
    private static final AtomicInteger customerIdCounter = new AtomicInteger(1);
    private final int customerId;
    private final TicketPool ticketPool;
    private final int purchaseInterval; // Interval between each ticket purchase attempt in milliseconds

    public Customer(TicketPool ticketPool, int purchaseInterval) {
        this.customerId = customerIdCounter.getAndIncrement();
        this.ticketPool = ticketPool;
        this.purchaseInterval = purchaseInterval;
    }

    // Run method to attempt ticket purchase at specified intervals
    @Override
    public void run() {
        while (true) {
            try {
                Ticket ticket = ticketPool.removeTicket();
                if (ticket != null) {
                    Logger.logInfo("Customer #" + customerId + " purchased " + ticket);
                } else {
                    Logger.logInfo("Customer #" + customerId + " attempted to purchase a ticket, but none were available.");
                }
                Thread.sleep(purchaseInterval);
            } catch (InterruptedException e) {
                Logger.logError("Customer #" + customerId + " interrupted.", e);
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
