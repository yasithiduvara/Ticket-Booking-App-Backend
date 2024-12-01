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
            Vendor vendor = new Vendor(
                    ticketPool,
                    config.getTicketReleaseRate(),
                    1000 // Example release interval in milliseconds
            );
            Thread vendorThread = new Thread(vendor, "Vendor-" + (i + 1));
            vendorThread.start();
        }

        // Step 4: Create and start customer threads (Consumers)
        for (int i = 0; i < config.getCustomerCount(); i++) {
            Customer customer = new Customer(
                    ticketPool,
                    1500 // Example retrieval interval in milliseconds
            );
            Thread customerThread = new Thread(customer, "Customer-" + (i + 1));
            customerThread.start();
        }

        // The system now runs, with vendors adding tickets and customers purchasing them.
        System.out.println("Ticketing system is running. Press Ctrl+C to stop.");
    }
}
