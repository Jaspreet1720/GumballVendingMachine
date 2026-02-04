public class GumballMachine {

    private int balance;
    private int rejected;

    private final int RED_PRICE = 5;
    private final int YELLOW_PRICE = 10;

    private boolean isValidCoin(int coin) {

        return coin == 5 || coin == 10 || coin == 25;
    }

}
