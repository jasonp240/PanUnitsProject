public class Monster {
    private int health;
    private int speed;
    private int maxHealth;
    private String type;
    private int level;
    private int exp;
    private int species;
    private int strength;
    private String name;
    private boolean dead;

    public Monster(int species, int level) {
        this.species = species;
        this.level = level;
        if (species == 0) {
            type = "grass";
            name = findName(species);
        }

        if (species == 1) {
            type = "fire";
            name = findName(species);
        }

        if (species == 2) {
            type = "water";
            name = findName(species);
        }

        if (species == 3) {
            type = "flying";
            name = findName(species);
        }

        if (species == 4) {
            type = "normal";
            name = findName(species);
        }

        if (species == 5) {
            type = "bug";
            name = findName(species);
        }

        if (species == 6) {
            type = "electric";
            name = findName(species);
        }
        exp = 0;
        strength = 1 + level;
        dead = false;
        health = 30 + level * 10;
        maxHealth = health;
        speed = (int) (Math.random() * 3) + 1;
    }

    public void checkExp() {
        int expLim = level * 10;
        if (exp >= expLim) {
            level ++;
            health += 10;
            maxHealth += 10;
            strength += 1;
        }
        exp = exp - expLim; // extra exp is saved for next level
    }

    public String findName(int speciesNum) {
        if (speciesNum == 0) {
            return "Leafrook";
        }
        if (speciesNum == 1) {
            return "Scorchicub";
        }
        if (speciesNum == 2) {
            return "Squallfin";
        }
        if (speciesNum == 3) {
            return "Skyquill";
        }
        if (speciesNum == 4) {
            return "Scurryfluff";
        }
        if (speciesNum == 5) {
            return "Lumibug";
        }
        if (speciesNum == 6) {
            return "Joltbunny";
        }
        return "";
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int expGain) {
        exp += expGain;
    }

    public void damageHealth(int damage) {
        health -= damage;
        if (health <= 0) {
            health = 0;
        }
    }

    public void fullHealth() {
        health = maxHealth;
    }

    public boolean isDead() {
        if (health > 0) {
            return true;
        }
        return false;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getLevel() {
        return level;
    }

    public int getStrength() {
        return strength;
    }

    public int getSpeed() {
        return speed;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        if (species == 0) {
            return "Leafrook (Grass Type): A sprightly grass Pokémon that resembles a tiny frog.";
        }
        if (species == 1) {
            return "Scorchicub (Fire Type): A cute and cuddly cub Pokémon with a fur that radiates warmth and a playful, fiery spirit.";
        }
        if (species == 2) {
            return "Squallfin (Water Type): A playful water Pokémon resembling a combination of a fish and a bubble.";
        }
        if (species == 3) {
            return "Skyquill (Flying Type): A small bird Pokémon with feather quills that it uses to write messages in the sky with clouds.";
        }
        if (species == 4) {
            return "Scurryfluff (Normal Type): A rat Pokémon with soft, fluffy fur that allows it to move silently through various environments.";
        }
        if (species == 5) {
            return "Lumibug (Bug Type): A small bug Pokémon that emits a soft, bioluminescent glow, making it both enchanting and helpful for illuminating dark spaces.";
        }
        if (species == 6) {
            return "Joltbunny (Electric Type): This bunny Pokémon is known for its quick movements and the ability to generate small sparks when hopping.";
        }
        return "";
    }
}
