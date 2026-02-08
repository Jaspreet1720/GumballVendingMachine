import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            GumballMachine machine = new GumballMachine();
            boolean running = true;

            System.out.println("Welcome to the Gumball Vending Machine!");
            System.out.println("Valid coins: 5, 10, 25");
            System.out.println("Commands: ");
            System.out.println("  insert <value>  : Insert a coin (e.g., 'insert 25')");
            System.out.println("  red             : Turn Red Lever (Cost: 5)");
            System.out.println("  yellow          : Turn Yellow Lever (Cost: 10)");
            System.out.println("  change          : Return My Change");
            System.out.println("  exit            : Quit the program");

            while (running) {
                System.out.print("\n> ");
                String input = scanner.nextLine().trim();
                String[] parts = input.split("\\s+");
                String command = parts[0].toLowerCase();

                switch (command) {
                    case "insert":
                        if (parts.length < 2) {
                            System.out.println("Usage: insert <value>");
                        } else {
                            try {
                                int coin = Integer.parseInt(parts[1]);
                                machine.insertCoin(coin);
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid coin value. Please enter an integer.");
                            }
                        }
                        break;
                    case "red":
                        machine.dispensedRed();
                        break;
                    case "yellow":
                        machine.dispensedYellow();
                        break;
                    case "change":
                        machine.returnMyChange();
                        break;
                    case "exit":
                    case "quit":
                        running = false;
                        System.out.println("Goodbye!");
                        break;
                    default:
                        // Try to parse as a direct coin insert
                        try {
                            int coin = Integer.parseInt(command);
                            machine.insertCoin(coin);
                        } catch (NumberFormatException e) {
                            System.out.println("Unknown command: " + command);
                            System.out.println("Try: insert <value>, red, yellow, change, exit");
                        }
                }
            }
            scanner.close();
        }
    }
}
