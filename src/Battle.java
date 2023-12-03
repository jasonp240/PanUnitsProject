import java.util.ArrayList;
import java.util.Objects;

public class Battle {
    private Monster player;
    private Monster npc;
    private ArrayList<Monster> monInventory;

    public Battle(Monster player, Monster npc, ArrayList<Monster> monInventory) {
        this.player = player;
        this.npc = npc;
        this.monInventory = monInventory;
    }

    public Battle(Monster player, ArrayList<Monster> monInventory) { // constructor for wilderness (Generates random enemy)
        int species = (int) (Math.random() * 4) + 3;
        this.player = player;
        npc = new Monster(species, player.getLevel() - ((int) (Math.random() * 2) + 1));
        this.monInventory = monInventory;
    }

    private boolean checkAllDead() {
        int deadMons = 0;
        for (int i = 0; i < monInventory.size(); i++) { // iterates through all monsters in inventory
            if (monInventory.get(i).isDead()) { // checks if the monster is dead
                deadMons++;
            }
        }
        if (deadMons == monInventory.size()) { // if total monsters dead is equal to the total monsters it returns true
            return true;
        } else {
            return false;
        }
    }

    private String displayHealth(Monster monster) {
        int health = monster.getHealth(); // finds health of monster
        int maxHealth = monster.getMaxHealth(); // finds max health of monster
        double barValue = (double) maxHealth / 10; // finds the value of each individual "bar"
        int barAmount = (int) Math.round(health / barValue); // finds how much health bars are shown
        if (health < barValue) { // returns at least 1 health bar if health is below bar amount
            return "[ + x x x x x x x x x ]";
        }
        String returnStr = "[ ";
        for (int i = 1; i <= barAmount; i++) { // adds a "+" for every health bar
            returnStr += "+ ";
        }
        for (int i = 1; i <= 10 - barAmount; i++) { // adds a "x" for every health bar gone
            returnStr += "x ";
        }
        return returnStr + "]";
    }

    private int superEffective(Monster attackingMonster, Monster defendingMonster) {
        if (Objects.equals(attackingMonster.getType(), "grass") && Objects.equals(defendingMonster.getType(), "water")) {
            return 2;
        }
        if (Objects.equals(attackingMonster.getType(), "water") && Objects.equals(defendingMonster.getType(), "fire")) {
            return 2;
        }
        if (Objects.equals(attackingMonster.getType(), "fire") && (Objects.equals(defendingMonster.getType(), "grass") || Objects.equals(defendingMonster.getType(), "bug"))) {
            return 2;
        }
        if (Objects.equals(attackingMonster.getType(), "flying") && (Objects.equals(defendingMonster.getType(), "grass") || Objects.equals(defendingMonster.getType(), "bug"))) {
            return 2;
        }
        if (Objects.equals(attackingMonster.getType(), "electric") && (Objects.equals(defendingMonster.getType(), "flying") || Objects.equals(defendingMonster.getType(), "water"))) {
            return 2;
        }
        if (Objects.equals(attackingMonster.getType(), "bug") && Objects.equals(defendingMonster.getType(), "grass")) {
            return 2;
        }
        return 1;
    }

    public int initiateBattle() {
        if (checkAllDead()) { // instantly ends if all monsters are dead
            return 0;
        }
        int nextMon = 1; // used to switch to next monster if current monster is dead
        int npcHealth = npc.getHealth(); // gets the original health of the npc (used for exp)
        Monster fasterMonster;
        Monster slowerMonster;
        if (player.getSpeed() < npc.getSpeed()) { // finds the faster monster (who attacks first)
            fasterMonster = npc;
            slowerMonster = player;
        } else {
            fasterMonster = player;
            slowerMonster = npc;
        }
        System.out.println(fasterMonster.getName() + " VS " + slowerMonster.getName());
        while (!checkAllDead() && !npc.isDead()) { // code continues to run until either all the player's monsters are dead or the npc is dead
            System.out.println("-----------------------");
            System.out.print(fasterMonster.getName());
            if (fasterMonster == player) {
                System.out.print(" (Player) \n");
            } else {
                System.out.print("\n");
            }
            System.out.println("-----------------------");
            System.out.println(displayHealth(fasterMonster));
            System.out.println("Health: " + fasterMonster.getHealth() + " / " + fasterMonster.getMaxHealth());
            System.out.println("-----------------------");
            System.out.println();
            System.out.println("-----------------------");
            System.out.print(slowerMonster.getName());
            if (slowerMonster == player) {
                System.out.print(" (Player) \n");
            } else {
                System.out.print("\n");
            }
            System.out.println("-----------------------");
            System.out.println(displayHealth(slowerMonster));
            System.out.println("Health: " + slowerMonster.getHealth() + " / " + slowerMonster.getMaxHealth());
            System.out.println("-----------------------");
            System.out.println();
            System.out.println(fasterMonster.getName() + " attacks " + slowerMonster.getName() + " for " + fasterMonster.getStrength() * 5 * superEffective(fasterMonster, slowerMonster) + " damage!");
            // calculates the attack by multiplying the monsters strength with the superEffective multiplier to be printed
            if (superEffective(fasterMonster, slowerMonster) == 2) {
                System.out.println("It is super effective!");
            }
            slowerMonster.damageHealth(fasterMonster.getStrength() * 5 * superEffective(fasterMonster, slowerMonster));
            // calculates the attack by multiplying the monsters strength with the superEffective multiplier
            System.out.println();
            if (slowerMonster.isDead() && slowerMonster != player) { // if the monster dies before attacking player should win or switch to another monster
                return npcHealth;
            } else if (slowerMonster.isDead() && slowerMonster == player){
                System.out.println(player.getName() + " fainted!");
                if (checkAllDead()) { // if all player monsters are dead returns "0" so logic class knows that player has loss
                    System.out.println("You lose!");
                    return 0;
                } else {
                    while (player.getHealth() == 0) { // if all monsters aren't dead it will change to the next monster for the player
                        player = monInventory.get(nextMon);
                        slowerMonster = player;
                        nextMon++;
                    }
                }
            }
            System.out.println(slowerMonster.getName() + " attacks " + fasterMonster.getName() + " for " + slowerMonster.getStrength() * 5 * superEffective(slowerMonster, fasterMonster) + " damage!");
            if (superEffective(slowerMonster, fasterMonster) == 2) {
                System.out.println("It is super effective!");
            }
            fasterMonster.damageHealth(slowerMonster.getStrength() * 5 * superEffective(slowerMonster, fasterMonster));
            System.out.println();
            sleep(2000);
            if (player.isDead()) { // same logic as lines 125 - 139
                System.out.println(player.getName() + " fainted!");
                if (checkAllDead()) {
                    System.out.println("You lose!");
                    return 0;
                } else {
                    while (player.getHealth() == 0) {
                        player = monInventory.get(nextMon);
                        fasterMonster = player;
                        nextMon++;
                    }
                }
            }
        }
        System.out.println("You win!");
        return npcHealth; // returns exp amount equal to the health of the monster fought
    }

    public Monster getMonster() {
        return npc;
    }
   private void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }


}
