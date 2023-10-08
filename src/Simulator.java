import java.util.ArrayList;

public class Simulator {

    private int strengthToughnessThreshold(int strength, int toughness) {
        int woundTarget;
        if (strength == toughness) {
            woundTarget = 4;
        } else if (strength > toughness && strength < toughness * 2) {
            woundTarget = 3;
        } else if (strength >= toughness * 2) {
            woundTarget = 2;
        } else if (strength < toughness && strength > toughness / 2) {
            woundTarget = 5;
        } else {
            woundTarget = 6;
        }
        return woundTarget;
    }

    private int saveThreshold(int invSave, int save, int armourPenetration) {
        int saveTarget;
        if (invSave == 0) {
            saveTarget = save + armourPenetration;
        } else {
            saveTarget = Math.min(save + armourPenetration, invSave);
        }
        return saveTarget;
    }

    public ArrayList<Integer> simulate(Attacker attack, Defender defend, int repetitions) {
        // Calculate on what attacker wounds
        int strength = attack.strength;
        int armourPenetration = attack.armourPenetration;
        int numberOfAttacks = attack.numberOfAttacks;
        int weaponSkill = attack.weaponSkill;
        int damage = attack.damage;

        int toughness = defend.toughness;
        int invSave = defend.invSave;
        int save = defend.save;
        int wounds = defend.wounds;

        int woundTarget = strengthToughnessThreshold(strength, toughness);

        int saveTarget = saveThreshold(invSave, save, armourPenetration);

        ArrayList<Integer> allDamage = new ArrayList<>();
        // Run functions
        for (int i=0; i<repetitions; i++) {
            RollResults hitRoll = DiceRoller.roll(numberOfAttacks).getAbove(weaponSkill);
            RollResults woundRoll = DiceRoller.roll(hitRoll.size()).getAbove(woundTarget);
            RollResults failedSaves = DiceRoller.roll(woundRoll.size()).getBelow(saveTarget);

            int damageTaken = failedSaves.size() * damage;
            allDamage.add(damageTaken);

            if (damageTaken >= wounds) {
                System.out.println("Defender has taken " + damageTaken + " damage and is dead.");
            } else if (damageTaken == 0) {
                System.out.println("Defender has " + wounds + " wounds remaining.");
            } else {
                System.out.println("Defender has taken " + damageTaken + " damage and they have " + (wounds - damageTaken) + " wounds remaining.");
            }
        }
        return allDamage;
    }
}