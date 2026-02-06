import java.util.ArrayList;

public class GumballMachine {

    //Gumball machine's balance
    private int balance;

    //Invalid input to machine, which will be returned when dispenser lever is pressed
    private ArrayList<String> rejected;

    //prices of gumballs
    private final int RED_PRICE = 5;
    private final int YELLOW_PRICE = 10;

    public GumballMachine(){
        rejected = new ArrayList<>();
        balance = 0;
    }

    //Validates user input as valid currency to add to balance OR invalid input to be returned later
    private boolean isValidCoin(String input) {
        try{
            int coin = Integer.parseInt(input);
            if(coin == 5 || coin == 10 || coin == 25){
                insertCoin(coin);
                return true;
            }
            else{
                rejected.add(input);
                return false;
            }
        }
        catch(NumberFormatException e){
            rejected.add(input);
            return false;
        }
    }

    //Add valid currency to machine balance
    private void insertCoin(int coin){
        balance += coin;
        showBalance();

    }

    //Returns invalid user input
    private void returnRejectedCoins(){
        for(String item : rejected){
            System.out.println(item);
        }
        rejected = new ArrayList<>();
    }

    //Dispenses 1 red gumball if machine balance is sufficient
    public void dispensedRed(){
        if(balance >= RED_PRICE){
            balance -= RED_PRICE;
            System.out.println("1 red gumball dispensed.");
            showBalance();
        }
        else{
            System.out.println("Error. Insufficient funds. Gumball not able to be dispensed.");
            showBalance();
        }
    }

    //Dispenses 1 yellow gumball if machine balance is sufficient
    public void dispensedYellow(){
        if(balance >= YELLOW_PRICE){
            balance -= YELLOW_PRICE;
            System.out.println("1 yellow gumball dispensed.");
            showBalance();
        }
        else{
            System.out.println("Error. Insufficient funds. Gumball not able to be dispensed.");
            showBalance();
        }
    }

    //Returns remaining machine balance
    public void returnMyChange(){
        System.out.println(balance + " cents have been returned.");
        balance = 0;
        showBalance();
    }

    //Prints out current machine balance
    public void showBalance(){
        System.out.println("Balance = " + balance);
    }

}
