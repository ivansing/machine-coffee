import java.util.Scanner;

public class Main {

    private static int waterFill = 400;
    private static int milkFill = 540;
    private static int coffeeFillGramsBeans = 120;
    private static int disposalCups = 9;
    private static int availableMoney = 550;

    private static int espressoWaterCup = 250;
    private static int espressoGransCup = 16;
    private static int espressoCost = 4;

    private static int latteWaterCup = 350;
    private static int latteMilkCup = 75;
    private static int latteGransCup = 20;
    private static int latteCost = 7;

    private static int cappuccinoWaterCup = 200;
    private static int cappuccinoMilkCup = 100;
    private static int cappuccinoGramsCup = 12;
    private static int cappuccinoCost = 6;

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            String userInputStr = mainDisplay("Write action (buy, fill, take, remaining, exit):");

            switch (userInputStr) {
                case "buy":
                    // Choose coffee type
                    String buyOption = mainDisplay("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ");

                    // Check resources and make coffee
                    switch (buyOption) {
                        case "1":
                            if (checkResources(espressoWaterCup, 0, espressoGransCup)) {
                                makeCoffee(espressoWaterCup, 0, espressoGransCup, espressoCost);
                            }
                            break;
                        case "2":
                            if (checkResources(latteWaterCup, latteMilkCup, latteGransCup)) {
                                makeCoffee(latteWaterCup, latteMilkCup, latteGransCup, latteCost);
                            }
                            break;
                        case "3":
                            if (checkResources(cappuccinoWaterCup, cappuccinoMilkCup, cappuccinoGramsCup)) {
                                makeCoffee(cappuccinoWaterCup, cappuccinoMilkCup, cappuccinoGramsCup, cappuccinoCost);
                            }
                            break;
                        case "back":
                            // Go back to the main menu
                            break;
                        default:
                            System.out.println("Invalid option");
                    }
                    break;
                case "fill":
                    // Handle filling resources
                    fillResources();
                    break;
                case "take":
                    // Handle taking money out
                    System.out.println();
                    takeMoney();
                    break;
                case "remaining":
                    // Display remaining resources
                    machineDisplay();
                    break;
                case "exit":
                    // Exit the program
                    System.exit(0);
                default:
                    // Invalid option
                    System.out.println("Invalid option");
            }
        }
    }

    private static void makeCoffee(int waterNeeded, int milkNeeded, int coffeeBeansNeeded, int cost) {
        waterFill -= waterNeeded;
        milkFill -= milkNeeded;
        coffeeFillGramsBeans -= coffeeBeansNeeded;
        disposalCups--;

        System.out.println();
//        System.out.println("I have enough resources, making you a coffee!");

        availableMoney += cost;

//        machineDisplay();
    }

    private static boolean checkResources(int waterNeeded, int milkNeeded, int coffeeBeansNeeded) {
//        boolean hasEnoughResources = waterFill >= waterNeeded && milkFill >= milkNeeded && coffeeFillGramsBeans >= coffeeBeansNeeded;
    boolean hasEnoughWater = waterFill >= waterNeeded;
    boolean hasEnoughMilk = milkFill >= milkNeeded;
    boolean hasEnoughCoffeeBeans = coffeeFillGramsBeans >= coffeeBeansNeeded;

       if(hasEnoughWater && hasEnoughMilk && hasEnoughCoffeeBeans){
           System.out.println("I have enough resources, making you a coffee!");
           return true;
       } else {
           if(!hasEnoughWater){
               System.out.println("Sorry, not enough water!");
           }
           if(!hasEnoughMilk){
               System.out.println("Sorry, not enough milk!");
           }
           if(!hasEnoughCoffeeBeans){
               System.out.println("Sorry, not enough coffee beans!");
           }
           System.out.println();
           return false;
       }
    }

    private static void fillResources() {
        System.out.println("Write how many ml of water you want to add:");
        int askWater = scanner.nextInt();
        waterFill += askWater;

        System.out.println("Write how many ml of milk you want to add:");
        int askMilk = scanner.nextInt();
        milkFill += askMilk;

        System.out.println("Write how many grams of coffee beans you want to add:");
        int askGramsCoffeeBeans = scanner.nextInt();
        coffeeFillGramsBeans += askGramsCoffeeBeans;

        System.out.println("Write how disposable cups you want to add:");
        int askDisposableCups = scanner.nextInt();
        disposalCups += askDisposableCups;
        System.out.println();
//        machineDisplay();
    }

    private static void takeMoney() {
        System.out.println("I gave you $" + availableMoney);
        availableMoney = 0;
//        machineDisplay();
    }

    private static String mainDisplay(String message) {
        System.out.println(message);
        return scanner.next();
    }

    private static void machineDisplay() {
        System.out.println();
        System.out.println("The coffee machine has:");
        System.out.println(waterFill + " ml of water");
        System.out.println(milkFill + " ml of milk");
        System.out.println(coffeeFillGramsBeans + " g of coffee beans");
        System.out.println(disposalCups + " disposable cups");
        System.out.println("$" + availableMoney + " of money\n");
    }

}
