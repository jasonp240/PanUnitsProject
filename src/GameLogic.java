import java.util.ArrayList;
import java.util.Scanner;
import java.util.ArrayList;
public class GameLogic {
    private Scanner scan;

    private Monster mon1;
    private Monster mon2;
    private Monster mon3;

    private ArrayList<Monster> monInventory;

    private boolean allDead;

    private int deadMons;

    public GameLogic() {
        scan = new Scanner(System.in);
        monInventory = new ArrayList<Monster>();
        allDead = false;
    }

    public void start() {
        System.out.println("Welcome to the world of Monsters! \n");
        sleep(1000);
        System.out.println("In this world there a Monsters that you can catch and use to fight along with you! \n");
        sleep(1000);
        System.out.println("It is time for you to choose your very first monster! \n");
        System.out.println("You have 3 choices!\n");
        sleep(4000);
        System.out.print("1) ");
        mon1 = new Monster(0, 5);
        System.out.println(mon1.getDescription());
        sleep(1000);
        System.out.print("2) ");
        mon1 = new Monster(1, 5);
        System.out.println(mon1.getDescription());
        sleep(1000);
        System.out.print("3) ");
        mon1 = new Monster(2, 5);
        System.out.println(mon1.getDescription());
        sleep(1000);
        System.out.print("\nWhich one would you like to choose? (1-3): ");
        int userInput = scan.nextInt();
        scan.nextLine();
        if (userInput == 1) {
            mon1 = new Monster(0, 5);
        } else if (userInput == 2) {
            mon1 = new Monster(1,5);
        } else {
            mon1 = new Monster(2, 5);
        }
        monInventory.add(mon1);
        System.out.println();
        System.out.println("-----------------------------------");
        System.out.println();
        System.out.println("It is time for you to start you adventure!");
        System.out.println();
        System.out.println("-----------------------------------");
        System.out.println();
        mon1.damageHealth(50);

        while(!allDead){
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
                Battle battle = new Battle(mon1);
                int exp = battle.initiateBattle();
                for (int i = 0; i < monInventory.size(); i++ ) {
                    monInventory.get(i).setExp(exp);
                    System.out.println("All of your pokemon gained " + exp + " EXP!");
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

            for (int i = 0; i < monInventory.size(); i++) {
                if (monInventory.get(i).isDead()) {
                    deadMons++;
                }
            }
            if (deadMons == monInventory.size()) {
                allDead = true;
            } else {
                deadMons = 0;
            }
        }




    }

    private void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
