
import java.util.Scanner;
class CoffeeMachine {
    private int waterFill = 400;
    private int milkFill = 540;
    private int coffeeFillGramsBeans = 120;
    private int disposalCups = 9;
    private int availableMoney = 550;

    private int espressoWaterCup = 250;
    private int espressoGransCup = 16;
    private int espressoCost = 4;

    private int latteWaterCup = 350;
    private int latteMilkCup = 75;
    private int latteGransCup = 20;
    private int latteCost = 7;

    private int cappuccinoWaterCup = 200;
    private int cappuccinoMilkCup = 100;
    private int cappuccinoGramsCup = 12;
    private int cappuccinoCost = 6;

    private State currentState = State.MAIN_MENU;

    public void start() {
        while (true) {
            handleInput();
        }
    }

    private void handleInput() {
        switch (currentState) {
            case MAIN_MENU:
                mainMenu();
                break;
            case BUY_MENU:
                buyMenu();
                break;
            case FILL_MENU:
                fillMenu();
                break;
        }
    }

    private void mainMenu() {
        String userInputStr = mainDisplay("Write action (buy, fill, take, remaining, exit):");

        switch (userInputStr) {
            case "buy":
                currentState = State.BUY_MENU;
                break;
            case "fill":
                currentState = State.FILL_MENU;
                break;
            case "take":
                takeMoney();
                break;
            case "remaining":
                machineDisplay();
                break;
            case "exit":
                System.exit(0);
            default:
                System.out.println("Invalid option");
        }
    }

    private void buyMenu() {
        String buyOption = mainDisplay("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ");

        switch (buyOption) {
            case "1":
                makeCoffee(espressoWaterCup, 0, espressoGransCup, espressoCost);
                currentState = State.MAIN_MENU;
                break;
            case "2":
                makeCoffee(latteWaterCup, latteMilkCup, latteGransCup, latteCost);
                currentState = State.MAIN_MENU;
                break;
            case "3":
                makeCoffee(cappuccinoWaterCup, cappuccinoMilkCup, cappuccinoGramsCup, cappuccinoCost);
                currentState = State.MAIN_MENU;
                break;
            case "back":
                currentState = State.MAIN_MENU;
                break;
            default:
                System.out.println("Invalid option");
        }
    }

    private void fillMenu() {
        System.out.println("Write how many ml of water you want to add:");
        int askWater = scanner().nextInt();
        waterFill += askWater;

        System.out.println("Write how many ml of milk you want to add:");
        int askMilk = scanner().nextInt();
        milkFill += askMilk;

        System.out.println("Write how many grams of coffee beans you want to add:");
        int askGramsCoffeeBeans = scanner().nextInt();
        coffeeFillGramsBeans += askGramsCoffeeBeans;

        System.out.println("Write how disposable cups you want to add:");
        int askDisposableCups = scanner().nextInt();
        disposalCups += askDisposableCups;
        System.out.println();

        currentState = State.MAIN_MENU;
    }

    private void makeCoffee(int waterNeeded, int milkNeeded, int coffeeBeansNeeded, int cost) {
        if (checkResources(waterNeeded, milkNeeded, coffeeBeansNeeded)) {
            waterFill -= waterNeeded;
            milkFill -= milkNeeded;
            coffeeFillGramsBeans -= coffeeBeansNeeded;
            disposalCups--;

            System.out.println();
            availableMoney += cost;
        }
    }

    private boolean checkResources(int waterNeeded, int milkNeeded, int coffeeBeansNeeded) {
        boolean hasEnoughWater = waterFill >= waterNeeded;
        boolean hasEnoughMilk = milkFill >= milkNeeded;
        boolean hasEnoughCoffeeBeans = coffeeFillGramsBeans >= coffeeBeansNeeded;

        if (hasEnoughWater && hasEnoughMilk && hasEnoughCoffeeBeans) {
            System.out.println("I have enough resources, making you a coffee!");
            return true;
        } else {
            if (!hasEnoughWater) {
                System.out.println("Sorry, not enough water!");
            }
            if (!hasEnoughMilk) {
                System.out.println("Sorry, not enough milk!");
            }
            if (!hasEnoughCoffeeBeans) {
                System.out.println("Sorry, not enough coffee beans!");
            }
            System.out.println();
            return false;
        }
    }

    private void takeMoney() {
        System.out.println("I gave you $" + availableMoney);
        availableMoney = 0;
    }

    private void machineDisplay() {
        System.out.println();
        System.out.println("The coffee machine has:");
        System.out.println(waterFill + " ml of water");
        System.out.println(milkFill + " ml of milk");
        System.out.println(coffeeFillGramsBeans + " g of coffee beans");
        System.out.println(disposalCups + " disposable cups");
        System.out.println("$" + availableMoney + " of money\n");
    }

    private String mainDisplay(String message) {
        System.out.println(message);
        return scanner().next();
    }

    private Scanner scanner() {
        return new Scanner(System.in);
    }
}