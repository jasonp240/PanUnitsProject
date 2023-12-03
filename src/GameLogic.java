import java.util.ArrayList;
import java.util.Scanner;
import java.util.ArrayList;
public class GameLogic {
    private Scanner scan;
    private Monster starterMon;
    private ArrayList<Monster> monInventory;
    private boolean victory;

    public GameLogic() {
        scan = new Scanner(System.in);
        monInventory = new ArrayList<Monster>();
        victory = false;
    }

    public void start() {
        System.out.println("Welcome to the world of Monsters! \n");
        sleep(1000);
        System.out.println("In this world there are Monsters that you can catch and use to fight along with you! \n");
        sleep(1000);
        System.out.println("It is time for you to choose your very first monster! \n");
        System.out.println("You have 3 choices!\n");
        sleep(4000);
        System.out.print("1) ");
        starterMon = new Monster(0, 5);
        System.out.println(starterMon.getDescription());
        sleep(1000);
        System.out.print("2) ");
        starterMon = new Monster(1, 5);
        System.out.println(starterMon.getDescription());
        sleep(1000);
        System.out.print("3) ");
        starterMon = new Monster(2, 5);
        System.out.println(starterMon.getDescription());
        sleep(1000);
        System.out.print("\nWhich one would you like to choose? (1-3): ");
        int userInput = scan.nextInt();
        scan.nextLine();
        if (userInput == 1) {
            starterMon = new Monster(0, 1000);
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
            for (int i = 0; i < monInventory.size(); i++) {
                if (monInventory.get(i).checkExp()) {
                    System.out.println(monInventory.get(i).getName() + " has leveled up to " + monInventory.get(i).getLevel() + "!");
                }
            }

            for (int i = 0; i < monInventory.size(); i++ ) {
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

            while (userInput > 3 || userInput < 1) {
                System.out.println("Invalid #!");
                System.out.print("Enter action (1-3): ");
                userInput = scan.nextInt();
                scan.nextLine();
            }

            if (userInput == 1) {
                Battle battle = new Battle(monInventory.get(0), monInventory);
                int exp = battle.initiateBattle();
                if (exp == 0) {
                    for (int i = 0; i < monInventory.size(); i++) {
                        monInventory.get(i).resetEXP();
                    }
                    System.out.println("All of your monsters have fallen asleep \uD83D\uDCA4");
                } else {
                    for (int i = 0; i < monInventory.size(); i++ ) {
                        monInventory.get(i).setExp(exp);
                        System.out.println(monInventory.get(i).getName() + " gained " + exp + " EXP!");
                    }
                    System.out.println("Do you want to catch " + battle.getMonster().getName());
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
                        if (monInventory.size() < 3) {
                            monInventory.add(battle.getMonster());
                            System.out.println(battle.getMonster().getName() + " successfully caught!");
                        } else {
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
                                for (int i = 0; i < monInventory.size(); i++ ) {
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
                                monInventory.remove(userInput4 - 1);
                                monInventory.add(battle.getMonster());
                                System.out.println(battle.getMonster().getName() + " successfully caught!");
                            }
                        }
                    }
                }
            }

            if (userInput == 2) {
                for (int i = 0; i < monInventory.size(); i++) {
                    monInventory.get(i).fullHealth();
                }
                System.out.println();
                System.out.println("-------------------------------------");
                System.out.println("All of your monsters are full health!");
                System.out.println("-------------------------------------");
                System.out.println();
                sleep(1000);
            }

            if (userInput == 3) {
                Monster boss1 = new Monster(3, 20);
                Battle battle = new Battle(starterMon, boss1, monInventory);
                int exp = battle.initiateBattle();
                if (exp == 0) {
                    for (int i = 0; i < monInventory.size(); i++) {
                        monInventory.get(i).resetEXP();
                    }
                    System.out.println("All of your monsters have fallen asleep \uD83D\uDCA4");
                } else {
                    victory = true;
                }
            }
        }
        System.out.println("YOU ARE THE WORLD CHAMPION!!! \uD83D\uDCAF \uD83E\uDD76 \uD83D\uDD25 \uD83D\uDE0E");
    }

    private void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
