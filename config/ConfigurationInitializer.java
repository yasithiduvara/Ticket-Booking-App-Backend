package config;

import java.util.Scanner;

public class ConfigurationInitializer {

    public static Configuration initializeConfiguration(){
        Scanner scanner = new Scanner(System.in);

        int totalTickets = getPositiveInt(scanner, "Enter number of tickets to be placed: ");
        int ticketReleaseRate = getPositiveInt(scanner, "Enter ticket release rate: ");
        int customerRetrievalRate = getPositiveInt(scanner, "Enter customer retrieval rate: ");
        int maxTicketCapacity = getPositiveInt(scanner, "Enter max ticket capacity: ");
        int vendorCount = getPositiveInt(scanner, "Enter number of vendors: "); // New input for vendor count
        int customerCount = getPositiveInt(scanner, "Enter number of customers: "); // New input for customer count

        Configuration config = new Configuration(totalTickets, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity, vendorCount, customerCount);

        System.out.println("Configuration initialized successfully:\n" + config);

        return config;
    }

    // Helper method to prompt and validate positive integer input
    private static int getPositiveInt(Scanner scanner, String prompt) {
        int value;

        while (true) {
            System.out.print(prompt);
            try {
                value = Integer.parseInt(scanner.nextLine());
                if (value > 0) {
                    return value;
                } else {
                    System.out.println("Please enter a positive integer.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
    }
}
