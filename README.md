# Gumball Vending Machine

A Java-based simulation of a Gumball Vending Machine, developed as a team assignment.

## Features
- **Coin Insertion**: Accepts 5, 10, and 25 cent coins.
- **Two Flavors**: Red (5 cents) and Yellow (10 cents) gumballs.
- **Change Return**: Returns remaining valid balance.
- **Invalid Coin Handling**: Buffers invalid coins and returns them only when a dispense lever is pressed.
- **Interactive CLI**: A command-line interface to simulate user interaction.

## Getting Started

No external dependencies (Maven/Gradle) are required. All you need is a standard Java JDK.

### Compilation
Open a terminal in the project root directory and run:

```bash
javac src/*.java
```

This will compile all source files in the `src` directory.

## How to Run

### 1. Interactive Mode (The "App")
To use the machine yourself:

```bash
java -cp src Main
```

**Commands:**
- `25` or `insert 25`: Insert a quarter.
- `10` or `insert 10`: Insert a dime.
- `5` or `insert 5`: Insert a nickel.
- `red`: Turn the Red lever (dispense 1 red gumball).
- `yellow`: Turn the Yellow lever (dispense 1 yellow gumball).
- `change`: Return current balance.
- `exit`: Quit.

**Example Session:**
```text
> 25
Balance = 25
> red
1 red gumball dispensed.
Balance = 20
```

### 2. Test Mode (Automated Verificaton)
To run the automated test suite (16 test cases covering all requirements):

```bash
java -cp src GumballMachineTest
```

**Expected Output:**
```text
TC-01: Verify basic valid coin acceptance (nickel)... PASS
...
TC-16: Verify Return My Change does not return invalid coins... PASS
ALL TESTS PASSED.
```

## Project Structure
- `src/Main.java`: The interactive application entry point.
- `src/GumballMachine.java`: The core logic class.
- `src/GumballMachineTest.java`: The custom test runner with 16 test cases.
