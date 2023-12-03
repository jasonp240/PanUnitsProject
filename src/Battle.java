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

    public Battle(Monster player, ArrayList<Monster> monInventory) {
        int species = (int) (Math.random() * 4) + 3;
        this.player = player;
        npc = new Monster(species, player.getLevel() - ((int) (Math.random() * 2) + 1));
        this.monInventory = monInventory;
    }

    private boolean checkAllDead() {
        int deadMons = 0;
        for (int i = 0; i < monInventory.size(); i++) {
            if (monInventory.get(i).isDead()) {
                deadMons++;
            }
        }
        if (deadMons == monInventory.size()) {
            return true;
        } else {
            return false;
        }
    }

    private String displayHealth(Monster monster) {
        int health = monster.getHealth();
        int maxHealth = monster.getMaxHealth();
        double barValue = (double) maxHealth / 10;
        int barAmount = (int) Math.round(health / barValue);
        if (health < barValue) {
            return "[ + x x x x x x x x x ]";
        }
        String returnStr = "[ ";
        for (int i = 1; i <= barAmount; i++) {
            returnStr += "+ ";
        }
        for (int i = 1; i <= 10 - barAmount; i++) {
            returnStr += "x ";
        }
        return returnStr + "]";
    }

    private int superEffective(Monster attackingMonster, Monster defendingMonster) {
        if (Objects.equals(attackingMonster.getType(), "normal")) {
            return 1;
        }
        if (Objects.equals(attackingMonster.getType(), "grass") && Objects.equals(defendingMonster.getType(), "water")) {
            return 2;
        }
        if (Objects.equals(attackingMonster.getType(), "water") && Objects.equals(defendingMonster.getType(), "fire")) {
            return 2;
        }
        if (Objects.equals(attackingMonster.getType(), "fire") && (Objects.equals(defendingMonster.getType(), "grass")) || Objects.equals(defendingMonster.getType(), "bug")) {
            return 2;
        }
        if (Objects.equals(attackingMonster.getType(), "flying") && (Objects.equals(defendingMonster.getType(), "grass")) || Objects.equals(defendingMonster.getType(), "bug")) {
            return 2;
        }
        if (Objects.equals(attackingMonster.getType(), "electric") && (Objects.equals(defendingMonster.getType(), "flying")) || Objects.equals(defendingMonster.getType(), "water")) {
            return 2;
        }
        return 1;
    }

    public int initiateBattle() {
        if (checkAllDead()) {
            return 0;
        }
        int nextMon = 1;
        int npcHealth = npc.getHealth();
        Monster fasterMonster;
        Monster slowerMonster;
        if (player.getSpeed() < npc.getSpeed()) {
            fasterMonster = npc;
            slowerMonster = player;
        } else {
            fasterMonster = player;
            slowerMonster = npc;
        }
        System.out.println(fasterMonster.getName() + " VS " + slowerMonster.getName());
        while (!checkAllDead() && !npc.isDead()) {
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
            if (superEffective(fasterMonster, slowerMonster) == 2) {
                System.out.println("It is super effective!");
            }
            slowerMonster.damageHealth(fasterMonster.getStrength() * 5 * superEffective(fasterMonster, slowerMonster));
            System.out.println();
            if (slowerMonster.isDead() && slowerMonster != player) {
                return npcHealth;
            } else if (slowerMonster.isDead() && slowerMonster == player){
                System.out.println(player.getName() + " fainted!");
                if (checkAllDead()) {
                    System.out.println("You lose!");
                    return 0;
                } else {
                    while (player.getHealth() == 0) {
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
            if (player.isDead()) {
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
        return npcHealth;
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
