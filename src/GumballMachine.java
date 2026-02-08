import java.util.ArrayList;

public class GumballMachine {

    // Gumball machine's balance
    private int balance;

    // Invalid input to machine, which will be returned when dispenser lever is
    // pressed
    private ArrayList<Integer> rejected;

    // prices of gumballs
    private final int RED_PRICE = 5;
    private final int YELLOW_PRICE = 10;

    public GumballMachine() {
        rejected = new ArrayList<>();
        balance = 0;
    }

    // Validates user input as valid currency to add to balance OR invalid input to
    // be returned later
    public void insertCoin(int coin) {
        if (coin == 5 || coin == 10 || coin == 25) {
            balance += coin;
            // showBalance(); // Requirement doesn't explicitly say to show balance on
            // insert, but TC-01 verifies Balance=5.
            // Keeping it implicitly or checking output? The test cases check "Balance = 5".
            // I'll keep showBalance() to match previous behavior and help with
            // debugging/verification.
            showBalance();
        } else {
            rejected.add(coin);
            // "Invalid coin buffered for return" - no output needed here per TC-14 visual,
            // but usually machines are silent or clunky. I'll leave it silent until
            // dispense.
        }
    }

    // Returns invalid user input
    private void returnRejectedCoins() {
        if (!rejected.isEmpty()) {
            for (Integer item : rejected) {
                System.out.println("Returned invalid coin: " + item);
            }
            rejected.clear();
        }
    }

    // Dispenses 1 red gumball if machine balance is sufficient
    public void dispensedRed() {
        // "Invalid coins are returned when a dispense lever is pressed(whether vend
        // succeeds or fails)."
        returnRejectedCoins();

        if (balance >= RED_PRICE) {
            balance -= RED_PRICE;
            System.out.println("1 red gumball dispensed.");
            showBalance();
        } else {
            System.out.println("Error. Insufficient funds. Gumball not able to be dispensed.");
            showBalance();
        }
    }

    // Dispenses 1 yellow gumball if machine balance is sufficient
    public void dispensedYellow() {
        // "Invalid coins are returned when a dispense lever is pressed(whether vend
        // succeeds or fails)."
        returnRejectedCoins();

        if (balance >= YELLOW_PRICE) {
            balance -= YELLOW_PRICE;
            System.out.println("1 yellow gumball dispensed.");
            showBalance();
        } else {
            System.out.println("Error. Insufficient funds. Gumball not able to be dispensed.");
            showBalance();
        }
    }

    // Returns remaining machine balance
    public void returnMyChange() {
        // "Return My Change returns remaining valid balance... It does not return
        // invalid coins."
        if (balance > 0) {
            System.out.println(balance + " cents have been returned.");
            balance = 0;
        } else {
            System.out.println("0 cents have been returned.");
        }
        showBalance();
    }

    // Prints out current machine balance
    public void showBalance() {
        System.out.println("Balance = " + balance);
    }

    // For testing purposes, to inspect balance without parsing stdout
    public int getBalance() {
        return balance;
    }
}
