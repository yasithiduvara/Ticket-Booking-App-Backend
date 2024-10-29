import config.Configuration;
import config.ConfigurationInitializer;
import ticket.TicketPool;
import ticket.Vendor;
import ticket.Customer;

public class TicketingSystemApp {

    public static void main(String[] args) {
        // Step 1: Initialize configuration
        Configuration config = ConfigurationInitializer.initializeConfiguration();

        // Step 2: Initialize TicketPool with max capacity from configuration
        TicketPool ticketPool = new TicketPool(config.getMaxTicketCapacity());

        // Step 3: Create and start vendor threads (Producers)
        for (int i = 0; i < config.getVendorCount(); i++) {
            Vendor vendor = new Vendor(ticketPool, config.getTicketReleaseRate());
            Thread vendorThread = new Thread(vendor);
            vendorThread.start();
        }

        // Step 4: Create and start customer threads (Consumers)
        for (int i = 0; i < config.getCustomerCount(); i++) {
            Customer customer = new Customer(ticketPool, config.getCustomerRetrievalRate());
            Thread customerThread = new Thread(customer);
            customerThread.start();
        }

        // The system now runs, with vendors adding tickets and customers purchasing them.
        System.out.println("Ticketing system is running. Press Ctrl+C to stop.");
    }
}
