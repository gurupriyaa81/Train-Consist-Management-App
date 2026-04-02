import java.util.*;

// Abstract base class
abstract class Bogie {
    protected String id;
    protected String type;

    public Bogie(String id, String type) {
        this.id = id;
        this.type = type;
    }

    public abstract void displayDetails();
}

// Passenger Bogie
class PassengerBogie extends Bogie {
    private int seatingCapacity;

    public PassengerBogie(String id, String type, int seatingCapacity) {
        super(id, type);
        this.seatingCapacity = seatingCapacity;
    }

    public int getSeatingCapacity() {
        return seatingCapacity;
    }

    @Override
    public void displayDetails() {
        System.out.println("Passenger Bogie | ID: " + id +
                " | Type: " + type +
                " | Capacity: " + seatingCapacity);
    }
}

// Goods Bogie
class GoodsBogie extends Bogie {
    private String cargoType;

    public GoodsBogie(String id, String type, String cargoType) {
        super(id, type);
        this.cargoType = cargoType;
    }

    public String getCargoType() {
        return cargoType;
    }

    @Override
    public void displayDetails() {
        System.out.println("Goods Bogie | ID: " + id +
                " | Type: " + type +
                " | Cargo: " + cargoType);
    }
}

// Train class
class Train {
    private List<Bogie> consist = new ArrayList<>();

    // Add bogie
    public void addBogie(Bogie bogie) {
        consist.add(bogie);
        System.out.println("Bogie added successfully.");
    }

    // Remove bogie
    public void removeBogie(String id) {
        Iterator<Bogie> iterator = consist.iterator();
        while (iterator.hasNext()) {
            Bogie b = iterator.next();
            if (b.id.equals(id)) {
                iterator.remove();
                System.out.println("Bogie removed.");
                return;
            }
        }
        System.out.println("Bogie not found.");
    }

    // Display train
    public void displayTrain() {
        if (consist.isEmpty()) {
            System.out.println("Train is empty.");
            return;
        }

        for (Bogie b : consist) {
            b.displayDetails();
        }
    }

    // Total passenger capacity
    public void totalCapacity() {
        int total = 0;
        for (Bogie b : consist) {
            if (b instanceof PassengerBogie) {
                total += ((PassengerBogie) b).getSeatingCapacity();
            }
        }
        System.out.println("Total Passenger Capacity: " + total);
    }

    // Cargo summary
    public void cargoSummary() {
        Set<String> cargoTypes = new HashSet<>();
        for (Bogie b : consist) {
            if (b instanceof GoodsBogie) {
                cargoTypes.add(((GoodsBogie) b).getCargoType());
            }
        }
        System.out.println("Cargo Types: " + cargoTypes);
    }

    // Safety check
    public void safetyCheck() {
        for (Bogie b : consist) {
            if (b instanceof GoodsBogie) {
                GoodsBogie g = (GoodsBogie) b;

                if (g.getCargoType().equalsIgnoreCase("liquid")
                        && !g.type.equalsIgnoreCase("cylindrical")) {
                    System.out.println("⚠ Safety Violation: Liquid in non-cylindrical bogie (ID: " + g.id + ")");
                }
            }
        }
        System.out.println("Safety check completed.");
    }
}

// Main App
public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Train train = new Train();

        System.out.println("🚆 Welcome to Train Consist Management System");

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add Passenger Bogie");
            System.out.println("2. Add Goods Bogie");
            System.out.println("3. Remove Bogie");
            System.out.println("4. Display Train");
            System.out.println("5. Total Capacity");
            System.out.println("6. Cargo Summary");
            System.out.println("7. Safety Check");
            System.out.println("8. Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter ID: ");
                    String pid = sc.nextLine();

                    System.out.print("Enter Type (Sleeper/AC/FirstClass): ");
                    String ptype = sc.nextLine();

                    System.out.print("Enter Seating Capacity: ");
                    int cap = sc.nextInt();

                    train.addBogie(new PassengerBogie(pid, ptype, cap));
                    break;

                case 2:
                    System.out.print("Enter ID: ");
                    String gid = sc.nextLine();

                    System.out.print("Enter Type (Rectangular/Cylindrical): ");
                    String gtype = sc.nextLine();

                    System.out.print("Enter Cargo Type: ");
                    String cargo = sc.nextLine();

                    train.addBogie(new GoodsBogie(gid, gtype, cargo));
                    break;

                case 3:
                    System.out.print("Enter Bogie ID to remove: ");
                    String rid = sc.nextLine();
                    train.removeBogie(rid);
                    break;

                case 4:
                    train.displayTrain();
                    break;

                case 5:
                    train.totalCapacity();
                    break;

                case 6:
                    train.cargoSummary();
                    break;

                case 7:
                    train.safetyCheck();
                    break;

                case 8:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}