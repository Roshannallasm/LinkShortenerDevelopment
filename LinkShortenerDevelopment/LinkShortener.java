import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LinkShortener {
    private Map<String, String> urlMappings;

    public LinkShortener() {
        this.urlMappings = new HashMap<>();
    }

    // Generate a short URL using the hashCode() method
    private String generateShortUrl(String longUrl) {
        return String.valueOf(longUrl.hashCode());
    }

    // Shorten a long URL
    public String shortenUrl(String longUrl) {
        String shortUrl = generateShortUrl(longUrl);
        
        // Check for collisions and generate a new short URL if necessary
        while (urlMappings.containsKey(shortUrl)) {
            shortUrl = generateShortUrl(longUrl + System.currentTimeMillis());
        }

        urlMappings.put(shortUrl, longUrl);
        return shortUrl;
    }

    // Expand a short URL to its original form
    public String expandUrl(String shortUrl) {
        return urlMappings.get(shortUrl);
    }

    // Display error message for invalid short URLs
    public void handleInvalidShortUrl() {
        System.out.println("Error: Invalid short URL.");
    }

    // Display error message for duplicate long URLs
    public void handleDuplicateLongUrl() {
        System.out.println("Error: This long URL is already associated with a short URL.");
    }

    public static void main(String[] args) {
        LinkShortener linkShortener = new LinkShortener();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Shorten URL");
            System.out.println("2. Expand URL");
            System.out.println("3. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter the long URL to shorten: ");
                    String longUrl = scanner.nextLine();
                    String shortUrl = linkShortener.shortenUrl(longUrl);
                    System.out.println("Shortened URL: " + shortUrl);
                    break;
                case 2:
                    System.out.print("Enter the short URL to expand: ");
                    String inputShortUrl = scanner.nextLine();
                    String expandedUrl = linkShortener.expandUrl(inputShortUrl);
                    if (expandedUrl != null) {
                        System.out.println("Expanded URL: " + expandedUrl);
                    } else {
                        linkShortener.handleInvalidShortUrl();
                    }
                    break;
                case 3:
                    System.out.println("Exiting Link Shortener. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please enter a valid option.");
            }
        }
    }
}
