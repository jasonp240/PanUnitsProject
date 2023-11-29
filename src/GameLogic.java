import java.util.Scanner;

public class GameLogic {
    private Scanner scan;
    private Monster mon1;
    private Monster mon2;
    private Monster mon3;

    private Battle battle;

    public GameLogic() {
        scan = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Welcome to the world of Monsters! \n");
        System.out.println("In this world there a Monsters that you can catch and use to fight along with you! \n");
        System.out.println("It is time for you to choose your very first monster! \n");
        System.out.println("You have 3 choices!\n");
        System.out.print("1) ");
        mon1 = new Monster(0, 5);
        System.out.println(mon1.getDescription());
        System.out.print("2) ");
        mon1 = new Monster(1, 5);
        System.out.println(mon1.getDescription());
        System.out.print("3) ");
        mon1 = new Monster(2, 5);
        System.out.println(mon1.getDescription());
        System.out.print("\n Which one would you like to choose? (1-3) ");
        int userInput = scan.nextInt();
        scan.nextLine();
        if (userInput == 1) {
            mon1 = new Monster(0, 5);
        } else if (userInput == 2) {
            mon1 = new Monster(1,5);
        } else {
            mon1 = new Monster(2, 5);
        }
    }

}
