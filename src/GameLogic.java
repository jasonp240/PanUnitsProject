import java.util.ArrayList;
import java.util.Scanner;

public class GameLogic {
    private Scanner scan;
    private Monster starterMon;
    private ArrayList<Monster> monInventory;
    private boolean victory;

    public GameLogic() {
        scan = new Scanner(System.in);
        monInventory = new ArrayList<>();
        victory = false;
    }

    public void start() {
        System.out.println("Welcome to the world of Monsters! \n");
        sleep();
        System.out.println("In this world there are Monsters that you can catch and use to fight along with you! \n");
        sleep();
        System.out.println("It is time for you to choose your very first monster! \n");
        System.out.println("You have 3 choices!\n");
        sleep(4000);
        System.out.print("1) ");
        // switches starterMon to each of the different starters to show their descriptions
        starterMon = new Monster(0, 5);
        System.out.println(starterMon.getDescription());
        sleep();
        System.out.print("2) ");
        starterMon = new Monster(1, 5);
        System.out.println(starterMon.getDescription());
        sleep();
        System.out.print("3) ");
        starterMon = new Monster(2, 5);
        System.out.println(starterMon.getDescription());
        sleep();
        System.out.print("\nWhich one would you like to choose? (1-3): ");
        int userInput = scan.nextInt();
        scan.nextLine();
        if (userInput == 1) { // initializes their monster
            starterMon = new Monster(0, 5);
        } else if (userInput == 2) {
            starterMon = new Monster(1,5);
        } else {
            starterMon = new Monster(2, 5);
        }
        monInventory.add(starterMon);
        System.out.println();
        System.out.println("-----------------------------------");
        System.out.println();
        System.out.println("It is time for you to start you adventure!");
        System.out.println();
        System.out.println("-----------------------------------");
        System.out.println();

        while (!victory){
            for (int i = 0; i < monInventory.size(); i++) { // prints a indicator for player to know that their monster has leveled up
                if (monInventory.get(i).checkExp()) {
                    System.out.println(monInventory.get(i).getName() + " has leveled up to " + monInventory.get(i).getLevel() + "!");
                }
            }

            for (int i = 0; i < monInventory.size(); i++ ) { // prints out monsters name, health, level, and exp for player to see
                System.out.println("-----------------------");
                System.out.print("Monster " + (i + 1) + ": " );
                System.out.println(monInventory.get(i).getName());
                System.out.print("Health: ");
                System.out.println(monInventory.get(i).getHealth() + " / " + monInventory.get(i).getMaxHealth());
                System.out.print("Level: ");
                System.out.println(monInventory.get(i).getLevel());
                System.out.print("EXP: ");
                System.out.print(monInventory.get(i).getExp() + " / " + monInventory.get(i).getLevel() * 10 + "\n");

            }
            System.out.println("-----------------------");
            System.out.println("1) Go into the wilderness");
            System.out.println("2) Go to the monster healing center");
            System.out.println("3) Fight the regional champion");
            System.out.print("Enter action (1-3): ");
            userInput = scan.nextInt();
            scan.nextLine();

            while (userInput > 3 || userInput < 1) { // makes sure player doesn't choose an action outside the
                System.out.println("Invalid #!");
                System.out.print("Enter action (1-3): ");
                userInput = scan.nextInt();
                scan.nextLine();
            }

            if (userInput == 1) { // if player chooses 1, they fight a randomly generated monster
                Battle battle = new Battle(monInventory.get(0), monInventory);
                int exp = battle.initiateBattle(); // returns exp value equal to the health of the monster defeated
                if (exp == 0) { // if the battle returns 0, all monsters are dead and their exp value gets reset
                    for (int i = 0; i < monInventory.size(); i++) {
                        monInventory.get(i).resetEXP(); // resets each individual monster by iterating through inventory
                    }
                    System.out.println("All of your monsters have fallen asleep \uD83D\uDCA4");
                } else { // if player wins, print an indicator of how much exp each monster has gained
                    for (int i = 0; i < monInventory.size(); i++ ) {
                        monInventory.get(i).setExp(exp);
                        System.out.println(monInventory.get(i).getName() + " gained " + exp + " EXP!");
                    }
                    System.out.println("Do you want to catch " + battle.getMonster().getName()); // after defeating monster ask if they want to catch it
                    System.out.println("1) Yes");
                    System.out.println("2) No");
                    System.out.print("Enter option (1-2): ");
                    int userInput2 = scan.nextInt();
                    scan.nextLine();
                    while (userInput2 != 1 && userInput2 != 2) {
                        System.out.println("Invalid input!");
                        System.out.print("Enter option (1-2): ");
                        userInput2 = scan.nextInt();
                        scan.nextLine();
                    }
                    if (userInput2 == 1) {
                        if (monInventory.size() < 3) { // if player inventory is less than 3 monsters they can catch it
                            monInventory.add(battle.getMonster());
                            System.out.println(battle.getMonster().getName() + " successfully caught!");
                        } else { // if player has more than 3 monsters, allow them to remove a monster to make room
                            System.out.println("Maximum number of monsters caught!");
                            System.out.println("Would you like to release a monster?");
                            System.out.println("1) Yes");
                            System.out.println("2) No");
                            System.out.print("Enter option (1-2): ");
                            int userInput3 = scan.nextInt();
                            scan.nextLine();
                            while (userInput3 != 1 && userInput3 != 2) {
                                System.out.println("Invalid input!");
                                System.out.print("Enter option (1-2): ");
                                userInput3 = scan.nextInt();
                                scan.nextLine();
                            }
                            if (userInput3 == 1) {
                                for (int i = 0; i < monInventory.size(); i++ ) { // prints out the order of the monster, so they can be removed
                                    System.out.println((i + 1) + ") " + monInventory.get(i).getName());
                                }
                                System.out.println("Which monster do you want to release? (1-3): ");
                                int userInput4 = scan.nextInt();
                                scan.nextLine();
                                while (userInput4 != 1 && userInput4 != 2 && userInput4 != 3) {
                                    System.out.println("Invalid input!");
                                    System.out.print("Enter option (1-3): ");
                                    userInput4 = scan.nextInt();
                                    scan.nextLine();
                                }
                                System.out.println(monInventory.get(userInput4 - 1).getName() + " has been released!");
                                monInventory.remove(userInput4 - 1); // removes that monster from inventory
                                monInventory.add(battle.getMonster()); // adds new monster to inventory
                                System.out.println(battle.getMonster().getName() + " successfully caught!");
                            }
                        }
                    }
                }
            }

            if (userInput == 2) {
                for (int i = 0; i < monInventory.size(); i++) {
                    monInventory.get(i).fullHealth(); // iterates through all monsters and heals them
                }
                System.out.println();
                System.out.println("-------------------------------------");
                System.out.println("All of your monsters are full health!");
                System.out.println("-------------------------------------");
                System.out.println();
                sleep();
            }

            if (userInput == 3) {
                Monster boss1 = new Monster(3, 20); // initializes strong monster
                Battle battle = new Battle(starterMon, boss1, monInventory);
                int exp = battle.initiateBattle();
                if (exp == 0) {
                    for (int i = 0; i < monInventory.size(); i++) {
                        monInventory.get(i).resetEXP();
                    }
                    System.out.println("All of your monsters have fallen asleep \uD83D\uDCA4");
                } else {
                    victory = true; // if player wins (exp returned is greater than 0) loop ends
                }
            }
        }
        System.out.println("YOU ARE THE WORLD CHAMPION!!! \uD83D\uDCAF \uD83E\uDD76 \uD83D\uDD25 \uD83D\uDE0E"); // THEY ARE THE WORLD CHAMPION!!!
    }

    private void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
