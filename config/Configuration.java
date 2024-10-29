package config;

public class Configuration {

    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;
    private int vendorCount;  // New field for number of vendors
    private int customerCount; // New field for number of customers

    public Configuration(int totalTickets, int ticketReleaseRate, int customerRetrievalRate, int maxTicketCapacity, int vendorCount, int customerCount) {
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerRetrievalRate = customerRetrievalRate;
        this.maxTicketCapacity = maxTicketCapacity;
        this.vendorCount = vendorCount;
        this.customerCount = customerCount;
    }

    // Getters for the new fields
    public int getVendorCount() {
        return vendorCount;
    }

    public int getCustomerCount() {
        return customerCount;
    }

    // Existing getters and setters...

    public int getTotalTickets() {
        return totalTickets;
    }

    public void setTotalTickets(int totalTickets) throws IllegalAccessException {
        if (totalTickets < 0) {
            this.totalTickets = 0;
        } else {
            throw new IllegalAccessException("Total tickets must be a positive integer.");
        }
    }

    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    public void setMaxTicketCapacity(int maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity;
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public void setCustomerRetrievalRate(int customerRetrievalRate) {
        if (customerRetrievalRate < 0) {
            this.customerRetrievalRate = customerRetrievalRate;
        } else {
            throw new IllegalArgumentException("Customer retrieval rate must be a positive integer.");
        }
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public void setTicketReleaseRate(int ticketReleaseRate) {
        if (maxTicketCapacity > 0) {
            this.ticketReleaseRate = ticketReleaseRate;
        } else {
            throw new IllegalArgumentException("Max ticket capacity must be a positive integer.");
        }
    }

    // Method to display the configuration settings
    @Override
    public String toString() {
        return "Configuration{" +
                "totalTickets=" + totalTickets +
                ", ticketReleaseRate=" + ticketReleaseRate +
                ", customerRetrievalRate=" + customerRetrievalRate +
                ", maxTicketCapacity=" + maxTicketCapacity +
                ", vendorCount=" + vendorCount +
                ", customerCount=" + customerCount +
                '}';
    }
}
