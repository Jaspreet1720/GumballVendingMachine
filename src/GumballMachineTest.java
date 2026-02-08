
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class GumballMachineTest {

    private static int passed = 0;
    private static int failed = 0;

    public static void main(String[] args) {
        System.out.println("Starting Gumball Machine Tests...");

        runTest("TC-01: Verify basic valid coin acceptance (nickel)", GumballMachineTest::test_TC01);
        runTest("TC-02: Verify basic valid coin acceptance (quarter)", GumballMachineTest::test_TC02);
        runTest("TC-03: Verify multiple valid coins accumulate correctly", GumballMachineTest::test_TC03);
        runTest("TC-04: Verify dispensing Red with exact payment", GumballMachineTest::test_TC04);
        runTest("TC-05: Verify dispensing Yellow with exact payment", GumballMachineTest::test_TC05);
        runTest("TC-06: Verify dispensing Red with excess balance leaves remainder", GumballMachineTest::test_TC06);
        runTest("TC-07: Verify dispensing Yellow with excess balance leaves remainder", GumballMachineTest::test_TC07);
        runTest("TC-08: Verify user can dispense multiple times from one balance", GumballMachineTest::test_TC08);
        runTest("TC-09: Verify dispense fails when no money inserted", GumballMachineTest::test_TC09);
        runTest("TC-10: Verify dispense fails when balance is insufficient", GumballMachineTest::test_TC10);
        runTest("TC-11: Verify change return behavior with zero balance", GumballMachineTest::test_TC11);
        runTest("TC-12: Verify assignment example scenario", GumballMachineTest::test_TC12);
        runTest("TC-13: Verify Return My Change clears balance and returns correct amount",
                GumballMachineTest::test_TC13);
        runTest("TC-14: Verify invalid coin does not increase balance", GumballMachineTest::test_TC14);
        runTest("TC-15: Verify invalid coin is returned on dispense lever press even if vend fails",
                GumballMachineTest::test_TC15);
        runTest("TC-16: Verify Return My Change does not return invalid coins", GumballMachineTest::test_TC16);

        System.out.println("\nTest Summary:");
        System.out.println("Passed: " + passed);
        System.out.println("Failed: " + failed);

        if (failed > 0) {
            System.out.println("SOME TESTS FAILED.");
            System.exit(1);
        } else {
            System.out.println("ALL TESTS PASSED.");
        }
    }

    private static void runTest(String name, Runnable test) {
        System.out.print(name + "... ");
        try {
            test.run();
            System.out.println("PASS");
            passed++;
        } catch (AssertionError e) {
            System.out.println("FAIL");
            System.out.println("  Reason: " + e.getMessage());
            failed++;
        } catch (Exception e) {
            System.out.println("FAIL");
            System.out.println("  Exception: " + e.toString());
            e.printStackTrace();
            failed++;
        }
    }

    // --- Helper for capturing stdout ---
    private static String captureOutput(Runnable action) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(baos));
        try {
            action.run();
        } finally {
            System.setOut(originalOut);
        }
        return baos.toString();
    }

    private static void assertContains(String output, String expected) {
        if (!output.contains(expected)) {
            throw new AssertionError("Expected output to contain '" + expected + "', but got: " + output.trim());
        }
    }

    private static void assertEquals(int expected, int actual) {
        if (expected != actual) {
            throw new AssertionError("Expected " + expected + " but got " + actual);
        }
    }

    // --- Tests ---

    private static void test_TC01() {
        GumballMachine gm = new GumballMachine();
        gm.insertCoin(5);
        assertEquals(5, gm.getBalance());
    }

    private static void test_TC02() {
        GumballMachine gm = new GumballMachine();
        gm.insertCoin(25);
        assertEquals(25, gm.getBalance());
    }

    private static void test_TC03() {
        GumballMachine gm = new GumballMachine();
        gm.insertCoin(25);
        gm.insertCoin(10);
        gm.insertCoin(5);
        assertEquals(40, gm.getBalance());
    }

    private static void test_TC04() {
        GumballMachine gm = new GumballMachine();
        gm.insertCoin(5);
        String out = captureOutput(gm::dispensedRed);
        assertContains(out, "1 red gumball dispensed");
        assertEquals(0, gm.getBalance());
    }

    private static void test_TC05() {
        GumballMachine gm = new GumballMachine();
        gm.insertCoin(10);
        String out = captureOutput(gm::dispensedYellow);
        assertContains(out, "1 yellow gumball dispensed");
        assertEquals(0, gm.getBalance());
    }

    private static void test_TC06() {
        GumballMachine gm = new GumballMachine();
        gm.insertCoin(25);
        String out = captureOutput(gm::dispensedRed);
        assertContains(out, "1 red gumball dispensed");
        assertEquals(20, gm.getBalance());
    }

    private static void test_TC07() {
        GumballMachine gm = new GumballMachine();
        gm.insertCoin(25);
        String out = captureOutput(gm::dispensedYellow);
        assertContains(out, "1 yellow gumball dispensed");
        assertEquals(15, gm.getBalance());
    }

    private static void test_TC08() {
        GumballMachine gm = new GumballMachine();
        gm.insertCoin(10);

        String out1 = captureOutput(gm::dispensedRed);
        assertContains(out1, "1 red gumball dispensed");
        assertEquals(5, gm.getBalance());

        String out2 = captureOutput(gm::dispensedRed);
        assertContains(out2, "1 red gumball dispensed");
        assertEquals(0, gm.getBalance());
    }

    private static void test_TC09() {
        GumballMachine gm = new GumballMachine();
        String out = captureOutput(gm::dispensedRed);
        assertContains(out, "Insufficient funds"); // Or "Gumball not able to be dispensed"
        assertEquals(0, gm.getBalance());
    }

    private static void test_TC10() {
        GumballMachine gm = new GumballMachine();
        gm.insertCoin(5);
        String out = captureOutput(gm::dispensedYellow);
        assertContains(out, "Insufficient funds");
        assertEquals(5, gm.getBalance());
    }

    private static void test_TC11() {
        GumballMachine gm = new GumballMachine();
        String out = captureOutput(gm::returnMyChange);
        assertContains(out, "0 cents have been returned"); // Adjust based on implementation
        assertEquals(0, gm.getBalance());
    }

    private static void test_TC12() {
        GumballMachine gm = new GumballMachine();
        gm.insertCoin(25);

        captureOutput(gm::dispensedRed); // Leaves 20
        captureOutput(gm::dispensedRed); // Leaves 15

        String out = captureOutput(gm::returnMyChange);
        assertContains(out, "15 cents have been returned");
        assertEquals(0, gm.getBalance());
    }

    private static void test_TC13() {
        GumballMachine gm = new GumballMachine();
        gm.insertCoin(10);
        String out = captureOutput(gm::returnMyChange);
        assertContains(out, "10 cents have been returned");
        assertEquals(0, gm.getBalance());
    }

    private static void test_TC14() {
        GumballMachine gm = new GumballMachine();
        gm.insertCoin(7); // Invalid
        assertEquals(0, gm.getBalance());

        // Ensure nothing returned yet
        // We can't easily check internal state without reflection or getters,
        // but verification happens in next steps or by ensuring balance didn't change.
    }

    private static void test_TC15() {
        GumballMachine gm = new GumballMachine();
        gm.insertCoin(7);

        // Press Red -> Should fail vend AND return 7
        String out = captureOutput(gm::dispensedRed);
        assertContains(out, "Returned invalid coin: 7");
        assertContains(out, "Insufficient funds"); // Or generic message
        assertEquals(0, gm.getBalance());
    }

    private static void test_TC16() {
        GumballMachine gm = new GumballMachine();
        gm.insertCoin(7);
        gm.insertCoin(5);

        // Return change -> Should return 5 only
        String outChange = captureOutput(gm::returnMyChange);
        assertContains(outChange, "5 cents have been returned");
        if (outChange.contains("7")) {
            throw new AssertionError("Should not return invalid coins on Change Return");
        }
        assertEquals(0, gm.getBalance());

        // Press Red -> Should return invalid coin now
        String outRed = captureOutput(gm::dispensedRed);
        assertContains(outRed, "Returned invalid coin: 7");
    }
}
