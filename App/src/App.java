import java.util.HashSet;

public class App {

    public static void main(String[] args) {

        // Step 1: Create HashSet for bogie IDs
        HashSet<String> bogieIds = new HashSet<>();

        System.out.println("🚆 Train Consist Management - UC3");

        // Step 2: Add bogie IDs (including duplicates)
        bogieIds.add("BG101");
        bogieIds.add("BG102");
        bogieIds.add("BG103");
        bogieIds.add("BG101"); // Duplicate
        bogieIds.add("BG102"); // Duplicate

        // Step 3: Display unique bogie IDs
        System.out.println("\nUnique Bogie IDs in Train:");
        System.out.println(bogieIds);

        System.out.println("\nProgram continues...");
    }
}